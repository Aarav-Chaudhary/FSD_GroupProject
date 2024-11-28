/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author aarav, zaid, bushra, jaspuneet 
 */
public class GroupOfCards {

    private ArrayList<Card> cards;
    private int size;//the size of the grouping

    public GroupOfCards(int size) {
        this.cards = new ArrayList<>(size);
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        
        for (String suit : suits) 
        {
            for (String rank : ranks) 
            {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

}//end class

