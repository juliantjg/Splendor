package model;

public class Card {
    int cardNo;
    int prestige;
    int[] price;
    char gemType;

    public String printSell(){
        return "";
    }

    public String printStored(){
        return "(Gems: " + priceString() + ", Prestige:" + prestige + ")";
    }

    private String priceString(){
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

    public char getGemType() {
        return gemType;
    }

    public void setGemType(char gemType) {
        this.gemType = gemType;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
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
