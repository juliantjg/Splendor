/**
 * Main class: client/Main.java
 * Class: Player.java
 * Author: Julian Tjiong (2020)
 */

package model.interfaces;

import model.implementations.PlayerDeckImpl;

public interface Player {
    /**
     * Returns player's name
     * @return
     */
    public String getName();

    /**
     * Returns player's number
     * @return player no
     */
    public int getPlayerNo();

    /**
     * Returns player's player deck
     * @return player deck
     */
    public PlayerDeckImpl getPlayerDeck();

    /**
     * Sets player's player deck
     * @param playerDeck
     */
    public void setPlayerDeck(PlayerDeckImpl playerDeck);
}
