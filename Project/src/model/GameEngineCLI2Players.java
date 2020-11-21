package model;

import model.interfaces.GameEngineCLI;

import java.util.Scanner;

public class GameEngineCLI2Players implements GameEngineCLI {
    private GameBoard gameBoard;
    private Player player1;
    private Player player2;

    public GameEngineCLI2Players(String playerName1, String playerName2){
        gameBoard = new GameBoard(2);
        player1 = new Player(1, playerName1);
        player2 = new Player(2, playerName2);
    }

    public void playGame(){
        Scanner keyboard = new Scanner(System.in);
        while(!checkEndGame()){
            for(int playerNo=1;playerNo<3;playerNo++){
                int opponentNo;
                if(playerNo==1){
                    opponentNo=2;
                }else {
                    opponentNo=1;
                }
                printOpponentDeck(opponentNo);
                gameBoard.printGameBoard();
                System.out.println("");
                printPrivateBoard(playerNo);
                String input;
                do{
                    System.out.println("Your turn: (tip: help)");
                    System.out.print(">");
                    input = keyboard.nextLine();
                } while(!processInput(input));
            }
        }
    }

    protected boolean processInput(String input){
        boolean retVal=false;



        return retVal;
    }

    protected void printPrivateBoard(int playerNo){
        getPlayer(playerNo).getPlayerDeck().printPersonalDeck();
        System.out.println("");
    }

    protected void printOpponentDeck(int opponentNo){
        getPlayer(opponentNo).getPlayerDeck().printPublicDeck();
        System.out.println("");
    }

    protected boolean checkEndGame(){
        return player1.getPlayerDeck().getPrestige()>=15 || player2.getPlayerDeck().getPrestige()>=15;
    }

    protected Player getPlayer(int no){
        if(no==1){
            return player1;
        }
        else{
            return player2;
        }
    }
}
