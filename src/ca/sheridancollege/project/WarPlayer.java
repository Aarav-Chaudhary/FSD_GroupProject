/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author aarav, zaid, bushra, jaspuneet 
 */
public class WarPlayer extends Player {
    private ArrayList<Card> handCards;

    public WarPlayer(String playerName) {
        super(playerName);
        this.handCards = new ArrayList<>();
    }

    public void addCardToHand(Card card) {
        handCards.add(card);
    }

    public Card play() {
        if (!handCards.isEmpty()) {
            return handCards.remove(0);
        }
        return null;
    }

    public boolean isHandEmpty() {
        return handCards.isEmpty();
    }

    public int getHandSize() {
        return handCards.size();
    }

}
