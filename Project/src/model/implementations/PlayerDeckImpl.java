/**
 * Main class: client/Main.java
 * Class: PlayerDeckImpl.java
 * Author: Julian Tjiong (2020)
 */

package model.implementations;

import model.implementations.CardImpl;
import model.implementations.NobleImpl;
import model.interfaces.PlayerDeck;

import java.util.ArrayList;

public class PlayerDeckImpl implements PlayerDeck {
    private ArrayList<CardImpl> developments;
    private ArrayList<CardImpl> reserves;
    private ArrayList<NobleImpl> nobles;
    private int[] permanentGems;
    private int[] handGems;
    private int[] totalGems;
    private int prestige;
    private int gold;
    private String name;

    public PlayerDeckImpl(String name){
        this.name = name;
        developments = new ArrayList<CardImpl>();
        reserves = new ArrayList<CardImpl>();
        nobles = new ArrayList<NobleImpl>();
        handGems = new int[]{0,0,0,0,0};
        permanentGems = new int[]{0,0,0,0,0};
        totalGems = new int[]{0,0,0,0,0};
        gold = 0;
        prestige = 0;
    }

    public boolean checkGems(int[] gems){
        int totalHandGems = this.getNumberOfGems(handGems);
        int totalInputGems = this.getNumberOfGems(gems);

        int newTotalHandGems = totalHandGems+totalInputGems;
        if(newTotalHandGems>10){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkNoble(NobleImpl noble){
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

    public void reserve(CardImpl inputCard, int gold){
        reserves.add(inputCard);
        //If gameBoard have more than 0 gold
        if(gold>0){
            this.gold++;
        }
    }

    public int checkReserve(){
        if(reserves.size()>2){
            //Can't have more than 3 reserves
            return 1;
        }
        else{
            //True
            return 2;
        }
    }

    public int checkDevelopment(CardImpl development){
        int[] devPrice = development.getPrice();
        int[] totalGemsCounted = this.getTotalGems();

        int checkSufficient=0;
        for(int i=0;i<5;i++){
            int diff = totalGemsCounted[i] - devPrice[i];
            if(diff<0){
                checkSufficient+=diff;
            }
        }
        return checkSufficient;
    }

    public void printPersonalDeck(){
        System.out.println("-PLAYER " + name.toUpperCase() + "'S TURN-    [Scroll up to see opponents' player deck]");
        System.out.println(personalDeckLines());

        System.out.format("%-25s", "[");
        System.out.format("%-45s", ">>>> PRESTIGE:" + prestige + " <<<<");
        printCloseBracket();

        System.out.format("%-70s", "[  Nobles:");
        printCloseBracket();
        noblesToString();
        System.out.format("%-70s", "[");
        printCloseBracket();

        System.out.format("%-70s", "[  Developments:");
        printCloseBracket();
        developmentsToString();
        System.out.format("%-70s", "[");
        printCloseBracket();

        System.out.format("%-70s", "[  Reserves:");
        printCloseBracket();
        reservesToString();
        System.out.format("%-70s", "[");
        printCloseBracket();

        System.out.format("%-70s", "[  Permanent Gems: " + printGems("permanent"));
        printCloseBracket();
        System.out.format("%-70s", "[  Gems on hand: " + printGems("hand") + "  (Total:" + getTotalGemCount(handGems) + ")");
        printCloseBracket();
        System.out.format("%-70s", "[  Total Gems: " + printGems("total"));
        printCloseBracket();
        if(this.gold>0){
            System.out.format("%-70s", "[  GOLD (E): "+ this.gold);
            printCloseBracket();
        }
        System.out.println(personalDeckLines());
    }

    public int[] addDevelopment(CardImpl dev){
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
        //Subtract the card's price with the player's permanentGems first
        int[] subtractedByPermanent = subtractArrayBuyDev(dev.getPrice(), permanentGems);
        //And then we subtract player's handGems with the previous subtracted one, so the permanent gems
        //act as discounts
        int indexGold = -1;
        for(int i=0;i<5;i++){
            handGems[i] -= subtractedByPermanent[i];
            //If handGem is -1 then increment it by one (gold wild card). Due to previous checkDevelopment
            //we knew that there will only be one -1 that is why we can simply add +1 to the handGems index.
            if(handGems[i]==-1){
                handGems[i]++;
                indexGold=i;
            }
        }
        //Increments permanent gems
        permanentGems[gemType]++;

        //If gold is indeed in use, find the gold index and reduce it by 1
        if(indexGold!=-1){
            subtractedByPermanent[indexGold]--;
        }
        //Return the payment for gameBoard
        return subtractedByPermanent;
    }

    public void addNoble(NobleImpl noble){
        nobles.add(noble);

        //Increments prestige
        prestige+=noble.getPrestige();
    }

    public void addHandGems(int[] gems){
        for(int i=0;i<5;i++){
            handGems[i]+=gems[i];
        }
    }

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

    public ArrayList<CardImpl> getDevelopments() {
        return developments;
    }

    public ArrayList<NobleImpl> getNobles() {
        return nobles;
    }

    public void setPermanentGems(int[] permGem){
        this.permanentGems = permGem;
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

    public void setPrestige(int prestige){
        this.prestige = prestige;
    }

    public void printPublicDeck(){
        System.out.println("PLAYER " + this.name.toUpperCase() + "'S DECK");
        System.out.println(publicDeckLines());
        System.out.format("%-12s", "[");
        System.out.format("%-32s", ">>>> PRESTIGE:" + prestige + " <<<<");
        printCloseBracket();
        System.out.format("%-44s", "[  Num. of developments: " + developments.size());
        printCloseBracket();
        System.out.format("%-44s", "[  Num. of nobles: " + nobles.size());
        printCloseBracket();
        System.out.format("%-44s", "[  Permanent gems: " + printGems("permanent"));
        printCloseBracket();
        System.out.format("%-44s", "[  Gems on hand: " + printGems("hand"));
        printCloseBracket();
        System.out.format("%-44s", "[  Total gems: " + printGems("total"));
        printCloseBracket();
        if(gold>0) {
            System.out.format("%-44s", "[  GOLD (E): " + this.gold);
            printCloseBracket();
        }
        if(reserves.size()>0) {
            System.out.format("%-44s", "[");
            printCloseBracket();
            System.out.format("%-44s", "[  -> This player is reserving " + reserves.size() + " card(s) <-");
            printCloseBracket();
        }
        System.out.print(publicDeckLines());
    }

    public int[] buyReserve(String input){
        int inputNum = Integer.parseInt(input) - 1;
        int[] retVal = addDevelopment(reserves.get(inputNum));
        reserves.remove(inputNum);
        //Return the payment for gameBoard
        return retVal;
    }

    public int checkBuyReserve(String input){
        if(input.equals("1") || input.equals("2") || input.equals("3")) {
            int inputNum = Integer.parseInt(input) - 1;

            if (inputNum < reserves.size()) {
                //Return checkDevelopment() to enable using gold
                return checkDevelopment(reserves.get(inputNum));
            } else {
                //False
                return -33;
            }
        }
        else{
            return -55;
        }
    }

    /*
    PRIVATE (HELPER METHODS)
     */

    /**
     * Subtract main array by subtractBy, only reduce indexes with values>0
     * @param main
     * @param subtractBy
     * @return subtracted result
     */
    private int[] subtractArrayBuyDev(int[] main, int[] subtractBy){
        int[] retVal = {0,0,0,0,0};

        for(int i=0;i<5;i++){
            if(main[i]>0){
                retVal[i] = main[i] - subtractBy[i];
                if(retVal[i]<0){
                    retVal[i]=0;
                }
            }
        }
        return retVal;
    }

    /**
     * Returns number of gems given a set of gems
     * @param gems
     * @return
     */
    private int getTotalGemCount(int[] gems){
        int retVal=0;
        for(int i=0;i<5;i++){
            retVal+=gems[i];
        }
        return retVal;
    }

    /**
     * Returns string of gems for printing given storageType (permanent/hand/total)
     * @param storageType
     * @return string of selected gems
     */
    private String printGems(String storageType){
        if(storageType.equals("permanent")){
            return gemsToString(permanentGems);
        }
        else if(storageType.equals("hand")){
            return gemsToString(handGems);
        }
        else{
            int[] total = this.getTotalGems();
            return gemsToString(total);
        }
    }

    /**
     * Returns string form of gems with format (can be seen on doc)
     * @param storageType
     * @return string form of gems
     */
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

    /**
     * Prints close bracket
     */
    private void printCloseBracket(){
        System.out.print("]");
        System.out.println();
    }

    /**
     * Prints the line on public player deck
     * @return
     */
    private String publicDeckLines(){
        String retVal="";
        for(int i=0;i<45;i++){
            retVal+="-";
        }
        return retVal;
    }

    /**
     * Prints nobles on private player deck
     */
    private void noblesToString(){
        if(nobles.size()>0) {
            String output = "";
            for (int i = 0; i < nobles.size(); i++) {
                int count = i + 1;
                output = "[     Noble " + count + " (Prestige: " + nobles.get(i).getPrestige() + ")";
                System.out.format("%-70s", output);
                printCloseBracket();
            }
        }
    }

    /**
     * Prints developments on private player deck
     */
    private void developmentsToString(){
        if(developments.size()>0) {
            String output = "";
            for (int i = 0; i < developments.size(); i++) {
                int count = i + 1;
                output = "[     Dev " + count + " " + developments.get(i).printStored();
                System.out.format("%-70s", output);
                printCloseBracket();
            }
        }
    }

    /**
     * Returns number of gems
     * @param gemsInput
     * @return total gems
     */
    private int getNumberOfGems(int[] gemsInput){
        int total=0;
        for(int i=0;i<5;i++){
            total+=gemsInput[i];
        }
        return total;
    }

    /**
     * Prints reserves on private player deck
     */
    private void reservesToString(){
        if(reserves.size()>0) {
            String output = "";
            for (int i = 0; i < reserves.size(); i++) {
                int count = i + 1;
                output = "[     Res " + count + " " + reserves.get(i).printWithPrice();
                System.out.format("%-70s", output);
                printCloseBracket();
            }
        }
    }

    /**
     * Prints the line on private player deck
     * @return
     */
    private String personalDeckLines(){
        String retVal = "";
        for(int i=0;i<71;i++){
            retVal+="-";
        }
        return retVal;
    }
}
