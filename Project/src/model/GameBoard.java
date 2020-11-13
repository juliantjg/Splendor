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

    public GameBoard(int numOfPlayers){
        noblesDeck = datas.getNobles();
        datas = new FillCardsNobles();
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

        refillDot3();
        refillDot2();
        refillDot1();
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
