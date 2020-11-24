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
                boolean flag=false;
                do {
                    clearScreen();
                    printOpponentDeck(opponentNo);
                    gameBoard.printGameBoard();
                    System.out.println("");
                    printPrivateBoard(playerNo);
                    String input;

                    do {
                        System.out.println("");
                        System.out.println("Your turn: (tip: help)");
                        System.out.print(">");
                        input = keyboard.nextLine();
                        flag=true;
                    } while (!processInput(input));

                    if(input.toLowerCase().equals("help")){
                        String helpInput="";
                        drawHelp();
                        do{
                            System.out.println("Input 'back' to go back");
                            System.out.print(">");
                            input = keyboard.nextLine();
                        } while(!input.equals("back"));
                        flag=false;
                    }
                } while(flag==false);
            }
        }
    }

    protected boolean processInput(String input){
        boolean retVal=false;

        if(input.toLowerCase().equals("help")){
            retVal = true;
        }
        else if(input.contains(" ")){
            if(input.substring(0, input.indexOf(" ")).equals("take")){
                retVal = true;
            }
            else if(input.substring(0, input.indexOf(" ")).equals("reserve")){

            }
            else if(input.substring(0, input.indexOf(" ")).equals("buy")){

            }
            else if(input.substring(0, input.indexOf(" ")).equals("pay")){

            }
            else{
                System.out.println("** Invalid command. Please look up the help page **");
                retVal = false;
            }
        }
        else{
            if(input.equals("take")){
                System.out.println("** Please specify the gems **");
            }
            else if(input.equals("buy")){
                System.out.println("** Please specify the development you'd like to buy **");
            }
            else if(input.equals("reserve")){
                System.out.println("** Please specify the development you'd like to reserve **");
            }
            else if(input.equals("pay")){
                System.out.println("** Please specify the reserved you'd like to pay **");
            }
            else{
                System.out.println("** Invalid command. Please look up the help page **");
            }
            return false;
        }
        return retVal;
    }

    protected void clearScreen(){
        for(int i=0;i<200;i++){
            System.out.println("");
        }
    }

    protected void drawHelp(){
        clearScreen();
        System.out.println("This is help");
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
