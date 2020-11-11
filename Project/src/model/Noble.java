package model;

public class Noble {
    private int no;
    private int prestige;
    private int[] price;

    public Noble(int no, int prestige, int[] price){
        this.no=no;
        this.prestige=prestige;
        this.price=price;
    }

    public String printSell(){
        return "Prestige: " + prestige +
                "\nPrice: " + priceString() +
                "\nNo: " + no;
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

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
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
