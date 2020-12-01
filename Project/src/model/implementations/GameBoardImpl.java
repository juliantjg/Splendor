/**
 * Main class: client/Main.java
 * Class: GameBoardImpl.java
 * Author: Julian Tjiong (2020)
 */

package model.implementations;

import model.data.FillCardsNobles;
import model.interfaces.GameBoard;

import java.util.ArrayList;
import java.util.Random;

public class GameBoardImpl implements GameBoard {
    private ArrayList<CardImpl> dot3_developments, dot2_developments, dot1_developments;
    private ArrayList<NobleImpl> noblesDeck;
    private CardImpl[] dot3, dot2, dot1;
    private FillCardsNobles datas;
    private int[] boardGems;
    private ArrayList<NobleImpl> nobles;
    private int gold;
    private int numOfPlayers;
    private int numOfNobles;

    public GameBoardImpl(int numOfPlayers){
        this.numOfNobles = numOfPlayers+1;
        this.numOfPlayers = numOfPlayers;
        datas = new FillCardsNobles();
        noblesDeck = datas.getNobles();

        if(numOfPlayers==2){
            boardGems = new int[]{4,4,4,4,4};
            nobles = new ArrayList<NobleImpl>();
        }
        else{
            boardGems = new int[]{5,5,5,5,5};
            nobles = new ArrayList<NobleImpl>();
        }
        fillNobles(numOfPlayers);
        gold=5;
        dot3_developments = datas.getDot3Cards();
        dot2_developments = datas.getDot2Cards();
        dot1_developments = datas.getDot1Cards();
        dot3 = new CardImpl[4];
        dot2 = new CardImpl[4];
        dot1 = new CardImpl[4];

        refillDot("a");
        refillDot("b");
        refillDot("c");
    }

    public int getGold(){
        return gold;
    }

    public void takeGold(){
        gold--;
    }

    //Only return the card without taking it from the deck
    public CardImpl getDevelopment(String inputDevCode){
        String code = inputDevCode.substring(0,1);
        int codeNumber = Integer.parseInt(inputDevCode.substring(1,2));
        CardImpl retVal = getCardDeck(code)[codeNumber];

        return retVal;
    }

    //Taking the card from the deck
    public CardImpl takeDevelopment(String inputDevCode){
        String code = inputDevCode.substring(0,1);
        int codeNumber = Integer.parseInt(inputDevCode.substring(1,2));
        CardImpl retVal = getCardDeck(code)[codeNumber];
        getCardDeck(code)[codeNumber] = null;
        refillDot(code);

        return retVal;
    }

    public ArrayList<NobleImpl> getNobles(){
        return nobles;
    }

    public void receiveGemPayment(int[] input, boolean gold){
        for(int i=0;i<5;i++){
            boardGems[i]+=input[i];
        }
        if(gold){
            this.gold++;
        }
    }

