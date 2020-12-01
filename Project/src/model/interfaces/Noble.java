/**
 * Main class: client/Main.java
 * Class: Noble.java
 * Author: Julian Tjiong (2020)
 */

package model.interfaces;

public interface Noble {
    /**
     * Returns the noble card's price with format
     * @return string of card's price
     */
    public String priceString();

    /**
     * Returns the noble card's number
     * @return no
     */
    public String getNo();

    /**
     * Sets the noble card's number
     * @param no
     */
    public void setNo(String no);

    /**
     * Returns the noble card's prestige
     * @return prestige
     */
    public int getPrestige();

    /**
     * Sets the noble card's prestige
     * @param prestige
     */
    public void setPrestige(int prestige);

    /**
     * Returns the noble card's price (array format)
     * @return noble's price
     */
    public int[] getPrice();

    /**
     * Sets the noble card's price
     * @param price (array format)
     */
    public void setPrice(int[] price);
}
