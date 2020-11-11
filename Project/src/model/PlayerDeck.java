package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerDeck {
    private ArrayList<Card> developments;
    private ArrayList<Noble> nobles;
    private int[] permanentGems;
    private int[] handGems;
    private int[] totalGems;
    private int prestige;
    private int gold;
    private String name;

    public PlayerDeck(String name){
        this.name = name;
        developments = new ArrayList<Card>();
        nobles = new ArrayList<Noble>();
        handGems = new int[]{0,0,0,0,0};
        permanentGems = new int[]{0,0,0,0,0};
        totalGems = new int[]{0,0,0,0,0};
        gold = 0;
        prestige = 0;
    }

    /*
    Functionalities
     */

    public boolean checkNoble(Noble noble){
        int[] noblePrice = noble.getPrice();

        int checkSufficient=1;
        for(int i=0;i<5;i++){
            int diff = permanentGems[i] - noblePrice[i];
            if(diff<0){
                checkSufficient=-1;
            }
        }
        if(checkSufficient==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public int checkDevelopment(Card developement){
        int[] devPrice = developement.getPrice();
        int[] totalGemsCounted = this.getTotalGems();

        int checkSufficient=0;
        for(int i=0;i<5;i++){
            int diff = totalGemsCounted[i] - devPrice[i];
            if(diff<0){
                checkSufficient--;
            }
        }
        return checkSufficient;
    }

    private String printGems(String storageType){
        String retVal="";
        if(storageType.equals("permanent")){
            retVal = gemsToString(permanentGems);
        }
        else if(storageType.equals("hand")){
            retVal = gemsToString(handGems);
        }
        else{
            int[] total = this.getTotalGems();
            retVal = gemsToString(total);
        }
        return retVal;
    }

    private String gemsToString(int[] storageType){
        String retVal="";
        for(int i=0;i<5;i++){
            if(storageType[i]>0){
                if(i==0){
                    retVal+=" W:" + storageType[i];
                }
                else if(i==1){
                    retVal+=" R:" + storageType[i];
                }
                else if(i==2){
                    retVal+=" G:" + storageType[i];
                }
                else if(i==3){
                    retVal+=" O:" + storageType[i];
                }
                else{
                    retVal+=" B:" + storageType[i];
                }
            }
        }
        return retVal;
    }

    public void printPublicDeck(){
        System.out.println("Player: " + name + "  ----PRESTIGE:" + prestige + "----" +
                "\n  Num. of developments: " + developments.size() +
                "\n  Num. of nobles: " + nobles.size() +
                "\n  Permanent gems: " + printGems("permanent") +
                "\n  Gems on hand: " + printGems("hand") +
                "\n  Total gems: " + printGems("total"));
    }

    private String noblesToString(){
        String output="";

        for(int i=0;i<nobles.size();i++){
            int count=i+1;
            output+="     Noble " + count + " (Prestige: " + nobles.get(i).getPrestige() + ")\n";
        }
        return output;
    }

    private String developmentsToString(){
        String output="";
        for(int i=0;i<developments.size();i++){
            int count=i+1;
            output+="     Dev " + count + " " + developments.get(i).printStored() + "\n";
        }
        return output;
    }

    public void printPersonalDeck(){

        System.out.println("Player: " + name + "  ----PRESTIGE:" + prestige + "----" +
                "\n  Nobles:" +
                "\n" + noblesToString() +

                "\n  Developments:" +
                "\n" + developmentsToString() +

                "\n  Permanent Gems: " + printGems("permanent") +
                "\n  Gems on hand: " + printGems("hand") +
                "\n  Total Gems: " + printGems("total")
        );
    }

    public void addDevelopment(Card dev){
        developments.add(dev);
        prestige+=dev.getPrestige();

        //Check the gem type
        int gemType;
        if(dev.getGemType()=='W'){
            gemType=0;
        }
        else if(dev.getGemType()=='R'){
            gemType=1;
        }
        else if(dev.getGemType()=='G'){
            gemType=2;
        }
        else if(dev.getGemType()=='O'){
            gemType=3;
        }
        else{
            gemType=4;
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

    public int[] getHandGems() {
        return handGems;
    }

    public int getPrestige() {
        return prestige;
    }

}
