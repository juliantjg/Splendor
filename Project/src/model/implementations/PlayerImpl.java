/**
 * Main class: client/Main.java
 * Class: PlayerImpl.java
 * Author: Julian Tjiong (2020)
 */

package model.implementations;

import model.implementations.PlayerDeckImpl;
import model.interfaces.Player;

public class PlayerImpl implements Player {
    private String name;
    private int playerNo;
    private PlayerDeckImpl playerDeck;

    public PlayerImpl(int no, String name){
        this.playerNo = no;
        this.name = name;
        playerDeck = new PlayerDeckImpl(name);
    }

    public String getName() {
        return name;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public PlayerDeckImpl getPlayerDeck() {
        return playerDeck;
    }

    public void setPlayerDeck(PlayerDeckImpl playerDeck) {
        this.playerDeck = playerDeck;
    }
}
