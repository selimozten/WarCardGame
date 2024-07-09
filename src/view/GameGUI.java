package view;

import model.Game;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameGUI extends JFrame {
    private Game game;
    private PlayerView[] playerViews;
    private JButton[] playButtons;
    private JButton startRoundButton;
    private JLabel statusLabel;

    public GameGUI(Game game) {
        this.game = game;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("War Card Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            setContentPane(new BackgroundPanel("src/images/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            setContentPane(new JPanel());
        }

        getContentPane().setLayout(new BorderLayout(20, 20));

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Players panel
        JPanel playersPanel = new JPanel(new GridLayout(1, game.getPlayers().size(), 20, 0));
        playersPanel.setOpaque(false);
        playerViews = new PlayerView[game.getPlayers().size()];
        for (int i = 0; i < game.getPlayers().size(); i++) {
            playerViews[i] = new PlayerView(game.getPlayers().get(i));
            playersPanel.add(playerViews[i]);
        }
        mainPanel.add(playersPanel, BorderLayout.CENTER);

        // Control panel
        JPanel controlPanel = new JPanel(new BorderLayout(10, 10));
        controlPanel.setOpaque(false);
        JPanel buttonPanel = new JPanel(new GridLayout(1, game.getPlayers().size(), 10, 0));
        buttonPanel.setOpaque(false);
        playButtons = new JButton[game.getPlayers().size()];
        for (int i = 0; i < game.getPlayers().size(); i++) {
            playButtons[i] = createStyledButton("Play Card", new Color(70, 130, 180));
            final int playerIndex = i;
            playButtons[i].addActionListener(e -> playCard(playerIndex));
            buttonPanel.add(playButtons[i]);
        }
        controlPanel.add(buttonPanel, BorderLayout.CENTER);

        startRoundButton = createStyledButton("Start Round", new Color(46, 139, 87));
        startRoundButton.addActionListener(e -> startRound());
        controlPanel.add(startRoundButton, BorderLayout.NORTH);

        statusLabel = new JLabel("Click 'Start Round' to begin", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        controlPanel.add(statusLabel, BorderLayout.SOUTH);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        setJMenuBar(new MenuBar(this));

        pack();
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900, 700));
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker(), 2),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        return button;
    }

    private void startRound() {
        game.startRound();
        updateControls();
        statusLabel.setText("Players, play your cards!");
        SoundPlayer.playSound("src/sounds/round_start.wav");
    }

    private void playCard(int playerIndex) {
        Player player = game.getPlayers().get(playerIndex);
        if (game.playCard(player)) {
            updatePlayerViews();
            playButtons[playerIndex].setEnabled(false);
            SoundPlayer.playSound("src/sounds/card_play.wav");
            if (game.isRoundComplete()) {
                if (game.isWarScenario()) {
                    handleWarScenario();
                } else {
                    game.resolveRound();
                    updatePlayerViews();
                    if (game.isGameOver()) {
                        endGame();
                    } else {
                        statusLabel.setText("Round complete. Click 'Start Round' for the next round.");
                        startRoundButton.setEnabled(true);
                    }
                }
            }
        }
    }

    private void handleWarScenario() {
        statusLabel.setText("WAR! Each player draws up to 3 face-down cards and 1 face-up card.");
        SoundPlayer.playSound("src/sounds/war.wav");
        game.resolveWar();
        updatePlayerViews();
        if (game.isGameOver()) {
            endGame();
        } else {
            statusLabel.setText("War resolved. Click 'Start Round' for the next round.");
            startRoundButton.setEnabled(true);
        }
    }

    private void updatePlayerViews() {
        for (PlayerView playerView : playerViews) {
            playerView.update();
        }
    }

    private void updateControls() {
        for (int i = 0; i < playButtons.length; i++) {
            playButtons[i].setEnabled(game.getPlayers().get(i).hasCards());
        }
        startRoundButton.setEnabled(false);
    }

    private void endGame() {
        Player winner = game.getWinner();
        if (winner != null) {
            JOptionPane.showMessageDialog(this, "Game Over! Winner: " + winner.getName() + 
                    "\nTotal Cards: " + winner.getCardCount());
            SoundPlayer.playSound("src/sounds/game_over.wav");
        } else {
            JOptionPane.showMessageDialog(this, "Game Over! It's a tie!");
        }
        for (JButton button : playButtons) {
            button.setEnabled(false);
        }
        startRoundButton.setEnabled(false);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        updatePlayerViews();
        updateControls();
        statusLabel.setText("Game loaded. Click 'Start Round' to begin.");
        startRoundButton.setEnabled(true);
    }

    public void startNewGame() {
        game.startGame();
        updatePlayerViews();
        updateControls();
        statusLabel.setText("New game started. Click 'Start Round' to begin.");
        startRoundButton.setEnabled(true);
    }
}