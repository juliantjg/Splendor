/**
 * Main class: client/Main.java
 * Class: Card.java
 * Author: Julian Tjiong (2020)
 */

package model.interfaces;

public interface Card {
    /**
     * Prints (string) development card for private PlayerDeck
     * @return development card with prestige and permanent gem
     */
    public String printStored();

    /**
     * Prints (string) development card for reserves
     * @return development card from printStored() with price
     */
    public String printWithPrice();

    /**
     * Prints (string) out card's price
     * @return card's price with format (ex: W:2  R:3)
     */
    public String priceString();

    /**
     * Sets card's number
     * @param no
     */
    public void setCardNo(String no);

    /**
     * Returns a card's gem type (permanent gem)
     * @return gem type
     */
    public char getGemType();

    /**
     * Returns a card's number
     * @return
     */
    public String getCardNo();

    /**
     * Returns a card's prestige
     * @return prestige
     */
    public int getPrestige();

    /**
     * Returns card's price
     * @return array of price
     */
    public int[] getPrice();
}
