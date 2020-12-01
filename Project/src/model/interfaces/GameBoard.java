/**
 * Main class: client/Main.java
 * Class: GameBoard.java
 * Author: Julian Tjiong (2020)
 */

package model.interfaces;

import model.implementations.CardImpl;

public interface GameBoard {

    /**
     * Returns the number of golds in the GameBoard
     * @return GameBoard's gold
     */
    public int getGold();

    /**
     * Calling this method will reduce GameBoard's gold by 1
     */
    public void takeGold();

    /**
     * Returns a development card given a card's number/code
     * @param Card's no/code
     * @return the Card with the particular card no/code
     */
    public CardImpl getDevelopment(String inputDevCode);

    /**
     * This method takes a selected card from the GameBoard, removing it
     * from the GameBoard and returns it
     * @param inputDevCode (Card's no)
     * @return the Card itself
     */
    public CardImpl takeDevelopment(String inputDevCode);

    /**
     * Receives array of gems input, and boolean gold to check whether
     * or not the payment includes gold. Adds the input into GameBoard's
     * boardGems, and increments gold if payment includes gold
     * @param input array
     * @param gold (yes/no)
     */
    public void receiveGemPayment(int[] input, boolean gold);

    /**
     * Checks whether or not the development code given exist (checks the
     * input format as well)
     * @param inputDevCode
     * @return true/false
     */
    public boolean checkDevelopment(String inputDevCode);

    /**
     * Subtract boardGems with given inputGems
     * @param inputGems
     */
    public void takeGems(int[] inputGems);

    /**
     * Converts raw take input for instance take W O B into a processable
     * format for instance {1,0,1,0,1}. Set as a public method for use
     * in GameEngine
     * @param input ex: (W O B)
     * @return it's array form ex: {1,0,1,0,1}
     */
    public int[] convertRawInputToArray(String input);

    /**
     * Checks input of takeGems whether or not it is valid/correct format
     * @param input
     * @return error code for error message printed in GameEngine
     */
    public int checkInputTakeGems(String input);

    /**
     * Prints GameBoard with format written in ProjectNotes
     */
    public void printGameBoard();
}
