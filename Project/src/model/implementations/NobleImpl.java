/**
 * Main class: client/Main.java
 * Class: NobleImpl.java
 * Author: Julian Tjiong (2020)
 */

package model.implementations;

import model.interfaces.Noble;

public class NobleImpl implements Noble {
    private String no;
    private int prestige;
    private int[] price;

    public NobleImpl(int prestige, int[] price){
        this.prestige=prestige;
        this.price=price;
    }

    public String priceString(){
        String output="";
        for(int i=0;i<5;i++){
            if(price[i]>0){
                if(i==0){
                    output+=" W:" + price[i];
                }
                else if(i==1){
                    output+=" R:" + price[i];
                }
                else if(i==2){
                    output+=" G:" + price[i];
                }
                else if(i==3){
                    output+=" O:" + price[i];
                }
                else{
                    output+=" B:" + price[i];
                }
            }
        }
        return output;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public int[] getPrice() {
        return price;
    }

    public void setPrice(int[] price) {
        this.price = price;
    }
}
