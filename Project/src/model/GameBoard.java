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

    public boolean checkGems(int[] inputGems){
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
            }
            return retVal;
        }
        else{
            return false;
        }
    }

    public void printGameBoard(){
        printNobles();
        System.out.println();
        printDevelopment();
        System.out.println();
        printGems();
    }

    private void printGems(){
        System.out.println("G E M S");

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
        System.out.println("D E V E L O P M E N T S");
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
        System.out.println("N O B L E S");
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
