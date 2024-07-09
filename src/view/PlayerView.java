package view;

import model.Player;
import model.Card;

import javax.swing.*;
import java.awt.*;

public class PlayerView extends JPanel {
    private Player player;
    private JLabel nameLabel;
    private JLabel cardCountLabel;
    private CardView topCard;

    public PlayerView(Player player) {
        this.player = player;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(0, 0, 0, 80));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        nameLabel = new JLabel(player.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);
        add(nameLabel, BorderLayout.NORTH);

        topCard = new CardView(null, true);  // Changed to true for face up
        add(topCard, BorderLayout.CENTER);

        cardCountLabel = new JLabel("Cards: " + player.getCardCount(), SwingConstants.CENTER);
        cardCountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cardCountLabel.setForeground(Color.WHITE);
        add(cardCountLabel, BorderLayout.SOUTH);
    }

    public void update() {
        cardCountLabel.setText("Cards: " + player.getCardCount());
        Card topPlayerCard = player.peekTopCard();
        topCard.setCard(topPlayerCard);
        topCard.setFaceUp(true);  // Always set to true to show the face
        revalidate();
        repaint();
    }
}