package view;

import model.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CardView extends JPanel {
    private Card card;
    private boolean faceUp;
    private static BufferedImage cardBack;
    private static BufferedImage[][] cardImages = new BufferedImage[4][13];
    private static final String[] SUITS = {"hearts", "diamonds", "clubs", "spades"};
    private static final String[] RANKS = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    static {
        try {
            String imagePath = "src/images/card_back.png";
            File file = new File(imagePath);
            if (!file.exists()) {
                System.err.println("Card back image not found: " + imagePath);
            } else {
                cardBack = ImageIO.read(file);
                System.out.println("Card back image loaded successfully");
            }

            for (int i = 0; i < SUITS.length; i++) {
                for (int j = 0; j < RANKS.length; j++) {
                    imagePath = "src/images/" + RANKS[j] + "_of_" + SUITS[i] + ".png";
                    file = new File(imagePath);
                    if (!file.exists()) {
                        System.err.println("Card image not found: " + imagePath);
                    } else {
                        cardImages[i][j] = ImageIO.read(file);
                        System.out.println("Loaded image: " + imagePath);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CardView(Card card, boolean faceUp) {
        this.card = card;
        this.faceUp = faceUp;
        setPreferredSize(new Dimension(120, 180));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (card == null) {
            drawEmptyCard(g2d);
        } else if (faceUp) {
            drawFaceUpCard(g2d);
        } else {
            drawFaceDownCard(g2d);
        }
    }

    private void drawEmptyCard(Graphics2D g2d) {
        g2d.setColor(new Color(200, 200, 200, 100));
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        g2d.setColor(new Color(150, 150, 150));
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        g2d.setColor(new Color(100, 100, 100));
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 16));
        drawCenteredString(g2d, "No Card", getWidth() / 2, getHeight() / 2);
    }

    private void drawFaceUpCard(Graphics2D g2d) {
        int suitIndex = card.getSuit().ordinal();
        int rankIndex = card.getRank().ordinal();
        if (cardImages[suitIndex][rankIndex] != null) {
            g2d.drawImage(cardImages[suitIndex][rankIndex], 0, 0, getWidth(), getHeight(), this);
        } else {
            g2d.setColor(Color.WHITE);
            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
            g2d.setColor(Color.BLACK);
            g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            drawCenteredString(g2d, card.toString(), getWidth() / 2, getHeight() / 2);
        }
    }

    private void drawFaceDownCard(Graphics2D g2d) {
        if (cardBack != null) {
            g2d.drawImage(cardBack, 0, 0, getWidth(), getHeight(), this);
        } else {
            g2d.setColor(new Color(25, 25, 112));
            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            g2d.setColor(Color.WHITE);
            g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
    }

    private void drawCenteredString(Graphics2D g2d, String text, int x, int y) {
        FontMetrics metrics = g2d.getFontMetrics();
        int textX = x - (metrics.stringWidth(text) / 2);
        int textY = y - (metrics.getHeight() / 2) + metrics.getAscent();
        g2d.drawString(text, textX, textY);
    }


    public void setCard(Card card) {
        this.card = card;
        repaint();
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
        repaint();
    }
}