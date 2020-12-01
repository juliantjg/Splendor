/**
 * Main class: client/Main.java
 * Class: CardImpl.java
 * Author: Julian Tjiong (2020)
 */

package model.implementations;

import model.interfaces.Card;

public class CardImpl implements Card {
    private String cardNo;
    private int prestige;
    private int[] price;
    private char gemType;

    public CardImpl(int prestige, int[] price, char gemType){
        this.prestige=prestige;
        this.price=price;
        this.gemType=gemType;
    }

    public String printStored(){
        return "(Gem:" + gemType + ", Prestige:" + prestige + ")";
    }

    public String printWithPrice() {
        return printStored() + "    -> PRICE:" + priceString() + "<-";
    }

    public String priceString(){
        String output="";
        for(int i=0;i<5;i++){
            if(price[i]>0){
                if(i==0){
                    output+=" W:" + price[i] + " ";
                }
                else if(i==1){
                    output+=" R:" + price[i] + " ";
                }
                else if(i==2){
                    output+=" G:" + price[i] + " ";
                }
                else if(i==3){
                    output+=" O:" + price[i] + " ";
                }
                else{
                    output+=" B:" + price[i] + " ";
                }
            }
        }
        return output;
    }

    public void setCardNo(String no){
        this.cardNo = no;
    }

    public char getGemType() {
        return gemType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public int getPrestige() {
        return prestige;
    }

    public int[] getPrice() {
        return price;
    }

}
