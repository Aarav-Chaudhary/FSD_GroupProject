/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author aarav, zaid, bushra, jaspuneet 
 */
public abstract class Game {

    private final String gameName;
    private ArrayList<Player> players;

    public Game(String givenName) {
        this.gameName = givenName;
        this.players = new ArrayList<>();
    }

    public String getGameName() {
        return gameName;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public abstract void play();

    public abstract void declareWinner();

}//end class
