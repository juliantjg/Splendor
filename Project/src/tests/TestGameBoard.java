package tests;

import model.GameBoard;

public class TestGameBoard {
    private static GameBoard gameBoard;

    private static void testPrintNoble(){
        gameBoard = new GameBoard(2);

        gameBoard.printGameBoard();

    }

    public static void main(String[] args){
        testPrintNoble();
    }
}
