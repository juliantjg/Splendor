/**
 * Main class: client/Main.java
 * Class: PlayerDeck.java
 * Author: Julian Tjiong (2020)
 */

package model.interfaces;

import model.implementations.CardImpl;
import model.implementations.NobleImpl;

import java.util.ArrayList;

public interface PlayerDeck {
    /**
     * Checks whether or not player can take the given gems
     * (player can't have more than 10 hand gems at a time)
     * @param gems
     * @return whether or not gems can be taken
     */
    public boolean checkGems(int[] gems);

    /**
     * Checks whether or not player is eligible for a noble's
     * visit (if they have sufficient permanent gems)
     * @param noble
     * @return whether or not player is eligible for noble visit
     */
    public boolean checkNoble(NobleImpl noble);

    /**
     * Reserve a card for player. If given gold (GameBoard's
     * gold>0) then adds player's gold
     * @param inputCard
     * @param gold
     */
    public void reserve(CardImpl inputCard, int gold);

    /**
     * Checks if player can reserve a new card (if they're
     * not already reserving more than 2 cards)
     * @return 1 means false, 2 means true
     */
    public int checkReserve();

    /**
     * Checks if a player is able to buy a development
     * (returns the number of dev.price - total gems, for future
     * use of gold gem if player needs 1 more gem and they
     * do have a gold gem)
     * @param development
     * @return the substracted amount of dev.price - total gem
     */
    public int checkDevelopment(CardImpl development);

    /**
     * Prints player's personal deck
     */
    public void printPersonalDeck();

    /**
     * Adds the development card to player's deck, adds player's
     * permanent gems based on the card's provided permanent gem,
     * also finally return the payment (to pay GameBoard). For the
     * payment, the method will first check if gold is ever used,
     * if true then subtract the payment gems by 1 (use gold). Return
     * the payment.
     * @param dev
     * @return payment for GameBoard
     */
    public int[] addDevelopment(CardImpl dev);

    /**
     * Adds a noble card to player's deck
     * @param noble
     */
    public void addNoble(NobleImpl noble);

    /**
     * Adds player deck's hand gems by given gems
     * @param gems
     */
    public void addHandGems(int[] gems);

    /**
     * Prints player's public player deck (format can be seen
     * on documentation file)
     */
    public void printPublicDeck();

    /**
     * Buys selected reserve from player deck
     * @param input
     * @return the payment for GameBoard (from addDevelopment())
     */
    public int[] buyReserve(String input);

    /**
     * Checks if player can buy a reserve card
     * @param input
     * @return number of subtracted gems amount (if -1 means
     * gold can be used)
     */
    public int checkBuyReserve(String input);

    /**
     * Returns player deck's gold
     * @return gold
     */
    public int getGold();

    /**
     * Adds player deck's gold by 1
     */
    public void addGold();

    /**
     * Subtract player deck's gold by 1
     */
    public void takeGold();

    /**
     * Returns player deck's total gems (hand gems + permanent gems)
     * @return
     */
    public int[] getTotalGems();

    /**
     * Returns player deck's developments
     * @return
     */
    public ArrayList<CardImpl> getDevelopments();

    /**
     * Returns player deck's nobles
     * @return
     */
    public ArrayList<NobleImpl> getNobles();

    /**
     * Sets player deck's permanent gems
     * @param permGem
     */
    public void setPermanentGems(int[] permGem);

    /**
     * Returns player deck's permanent gems
     * @return
     */
    public int[] getPermanentGems();

    /**
     * Returns player deck's hand gems
     * @return
     */
    public int[] getHandGems();

    /**
     * Returns player deck's prestige points
     * @return
     */
    public int getPrestige();

    /**
     * Sets player deck's prestige points
     * @param prestige
     */
    public void setPrestige(int prestige);
}

