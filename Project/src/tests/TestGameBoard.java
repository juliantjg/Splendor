package tests;

import model.GameBoard;

public class TestGameBoard {
    private static GameBoard gameBoard;

    private static void testPrintFormat(){
        gameBoard = new GameBoard(2);
        gameBoard.printGameBoard();
    }

    private static void testTakeDevelopment(){
        gameBoard = new GameBoard(2);
        gameBoard.printGameBoard();
        String input = "a2";
        if(gameBoard.checkDevelopment(input)){
            gameBoard.takeDevelopment(input);
            System.out.println("Successful");
            gameBoard.printGameBoard();
        }
        else{
            System.out.println("Invalid development number");
        }
    }

    private static void testTakeGems(){

    }

    public static void main(String[] args){
        testTakeGems();
    }
}
