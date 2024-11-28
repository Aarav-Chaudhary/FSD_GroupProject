/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author aarav, zaid, bushra, jaspuneet 
 */
public class WarGame extends Game {
    private GroupOfCards cardDeck;
    private static final int MAXIMUM_ROUNDS = 100;

    public WarGame(String gameName) {
        super(gameName);
        this.cardDeck = new GroupOfCards(52);
    }

    @Override
    public void play() {
        cardDeck.shuffle();

        ArrayList<Card> cardPile = cardDeck.getCards();
        ArrayList<Player> participants = getPlayers();

        // Distribute cards evenly among players
        for (int i = 0; i < cardPile.size(); i++) {
            WarPlayer currentPlayer = (WarPlayer) participants.get(i % participants.size());
            currentPlayer.addCardToHand(cardPile.get(i));
        }

        int roundNumber = 0;
        while (roundNumber < MAXIMUM_ROUNDS && participants.size() > 2) {
            System.out.println("Starting Round " + (roundNumber + 1));

            ArrayList<Card> roundCards = new ArrayList<>();
            for (Player participant : participants) {
                roundCards.add(((WarPlayer) participant).play());
            }

            for (int i = 0; i < participants.size(); i++) {
                System.out.println(participants.get(i).getPlayerID() + " plays " + roundCards.get(i));
            }

            int strongestCardIndex = 0;
            boolean tieOccurred = false;

            for (int i = 1; i < roundCards.size(); i++) {
                int comparison = evaluateCards(roundCards.get(strongestCardIndex), roundCards.get(i));
                if (comparison < 0) {
                    strongestCardIndex = i;
                    tieOccurred = false;
                } else if (comparison == 0) {
                    tieOccurred = true;
                }
            }

            if (tieOccurred) {
                System.out.println("It's a WAR!");
                for (Card card : roundCards) {
                    if (card != null) {
                        ((WarPlayer) participants.get(strongestCardIndex)).addCardToHand(card);
                    }
                }
                System.out.println(participants.get(strongestCardIndex).getPlayerID() + " wins this tie round.");
            } else {
                for (Card card : roundCards) {
                    if (card != null) {
                        ((WarPlayer) participants.get(strongestCardIndex)).addCardToHand(card);
                    }
                }
                System.out.println(participants.get(strongestCardIndex).getPlayerID() + " wins this round.");
            }

            // Remove players who are out of cards
            ArrayList<Player> outPlayers = new ArrayList<>();
            for (Player participant : participants) {
                if (((WarPlayer) participant).isHandEmpty()) {
                    outPlayers.add(participant);
                }
            }

            for (Player outPlayer : outPlayers) {
                System.out.println(outPlayer.getPlayerID() + " is eliminated.");
                participants.remove(outPlayer);
            }

            roundNumber++;
            System.out.println();
        }

        declareWinner();
    }

    @Override
    public void declareWinner() {
        ArrayList<Player> participants = getPlayers();
        System.out.println("Game Over! Final Standings:");

        for (Player participant : participants) {
            WarPlayer player = (WarPlayer) participant;
            System.out.println(player.getPlayerID() + " has " + player.getHandSize() + " cards remaining.");
        }

        int maxCards = participants.stream()
            .mapToInt(player -> ((WarPlayer) player).getHandSize())
            .max()
            .orElse(0);

        ArrayList<Player> victors = participants.stream()
            .filter(player -> ((WarPlayer) player).getHandSize() == maxCards)
            .collect(Collectors.toCollection(ArrayList::new));

        if (victors.size() > 1) {
            System.out.println("The game ends in a draw between:");
            for (Player player : victors) {
                System.out.println(player.getPlayerID());
            }
        } else {
            System.out.println("Congratulations to " + victors.get(0).getPlayerID() + " for winning the game!");
        }
    }

    public int evaluateCards(Card firstCard, Card secondCard) {
        if (firstCard == null || secondCard == null) return 0;

        Map<String, Integer> rankValues = new HashMap<>();
        rankValues.put("2", 2);
        rankValues.put("3", 3);
        rankValues.put("4", 4);
        rankValues.put("5", 5);
        rankValues.put("6", 6);
        rankValues.put("7", 7);
        rankValues.put("8", 8);
        rankValues.put("9", 9);
        rankValues.put("10", 10);
        rankValues.put("Jack", 11);
        rankValues.put("Queen", 12);
        rankValues.put("King", 13);
        rankValues.put("Ace", 14);

        return Integer.compare(rankValues.get(firstCard.getRank()), rankValues.get(secondCard.getRank()));
    }

    public static void main(String[] args) {
        WarGame warGame = new WarGame("War Card Game");
        warGame.getPlayers().add(new WarPlayer("Alex"));
        warGame.getPlayers().add(new WarPlayer("Jordan"));
        warGame.getPlayers().add(new WarPlayer("Taylor"));
        warGame.getPlayers().add(new WarPlayer("Morgan"));

        warGame.play();
    }
}
