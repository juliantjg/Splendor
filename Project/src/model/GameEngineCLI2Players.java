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
                    } while (!processInput(input ,playerNo));

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

    protected boolean processInput(String input, int playerNo){
        boolean retVal=false;

        if(input.toLowerCase().equals("help")){
            retVal = true;
        }
        else if(input.contains(" ")){
            if(input.substring(0, input.indexOf(" ")).equals("take")){
                return processInputTake(playerNo, input);
            }
            else if(input.substring(0, input.indexOf(" ")).equals("buy")){

            }
            else if(input.substring(0, input.indexOf(" ")).equals("reserve")){

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

    protected boolean processInputTake(int playerNo, String input){
        String inputSubstring = input.substring(5, input.length());
        PlayerDeck playerDeck = getPlayer(playerNo).getPlayerDeck();
        int returnErrorNo = gameBoard.checkInputTakeGems(inputSubstring);
        if(returnErrorNo == 200){
            int[] rawInputArray = gameBoard.convertRawInputToArray(inputSubstring);
            if(playerDeck.checkGems(rawInputArray)){
                gameBoard.takeGems(rawInputArray);
                playerDeck.addHandGems(rawInputArray);
                return true;
            }
            else{
                System.out.println("** You can't have more than 10 gems! **");
                return false;
            }
        }
        else{
            printErrorMessageTakeGems(returnErrorNo);
            return false;
        }
    }

    protected void printErrorMessageTakeGems(int errorNo){
        String errorMessage="";
        if(errorNo==1){
            errorMessage = "** You can't take the gold gem. Please try again **";
        }
        else if(errorNo==2){
            errorMessage = "** Invalid take gem format. Please look up the help page **";
        }
        else if(errorNo==22){
            errorMessage = "** You can't take only 1 gem **";
        }
        else if(errorNo==3){
            errorMessage = "** Invalid gem code. (must be one of the following: W,R,G,O,B) **";
        }
        else if(errorNo==4){
            errorMessage = "** Invalid format, please look up the help page for take gem format **";
        }
        else if(errorNo==5){
            errorMessage = "** You must take 2 same coloured gems OR 3 different coloured gems **";
        }
        else if(errorNo==6){
            errorMessage = "** You must take 3 different coloured gems OR 2 same coloured gems **";
        }
        else if(errorNo==7){
            errorMessage = "** Not enough gems to take **";
        }
        else{
            errorMessage = "** You can't take 2 same coloured gems from a tile with less than 2 gems after taking **";
        }
        System.out.println(errorMessage);
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
