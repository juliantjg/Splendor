package model;

public class Card {
    private int cardNo;
    private int prestige;
    private int[] price;
    private char gemType;

    public Card(int cardNo, int prestige, int[] price, char gemType){
        this.cardNo=cardNo;
        this.prestige=prestige;
        this.price=price;
        this.gemType=gemType;
    }

    public String printSell(){
        return "";
    }

    public String printStored(){
        return "(Gem:" + gemType + ", Prestige:" + prestige + ")";
    }

    private String priceString(){
        String output="";
        for(int i=0;i<5;i++){
            if(price[i]>0){
                if(i==0){
                    output+=" W:" + price[i] + ",";
                }
                else if(i==1){
                    output+=" R:" + price[i] + ",";
                }
                else if(i==2){
                    output+=" G:" + price[i] + ",";
                }
                else if(i==3){
                    output+=" O:" + price[i] + ",";
                }
                else{
                    output+=" B:" + price[i] + ",";
                }
            }
        }
        return output;
    }

    public char getGemType() {
        return gemType;
    }

    public int getCardNo() {
        return cardNo;
    }

    public int getPrestige() {
        return prestige;
    }

    public int[] getPrice() {
        return price;
    }

}
