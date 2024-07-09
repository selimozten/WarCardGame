package model;

import java.io.*;

public class GameSaver {
    private static final String SAVE_FILE = "war_game_save.dat";

    public static boolean saveGame(Game game) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(game);
            System.out.println("Game saved successfully to " + SAVE_FILE);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static Game loadGame() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            System.err.println("No saved game found at " + SAVE_FILE);
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Game game = (Game) ois.readObject();
            System.out.println("Game loaded successfully from " + SAVE_FILE);
            return game;
        } catch (IOException e) {
            System.err.println("Error reading saved game: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Saved game format is incompatible. " + e.getMessage());
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.err.println("Error: Saved file does not contain a valid Game object. " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}