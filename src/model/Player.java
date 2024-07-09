package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int id;
    private List<Card> cardPile;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
        this.cardPile = new ArrayList<>();
    }

    public void addCard(Card card) {
        cardPile.add(card);
    }

    public Card playCard() {
        if (cardPile.isEmpty()) {
            return null;
        }
        return cardPile.remove(0);
    }

    public boolean hasCards() {
        return !cardPile.isEmpty();
    }

    public int getCardCount() {
        return cardPile.size();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Card peekTopCard() {
        if (!cardPile.isEmpty()) {
            return cardPile.get(0);
        }
        return null;
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ") - Cards: " + cardPile.size();
    }

    public void clearCards() {
        cardPile.clear();
    }
}