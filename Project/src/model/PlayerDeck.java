package model;

import java.util.ArrayList;

public class PlayerDeck {
    ArrayList<Card> developments;
    ArrayList<Noble> nobles;
    int[] permanentGems;
    int[] handGems;
    int[] totalGems;
    int prestige;
    int gold;
    String name;

    public PlayerDeck(String name){
        handGems = new int[]{0,0,0,0,0};
        permanentGems = new int[]{0,0,0,0,0};
        gold = 0;
        prestige = 0;
    }

    /*
    Functionalities
     */

    private String printGems(String storageType){
        if(storageType.equals("permanent")){
            return "W:" + permanentGems[0] + ",R:" + permanentGems[1] + ", G:" + permanentGems[2] +
                    ", O:" + permanentGems[3] + ", B:" + permanentGems[4];
        }
        else if(storageType.equals("private")){
            return "W:" + handGems[0] + ",R:" + handGems[1] + ", G:" + handGems[2] +
                    ", O:" + handGems[3] + ", B:" + handGems[4] + ", E:" + gold;
        }
        else{
            int[] total = this.getTotalGems();
            return "W:" + total[0] + ",R:" + total[1] + ", G:" + total[2] +
                    ", O:" + total[3] + ", B:" + total[4] + ", E:" + gold;
        }
    }

    public void printPublicDeck(){
        System.out.println("Player: " + name +
                "\n Num. of developments: " + developments.size() +
                "\n Num of nobles: " + nobles.size() +
                "\n Total prestige: " + prestige +
                "\n Permanent gems: " + printGems("permanent") +
                "\n Gems on hand: " + printGems("private") +
                "\n Total gems: " + printGems("total"));
    }

    public void printPersonalDeck(){

    }

    public void addDevelopment(Card dev){
        developments.add(dev);
        prestige+=dev.getPrestige();

        //Check the gem type
        int gemType;
        if(dev.getGemType()=='W'){
            gemType=1;
        }
        else if(dev.getGemType()=='R'){
            gemType=2;
        }
        else if(dev.getGemType()=='G'){
            gemType=3;
        }
        else if(dev.getGemType()=='O'){
            gemType=4;
        }
        else{
            gemType=5;
        }
        //Increments permanent gems
        permanentGems[gemType]++;
    }

    public void addNoble(Noble noble){
        nobles.add(noble);

        //Increments prestige
        prestige+=noble.getPrestige();
    }

    public void addHandGems(int[] gems){
        for(int i=0;i<5;i++){
            handGems[i]+=gems[i];
        }
    }

    /*
    Getters and Setters
     */

    public int getGold() {
        return gold;
    }

    public void addGold() {
        gold++;
    }

    public void takeGold(){
        gold--;
    }

    public int[] getTotalGems(){
        int retVal[] = {0,0,0,0,0};
        for(int i=0;i<5;i++){
            retVal[i] = handGems[i] + permanentGems[i];
        }
        return retVal;
    }

    public ArrayList<Card> getDevelopments() {
        return developments;
    }

    public ArrayList<Noble> getNobles() {
        return nobles;
    }

    public int[] getPermanentGems() {
        return permanentGems;
    }

    public void setPermanentGems(int[] permanentGems) {
        this.permanentGems = permanentGems;
    }

    public int[] getHandGems() {
        return handGems;
    }

    public void setTotalGems(int[] totalGems) {
        this.totalGems = totalGems;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }
}
