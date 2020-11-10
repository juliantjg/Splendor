package model;

public class Card {
    int cardNo;
    int prestige;
    int[] price;
    char gemType;

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
