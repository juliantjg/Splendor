package model;

import model.data.FillCardsNobles;

import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
    private ArrayList<Card> dot3_developments, dot2_developments, dot1_developments;
    private Card[] dot3, dot2, dot1;
    private FillCardsNobles datas;
    private ArrayList<Noble> noblesDeck;
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

        refillDot3();
        refillDot2();
        refillDot1();
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
        print3Dots();
        printDevelopmentLines();
        print2Dots();
        printDevelopmentLines();
        print1Dots();
        printDevelopmentLines();
    }

    private void print3Dots(){
        System.out.format("%-20s", "| ");
        for (int i = 0; i < 4; i++) {
            System.out.format("%-30s", "| Prestige: " + dot3[i].getPrestige());
        }
        System.out.print("|");
        System.out.println();

        System.out.format("%-20s", "|  III DOTS");
        for (int i = 0; i < 4; i++) {
            System.out.format("%-30s", "| Price: " + dot3[i].priceString());
        }
        System.out.print("|");
        System.out.println();

        System.out.format("%-20s", "|");
        for (int i = 0; i < 4; i++) {
            System.out.format("%-30s", "| No: " + dot3[i].getCardNo());
        }
        System.out.print("|");
        System.out.println();
    }

    private void print2Dots(){
        System.out.format("%-20s", "| ");
        for (int i = 0; i < 4; i++) {
            System.out.format("%-30s", "| Prestige: " + dot2[i].getPrestige());
        }
        System.out.print("|");
        System.out.println();

        System.out.format("%-20s", "|  II DOTS");
        for (int i = 0; i < 4; i++) {
            System.out.format("%-30s", "| Price: " + dot2[i].priceString());
        }
        System.out.print("|");
        System.out.println();

        System.out.format("%-20s", "|");
        for (int i = 0; i < 4; i++) {
            System.out.format("%-30s", "| No: " + dot2[i].getCardNo());
        }
        System.out.print("|");
        System.out.println();
    }

    private void print1Dots(){
        System.out.format("%-20s", "| ");
        for (int i = 0; i < 4; i++) {
            System.out.format("%-30s", "| Prestige: " + dot1[i].getPrestige());
        }
        System.out.print("|");
        System.out.println();

        System.out.format("%-20s", "|  I DOTS");
        for (int i = 0; i < 4; i++) {
            System.out.format("%-30s", "| Price: " + dot1[i].priceString());
        }
        System.out.print("|");
        System.out.println();

        System.out.format("%-20s", "|");
        for (int i = 0; i < 4; i++) {
            System.out.format("%-30s", "| No: " + dot1[i].getCardNo());
        }
        System.out.print("|");
        System.out.println();
    }

    private void printDevelopmentLines(){
        System.out.print("+");
        for (int i = 0; i < 19; i++) {
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

    private void refillDot3(){
        Random rand = new Random();
        for(int i=0;i<4;i++){
            if(dot3[i]==null){
                int randomIndex = rand.nextInt(dot3_developments.size());
                dot3[i] = dot3_developments.get(randomIndex);
                dot3_developments.remove(randomIndex);
                String newCardNo = "a" + i;
                dot3[i].setCardNo(newCardNo);
            }
        }
    }

    private void refillDot2(){
        Random rand = new Random();
        for(int i=0;i<4;i++){
            if(dot2[i]==null){
                int randomIndex = rand.nextInt(dot2_developments.size());
                dot2[i] = dot2_developments.get(randomIndex);
                dot2_developments.remove(randomIndex);
                String newCardNo = "b" + i;
                dot2[i].setCardNo(newCardNo);
            }
        }
    }

    private void refillDot1(){
        Random rand = new Random();
        for(int i=0;i<4;i++){
            if(dot1[i]==null){
                int randomIndex = rand.nextInt(dot1_developments.size());
                dot1[i] = dot1_developments.get(randomIndex);
                dot1_developments.remove(randomIndex);
                String newCardNo = "c" + i;
                dot1[i].setCardNo(newCardNo);
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
