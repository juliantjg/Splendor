package model;

import model.data.FillCardsNobles;

import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
    private ArrayList<Card> dot3_developments, dot2_developments, dot1_developments;
    private ArrayList<Noble> noblesDeck;
    private Card[] dot3, dot2, dot1;
    private FillCardsNobles datas;
    private int[] boardGems;
    private Noble[] nobles;
    private int gold;
    private int numOfPlayers;
    private int numOfNobles;

    public GameBoard(int numOfPlayers){
        this.numOfNobles = numOfPlayers+1;
        this.numOfPlayers = numOfPlayers;
        datas = new FillCardsNobles();
        noblesDeck = datas.getNobles();

        if(numOfPlayers==2){
            boardGems = new int[]{4,4,4,4,4};
            nobles = new Noble[3];
        }
        else{
            boardGems = new int[]{5,5,5,5,5};
            nobles = new Noble[4];
        }
        fillNobles(numOfPlayers);
        gold=5;
        dot3_developments = datas.getDot3Cards();
        dot2_developments = datas.getDot2Cards();
        dot1_developments = datas.getDot1Cards();
        dot3 = new Card[4];
        dot2 = new Card[4];
        dot1 = new Card[4];

        refillDot("a");
        refillDot("b");
        refillDot("c");
    }

    private Card[] getCardDeck(String dot){
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

    public Card takeDevelopment(String inputDevCode){
        String code = inputDevCode.substring(0,1);
        int codeNumber = Integer.parseInt(inputDevCode.substring(1,2));
        Card retVal = getCardDeck(code)[codeNumber];
        getCardDeck(code)[codeNumber] = null;
        refillDot(code);

        return retVal;
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
        String code = inputDevCode.substring(0,1);
        String codeNumber = inputDevCode.substring(1,2);
        if(code.equals("a") || code.equals("b") || code.equals("c")) {
            if (codeNumber.equals("0") || codeNumber.equals("1") || codeNumber.equals("2") || codeNumber.equals("3")) {
                return true;
            } else {
                return false;
            }
        }
        else{
            return false;
        }
    }

    public void takeGems(int[] inputGems){
        for(int i=0;i<5;i++){
            boardGems[i]-=inputGems[i];
        }
    }

    private int[] convertRawInputToArray(String input){
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

    private boolean checkCharValidity(String gemChar){
        if(gemChar.equals("W") || gemChar.equals("R") || gemChar.equals("G") || gemChar.equals("O") || gemChar.equals("B")){
            return true;
        }
        else{
            return false;
        }
    }

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
    }

    private void printGameBoardLines(){
        String lines="";
        for(int i=0;i<300;i++){
            lines+="_";
        }
        System.out.println(lines);
    }

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

    private void printNobles() {
        System.out.println("Nobles");
        if(numOfNobles>0) {
            printNobleLines();

            for (int i = 0; i < numOfNobles; i++) {
                System.out.format("%-25s", "| Prestige: " + nobles[i].getPrestige());
            }
            System.out.print("|");
            System.out.println();
            for (int i = 0; i < numOfNobles; i++) {
                System.out.format("%-25s", "| Price: " + nobles[i].priceString());
            }
            System.out.print("|");
            System.out.println();

            printNobleLines();
        }
        else{
            System.out.println("--No more nobles left--");
        }
    }

    private void printNobleLines(){
        System.out.print("+");
        for(int j=0;j<numOfNobles;j++) {
            for (int i = 0; i < 24; i++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }

    private ArrayList<Card> getDevDeck(String code){
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

    private void fillNobles(int numPlayers){
        Random rand = new Random();
        if(numPlayers==2){
            for(int i=0;i<3;i++){
                int randomIndex = rand.nextInt(noblesDeck.size());
                nobles[i] = noblesDeck.get(randomIndex);
                noblesDeck.remove(randomIndex);
                String newNo = "n" + i;
                nobles[i].setNo(newNo);
            }
        }
        else{
            for(int i=0;i<4;i++){
                int randomIndex = rand.nextInt(noblesDeck.size());
                nobles[i] = noblesDeck.get(randomIndex);
                noblesDeck.remove(randomIndex);
            }
        }
    }
}
