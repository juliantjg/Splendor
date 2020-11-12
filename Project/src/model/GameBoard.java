package model;

import java.util.ArrayList;

public class GameBoard {
    private ArrayList<Card> dot1_developments, dot2_developments, dot3_developments;
    private ArrayList<Noble> nobles;
    int[] boardGems;
    int gold;

    public GameBoard(int numOfPlayers){
        if(numOfPlayers==2){
            boardGems = new int[]{4,4,4,4,4};
        }
        else{
            boardGems = new int[]{5,5,5,5,5};
        }
        gold=5;
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
