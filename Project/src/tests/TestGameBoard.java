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
        gameBoard = new GameBoard(2);

        //Input format/validity tests:
        //Test take 2 same gems
        int retVal = gameBoard.checkInputTakeGems("O O");
        System.out.println(retVal);
        //Test take 3 different gems
        retVal = gameBoard.checkInputTakeGems("W B R");
        System.out.println(retVal);
        //Test take 2 different gems
        retVal = gameBoard.checkInputTakeGems("R B");
        System.out.println(retVal);
        //Test take 3 same gems
        retVal = gameBoard.checkInputTakeGems("R R R");
        System.out.println(retVal);
        //Test take 3 gems with duplicates
        retVal = gameBoard.checkInputTakeGems("R R W");
        System.out.println(retVal);
        //Test take 1 gem
        retVal = gameBoard.checkInputTakeGems("W");
        System.out.println(retVal);
        //Test take gold
        retVal = gameBoard.checkInputTakeGems("E E");
        System.out.println(retVal);
        //Test take invalid gems
        retVal = gameBoard.checkInputTakeGems("Z W B");
        System.out.println(retVal);
        //Test take gems with invalid spaces
        retVal = gameBoard.checkInputTakeGems("WRR G");
        System.out.println(retVal);

        //Input gems availability tests:
        //Test taking 2 gems from a tile with <4 gems
        gameBoard.takeGems(new int[]{1,0,0,0,0});
        retVal = gameBoard.checkInputTakeGems("W W");
        System.out.println(retVal);
        //Test taking from empty gem tile
        gameBoard.takeGems(new int[]{3,0,0,0,0});
        retVal = gameBoard.checkInputTakeGems("W R G");
        System.out.println(retVal);
    }

    private static void testReceiveGemPayment(){
        gameBoard = new GameBoard(2);

        gameBoard.printGameBoard();
        gameBoard.takeDevelopment("a2");
        gameBoard.printGameBoard();
    }

    public static void main(String[] args){
        testPrintFormat();
    }
}