    public boolean checkDevelopment(String inputDevCode){
        if(inputDevCode.length()==2) {
            String code = inputDevCode.substring(0, 1);
            String codeNumber = inputDevCode.substring(1, 2);
            if (code.equals("a") || code.equals("b") || code.equals("c")) {
                if (codeNumber.equals("0") || codeNumber.equals("1") || codeNumber.equals("2") || codeNumber.equals("3")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public void takeGems(int[] inputGems){
        for(int i=0;i<5;i++){
            boardGems[i]-=inputGems[i];
        }
    }

    public int[] convertRawInputToArray(String input){
        int[] retVal = new int[]{0,0,0,0,0};
        for(int i=0;i<input.length();i++){
            String substrings = input.substring(i,i+1);
            switch(substrings){
                case "W":
                    retVal[0]++;
                    break;
                case "R":
                    retVal[1]++;
                    break;
                case "G":
                    retVal[2]++;
                    break;
                case "O":
                    retVal[3]++;
                    break;
                case "B":
                    retVal[4]++;
                    break;
            }
        }
        return retVal;
    }

    public int checkInputTakeGems(String input){
        if(input.indexOf('E')==-1) {
            if(input.length()==3 || input.length()==5) {
                if (checkGemChars(input, input.length())) {
                    if (input.length() == 5 && input.substring(1, 2).equals(" ") && input.substring(3, 4).equals(" ")) {
                        if (!input.substring(0, 1).equals(input.substring(2, 3)) && !input.substring(2, 3).equals(input.substring(4, 5)) &&
                                !input.substring(0, 1).equals(input.substring(4, 5))) {
                            if (checkGems(convertRawInputToArray(input))) {
                                //200 means true
                                return 200;
                            } else {
                                //Not enough gems to take
                                return 7;
                            }
                        } else {
                            //Can't take 2 same gems if taking 3 gems
                            return 6;
                        }
                    } else if (input.length() == 3 && input.substring(1, 2).equals(" ")) {
                        if (input.substring(0, 1).equals(input.substring(2, 3))) {
                            if (checkGems(convertRawInputToArray(input))) {
                                //200 means true
                                return 200;
                            } else {
                                //Not enough gems to take / taking 2 gems from a tile with <4 gems
                                return 75;
                            }
                        } else {
                            //Must take 2 same gems if taking 2 gems
                            return 5;
                        }
                    } else {
                        //Input format invalid (spaces problem)
                        return 4;
                    }
                } else {
                    //Invalid gem code (must be W,R,G,O,B)
                    return 3;
                }
            }
            else{
                if(input.length()==1){
                    //Can't take just 1 gem
                    return 22;
                }
                //Invalid format
                return 2;
            }
        }
        else{
            //Can't take gold
            return 1;
        }
    }

    public void printGameBoard(){
        printGameBoardLines();
        System.out.println("G A M E - B O A R D");
        printGameBoardLines();
        System.out.println("");
        printNobles();
        System.out.println();
        printDevelopment();
        System.out.println();
        printGems();
        printGameBoardLines();
        printGameBoardLines();
    }

    /*
    PRIVATE (HELPER) METHODS
     */

    /**
     * Prints the horizontal lines that divide development cards
     */
    private void printGameBoardLines(){
        String lines="";
        for(int i=0;i<131;i++){
            lines+="=";
        }
        System.out.println(lines);
    }

    /**
     * Prints boardGems
     */
    private void printGems(){
        System.out.println("Gems");

        printGemLines();

        System.out.format("%-6s%-6s%-6s%-6s%-6s%-6s", "|  W", "|  R", "|  G", "|  O", "|  B", "|  E");
        System.out.println("|");
        System.out.format("%-6s%-6s%-6s%-6s%-6s%-6s", "|  " + boardGems[0], "|  " + boardGems[1], "|  " +
                boardGems[2], "|  " + boardGems[3], "|  " + boardGems[4], "|  " + gold);
        System.out.println("|");

        printGemLines();
    }

    /**
     * Prints horizontal lines for boardGems
     */
    private void printGemLines(){
        System.out.print("+");
        for(int j=0;j<6;j++) {
            for (int i = 0; i < 5; i++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }

    /**
     * Prints all the development cards
     */
    private void printDevelopment(){
        System.out.println("Developments");
        printDevelopmentLines();
        printDevelopmentCards("a");
        printDevelopmentLines();
        printDevelopmentCards("b");
        printDevelopmentLines();
        printDevelopmentCards("c");
        printDevelopmentLines();
    }

    /**
     * Prints development cards base on given card dot I/II/III
     * @param dot
     */
    private void printDevelopmentCards(String dot){
        String printDots="";
        if(dot.equals("a")){
            printDots = "III DOT";
        }
        else if(dot.equals("b")){
            printDots = "II DOT";
        }
        else{
            printDots = "I DOT";
        }

        System.out.format("%-10s", "| ");
        for (int i = 0; i < 4; i++) {
            System.out.format("%-30s", "| GEM: " + getCardDeck(dot)[i].getGemType());
        }
        System.out.print("|");
        System.out.println();

        System.out.format("%-10s", "| " + printDots);
        for (int i = 0; i < 4; i++) {
            System.out.format("%-30s", "| Prestige: " + getCardDeck(dot)[i].getPrestige());
        }
        System.out.print("|");
        System.out.println();

        System.out.format("%-10s", "| ");
        for (int i = 0; i < 4; i++) {
            System.out.format("%-30s", "| Price: " + getCardDeck(dot)[i].priceString());
        }
        System.out.print("|");
        System.out.println();

        System.out.format("%-10s", "|");
        for (int i = 0; i < 4; i++) {
            System.out.format("%-30s", "| No: " + getCardDeck(dot)[i].getCardNo());
        }
        System.out.print("|");
        System.out.println();
    }

    /**
     * Prints lines between development cards
     */
    private void printDevelopmentLines(){
        System.out.print("+");
        for (int i = 0; i < 9; i++) {
            System.out.print("-");
        }
        System.out.print("+");
        for(int j=0;j<4;j++) {
            for (int i = 0; i < 29; i++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }

    /**
     * Prints noble cards
     */
    private void printNobles() {
        System.out.println("Nobles");
        if(nobles.size()>0) {
            printNobleLines();

            for (int i = 0; i < nobles.size(); i++) {
                System.out.format("%-25s", "| Prestige: " + nobles.get(i).getPrestige());
            }
            System.out.print("|");
            System.out.println();
            for (int i = 0; i < nobles.size(); i++) {
                System.out.format("%-25s", "| Price: " + nobles.get(i).priceString());
            }
            System.out.print("|");
            System.out.println();

            printNobleLines();
        }
        else{
            System.out.println("--No more nobles left--");
        }
    }

    /**
     * Prints lines that separate noble cards (horizontally)
     */
    private void printNobleLines(){
        System.out.print("+");
        for(int j=0;j<nobles.size();j++) {
            for (int i = 0; i < 24; i++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }

    /**
     * Returns a development card DECK based on given code I/II/III
     * @param code
     * @return the arraylist of card DECK
     */
    private ArrayList<CardImpl> getDevDeck(String code){
        if(code.equals("a")){
            return dot3_developments;
        }
        else if(code.equals("b")){
            return dot2_developments;
        }
        else{
            return dot1_developments;
        }
    }

    /**
     * Refills FACEUP development cards given the card dot
     * @param dot
     */
    private void refillDot(String dot){
        Random rand = new Random();

        for(int i=0;i<4;i++){
            if(getCardDeck(dot)[i]==null){
                int randomIndex = rand.nextInt(getDevDeck(dot).size());
                getCardDeck(dot)[i] = getDevDeck(dot).get(randomIndex);
                getDevDeck(dot).remove(randomIndex);
                String newCardNo = dot + i;
                getCardDeck(dot)[i].setCardNo(newCardNo);
            }
        }
    }

    /**
     * Fill the face up nobles in the beginning of the game (Nobles don't get
     * replenished)
     * parameter: numPlayers. 2 players means 3 nobles being shown, 3 players 4 nobles
     */
    private void fillNobles(int numPlayers){
        Random rand = new Random();
        if(numPlayers==2){
            for(int i=0;i<3;i++){
                int randomIndex = rand.nextInt(noblesDeck.size());
                NobleImpl targetNoble = noblesDeck.get(randomIndex);
                noblesDeck.remove(randomIndex);
                String newNo = "n" + i;
                targetNoble.setNo(newNo);
                nobles.add(targetNoble);
            }
        }
        else{
            for(int i=0;i<4;i++){
                int randomIndex = rand.nextInt(noblesDeck.size());
                NobleImpl targetNoble = noblesDeck.get(randomIndex);
                noblesDeck.remove(randomIndex);
                String newNo = "n" + i;
                targetNoble.setNo(newNo);
                nobles.add(targetNoble);
            }
        }
    }

    /**
     * Checks input gem's format (helper method for the larger checkInputTakeGems method
     * @param gemChar input
     * @param length the input's length
     * @return whether or not the format's correct
     */
    private boolean checkGemChars(String gemChar, int length){
        if (checkCharValidity(gemChar.substring(0, 1)) && checkCharValidity(gemChar.substring(2, 3))) {
            if (length == 5) {
                if (checkCharValidity(gemChar.substring(4, 5))) {
                    return true;
                } else {
                    return false;
                }
            }
            else{
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks the gems' validity (if gems are W/R/G/O/B), also a helper method for larger checkInputTakeGems
     * @param gemChar
     * @return whether or not the gems are valid
     */
    private boolean checkCharValidity(String gemChar){
        if(gemChar.equals("W") || gemChar.equals("R") || gemChar.equals("G") || gemChar.equals("O") || gemChar.equals("B")){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Checks whether or not the gems exist in GameBoard / enough to take
     * @param inputGems
     * @return whether or not there are enough gems to take
     */
    private boolean checkGems(int[] inputGems){
        boolean availabilityCheck=true;
        boolean retVal=false;
        int checkIndex=-1;
        for(int i=0;i<5;i++){
            //Check if there are any unavailable gems
            if((boardGems[i] - inputGems[i]) < 0){
                availabilityCheck=false;
            }
            //Check if input is taking 2 same gems
            if(inputGems[i]==2){
                checkIndex=i;
            }
        }
        if(availabilityCheck==true) {
            //If input is taking 2 same gems
            if (checkIndex != -1) {
                int gemsLeft = boardGems[checkIndex] - inputGems[checkIndex];
                if (gemsLeft < 2) {
                    retVal = false;
                } else {
                    retVal = true;
                }
                return retVal;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }

    /**
     * Returns the card deck given the dot I/II/III (represented as a/b/c)
     * @param dot
     * @return CardImpl[] array
     */
    private CardImpl[] getCardDeck(String dot){
        if(dot.equals("a")){
            return dot3;
        }
        else if(dot.equals("b")){
            return dot2;
        }
        else{
            return dot1;
        }
    }
}
