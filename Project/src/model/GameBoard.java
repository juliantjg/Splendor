package model;

import model.data.FillCardsNobles;

import java.util.ArrayList;

public class GameBoard {
    private ArrayList<Card> dot3_developments, dot2_developments, dot1_developments;
    private Card[] dot3, dot2, dot1;
    private FillCardsNobles datas;
    private ArrayList<Noble> nobles;
    private int[] boardGems;
    private int gold;

    public GameBoard(int numOfPlayers){
        if(numOfPlayers==2){
            boardGems = new int[]{4,4,4,4,4};
            datas.getNobles(2);
        }
        else{
            boardGems = new int[]{5,5,5,5,5};
            datas.getNobles(3);
        }
        gold=5;
        dot1_developments = datas.getDot1Cards();
        dot2_developments = datas.getDot2Cards();
        dot3_developments = datas.getDot3Cards();
    }




    public ArrayList<Card> getDot1_developments() {
        return dot1_developments;
    }

    public ArrayList<Card> getDot2_developments() {
        return dot2_developments;
    }

    public ArrayList<Card> getDot3_developments() {
        return dot3_developments;
    }

    public ArrayList<Noble> getNobles() {
        return nobles;
    }

    public int[] getBoardGems() {
        return boardGems;
    }
}
