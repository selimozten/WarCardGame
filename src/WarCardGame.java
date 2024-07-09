import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import model.Game;
import view.GameGUI;

public class WarCardGame {
    public static void main(String[] args) {
        try {
            // Set the system look and feel for a more native appearance
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Customize global UI settings
        UIManager.put("Button.arc", 15);
        UIManager.put("Component.arc", 10);
        UIManager.put("TextComponent.arc", 10);
        UIManager.put("Button.background", new Color(70, 130, 180)); // Steel blue
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Menu.font", new Font("Segoe UI", Font.BOLD, 14));
        UIManager.put("MenuItem.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14));

        SwingUtilities.invokeLater(() -> {
            List<String> playerNames = Arrays.asList("Player 1", "Player 2");
            Game game = new Game(playerNames);
            game.startGame();
            GameGUI gui = new GameGUI(game);
            
            // Set a custom icon for the game window
            ImageIcon icon = new ImageIcon("src/images/game_icon.png");
            if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                gui.setIconImage(icon.getImage());
            } else {
                System.err.println("Failed to load game icon");
            }
            
            // Set a minimum size for the game window
            gui.setMinimumSize(new Dimension(900, 700));
            
            // Center the window on the screen
            gui.setLocationRelativeTo(null);
            
            gui.setVisible(true);
        });
    }
}