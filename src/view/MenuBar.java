package view;

import model.Game;
import model.GameSaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar implements ActionListener {
    private JMenuItem newGameItem;
    private JMenuItem saveGameItem;
    private JMenuItem loadGameItem;
    private JMenuItem aboutItem;
    private GameGUI gameGUI;

    public MenuBar(GameGUI gameGUI) {
        this.gameGUI = gameGUI;
        initializeMenu();
    }

    private void initializeMenu() {
        setBackground(new Color(50, 50, 50));

        JMenu gameMenu = createMenu("Game");
        newGameItem = createMenuItem("New Game");
        saveGameItem = createMenuItem("Save Game");
        loadGameItem = createMenuItem("Load Game");

        gameMenu.add(newGameItem);
        gameMenu.add(saveGameItem);
        gameMenu.add(loadGameItem);

        JMenu helpMenu = createMenu("Help");
        aboutItem = createMenuItem("About");
        helpMenu.add(aboutItem);

        add(gameMenu);
        add(helpMenu);
    }

    private JMenu createMenu(String text) {
        JMenu menu = new JMenu(text);
        menu.setForeground(Color.WHITE);
        menu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return menu;
    }

    private JMenuItem createMenuItem(String text) {
        JMenuItem item = new JMenuItem(text);
        item.setBackground(new Color(60, 60, 60));
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        item.addActionListener(this);
        return item;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameItem) {
            int choice = JOptionPane.showConfirmDialog(gameGUI,
                    "Are you sure you want to start a new game? Current game progress will be lost.",
                    "New Game",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                gameGUI.startNewGame();
            }
        } else if (e.getSource() == saveGameItem) {
            if (GameSaver.saveGame(gameGUI.getGame())) {
                JOptionPane.showMessageDialog(gameGUI,
                        "Game saved successfully.",
                        "Save Game",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(gameGUI,
                        "Failed to save game. Please try again.",
                        "Save Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == loadGameItem) {
            int choice = JOptionPane.showConfirmDialog(gameGUI,
                    "Are you sure you want to load a saved game? Current game progress will be lost.",
                    "Load Game",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                Game loadedGame = GameSaver.loadGame();
                if (loadedGame != null) {
                    gameGUI.setGame(loadedGame);
                    JOptionPane.showMessageDialog(gameGUI,
                            "Game loaded successfully.",
                            "Load Game",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(gameGUI,
                            "Failed to load game. The save file might be corrupted or not found.",
                            "Load Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == aboutItem) {
            JOptionPane.showMessageDialog(gameGUI,
                    "War Card Game\nDeveloped by: Selim Özten\nVersion: 1.1",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}