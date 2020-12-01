/**
 * Main class: client/Main.java
 * Class: GameEngineCLI2Players.java
 * Author: Julian Tjiong (2020)
 */

package model.implementations;

import model.data.HelpPage;
import model.interfaces.GameEngineCLI;

import java.util.Scanner;

public class GameEngineCLI2Players implements GameEngineCLI {
    protected GameBoardImpl gameBoard;
    protected PlayerImpl player1;
    protected PlayerImpl player2;
    protected HelpPage helpPage;

    public GameEngineCLI2Players(int numOfPlayer, String playerName1, String playerName2){
        gameBoard = new GameBoardImpl(numOfPlayer);
        player1 = new PlayerImpl(1, playerName1);
        player2 = new PlayerImpl(2, playerName2);
        helpPage = new HelpPage();
    }

    public void playGame(){
        Scanner keyboard = new Scanner(System.in);
        while(!checkEndGame()){
            //Loops though 1 to 2
            for(int playerNo=1;playerNo<3;playerNo++){
                int opponentNo;
                if(playerNo==1){
                    opponentNo=2;
                }else {
                    opponentNo=1;
                }
                boolean flag=false;
                do {
                    //Prints the board and playerDecks again if help is called
                    clearScreen();
                    printOpponentDeck(opponentNo);
                    System.out.println("");
                    gameBoard.printGameBoard();
                    System.out.println("\n");
                    printPrivateBoard(playerNo);
                    String input;

                    do {
                        System.out.println("");
                        System.out.println("Your turn: (tip: help)");
                        System.out.print(">");
                        input = keyboard.nextLine();
                        flag=true;
                    } while (!processInput(input ,playerNo));

                    //If help is called
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
                    //Flag false means flag is called
                } while(flag==false);
            }
        }

        //End of game
        clearScreen();
        System.out.println("===  E N D   O F   G A M E ===" +
                         "\n==============================" +
                       "\n\nResult:" +
                         "\n-------\n" +
                       printResults(player1) +
                         "\n-------\n" +
                       printResults(player2));

        PlayerDeckImpl player1Deck = player1.getPlayerDeck();
        PlayerDeckImpl player2Deck = player2.getPlayerDeck();
        String winner = "";
        //If player 1 has more prestige
        if(player1Deck.getPrestige() > player2Deck.getPrestige()){
            winner = player1.getName();
        }
        //If both player has equal prestige
        else if(player1Deck.getPrestige() == player2Deck.getPrestige()){
            //If player 1 purchased less developments
            if(player1Deck.getDevelopments().size() < player2Deck.getDevelopments().size()){
                winner = player1.getName();
            }
            //If player 2 purchased less developments
            else if(player1Deck.getDevelopments().size() > player2Deck.getDevelopments().size()){
                winner = player2.getName();
            }
            else{
                winner = "draw";
            }
        }
        //If player 2 has more prestige
        else{
            winner = player2.getName();
        }

        printWinner(winner);
    }

    /**
     * Prints the final winner, except if it's a draw then prints draw result
     * @param winner or draw
     */
    protected void printWinner(String winner){
        if(!winner.equals("draw")){
            System.out.println("\nAnd the winner is ... " + winner.toUpperCase() + " !!" +
                    "\n\n *Note: Player with the most prestige wins. If both player appears to have equal" +
                    "\n number of prestige, then player with the least developments wins.");
        }
        else{
            System.out.println("\nIt appears to be a draw !!" +
                    "\n\n *Note: Player with the most prestige wins. If both player appears to have equal" +
                    "\n number of prestige, then player with the least developments wins.");
        }
        System.out.println("");
    }

    /**
     * Cheat codes for testing
     * @param code
     * @param playerDeck
     */
    protected void cheatCode(String code, PlayerDeckImpl playerDeck){
        if(code.equals("cheatone")) {
            playerDeck.setPermanentGems(new int[]{1,3,1,1,2});
            playerDeck.setPrestige(3);
        }
        else if(code.equals("cheattwo")){
            playerDeck.setPermanentGems(new int[]{3,2,3,2,1});
            playerDeck.setPrestige(8);
        }
        else if(code.equals("cheatthree")){
            playerDeck.setPermanentGems(new int[]{4,2,4,1,0});
            playerDeck.setPrestige(11);
        }
        else if(code.equals("cheatfour")){
            playerDeck.setPermanentGems(new int[]{4,4,4,4,4});
            playerDeck.setPrestige(12);
        }
        else if(code.equals("cheatendgame")){
            playerDeck.setPermanentGems(new int[]{4,4,4,4,4});
            playerDeck.setPrestige(15);
        }
    }

    /**
     * Prints a player's respective result
     * @param player
     * @return
     */
    protected String printResults(PlayerImpl player){
        return " Player " + player.getName().toUpperCase() + " :" +
                "\n  Developments purchased: " + player.getPlayerDeck().getDevelopments().size() +
                "\n  Total prestige: " + player.getPlayerDeck().getPrestige();
    }

    /**
     * The method to process user/player input. Will call smaller methods to do selected tasks/commands
     * @param inputRaw User's raw input (will be checked here for the commands)
     * @param playerNo the player's number
     * @return whether or not format is correct
     */
    protected boolean processInput(String inputRaw, int playerNo){
        String input = inputRaw.toLowerCase();
        boolean retVal=false;

        //If quit is called
        if(input.toLowerCase().equals("quit")){
            Scanner keyboard = new Scanner(System.in);
            String quitInput = "";
            do{
                System.out.print("Are you sure you want to quit? (Y/N) >");
                quitInput = keyboard.nextLine();
            } while(!quitInput.toLowerCase().equals("n") && !quitInput.toLowerCase().equals("y"));
            if(quitInput.equals("y")){
                System.exit(0);
            }
            else{
                retVal=false;
            }
        }
        //If help is called, return true to go to help in the next section, look up playGame()
        else if(input.toLowerCase().equals("help")){
            retVal = true;
        }
        //If input contains space, meaning player is calling one of the following methods with it's inputs:
        //Will call the selected processInput to do one of the tasks:
        else if(input.contains(" ")){
            if(input.substring(0, input.indexOf(" ")).equals("take")){
                return processInputTake(playerNo, input);
            }
            else if(input.substring(0, input.indexOf(" ")).equals("buy")){
                return processInputBuy(playerNo, input);
            }
            else if(input.substring(0, input.indexOf(" ")).equals("reserve")){
                return processInputReserve(playerNo, input);
            }
            else if(input.substring(0, input.indexOf(" ")).equals("pay")){
                return processInputPay(playerNo, input);
            }
            else{
                //Error: invalid command
                printErrorMessageTakeGems(15);
                retVal = false;
            }
        }
        //If no spaces is included, meaning user is calling command without an input:
        else{
            if(input.equals("take")){
                //Error: Please specify gems
                printErrorMessageTakeGems(16);
                return false;
            }
            else if(input.equals("buy")){
                //Error: Please specify development
                printErrorMessageTakeGems(17);
                return false;
            }
            else if(input.equals("reserve")){
                //Error: Please specify development to reserve
                printErrorMessageTakeGems(18);
                return false;
            }
            else if(input.equals("pay")){
                //Error: Please specify target reserve
                printErrorMessageTakeGems(19);
                return false;
            }
            //The cheatcodes:
            else if(input.equals("cheatone")){
                cheatCode("cheatone", getPlayer(playerNo).getPlayerDeck());
                return true;
            }
            else if(input.equals("cheattwo")){
                cheatCode("cheattwo", getPlayer(playerNo).getPlayerDeck());
                return true;
            }
            else if(input.equals("cheatthree")){
                cheatCode("cheatthree", getPlayer(playerNo).getPlayerDeck());
                return true;
            }
            else if(input.equals("cheatfour")){
                cheatCode("cheatfour", getPlayer(playerNo).getPlayerDeck());
                return true;
            }
            else if(input.equals("cheatendgame")){
                cheatCode("cheatendgame", getPlayer(playerNo).getPlayerDeck());
                return true;
            }
            else{
                //Error: Invalid command
                printErrorMessageTakeGems(15);
                return false;
            }
        }
        return retVal;
    }

    /**
     * Process input if player calls the pay (pay reserve) command. Will first call the checkBuyReserve()
     * from PlayerDeck to check the input format, and then whether or not player has the reserve card, and
     * finally does the player have sufficient gems to purchase it. If player needs 1 more gem and they have
     * a gold gem then ask whether or not they would like to use the gold gem. If all criteria above are met
     * then make the purchase.
     * @param playerNo
     * @param input
     * @return whether or not purchase successful
     */
    protected boolean processInputPay(int playerNo, String input){
        String inputSubstring = input.substring(4, input.length());
        PlayerDeckImpl playerDeck = getPlayer(playerNo).getPlayerDeck();

        int retValBuyReserve = playerDeck.checkBuyReserve(inputSubstring);
        if(retValBuyReserve>-20){
            //If card does exist
            if(retValBuyReserve>=0){
                int[] payment = playerDeck.buyReserve(inputSubstring);
                gameBoard.receiveGemPayment(payment, false);
                return true;
            }
            else if(retValBuyReserve==-1 && playerDeck.getGold()>0){
                boolean retValUseGold = useGold();
                if(retValUseGold){
                    int[] payment = playerDeck.buyReserve(inputSubstring);
                    gameBoard.receiveGemPayment(payment, true);
                    playerDeck.takeGold();
                    return true;
                }
                else{
                    System.out.println("** Purchase cancelled **");
                    return false;
                }
            }
            else{
                printErrorMessageTakeGems(12);
                return false;
            }
        }
        else{
            //If card does not exist
            if(retValBuyReserve==-33){
                //Can't find reserve card
                printErrorMessageTakeGems(14);
            }
            else{
                //Invalid reserve number
                printErrorMessageTakeGems(13);
            }
            return false;
        }
    }

    /**
     * Process input when user calls the reserve command (reserve a development). Will first check whether
     * or not the development number exist, and then checks whether or not user has already reserved three
     * cards (they can't reserve more than 3). If above criteria are met then reserve the card, and if
     * GameBoard has more than 0 gold gems then give 1 to the player
     * @param playerNo
     * @param input
     * @return whether or not reserve were successful
     */
    protected boolean processInputReserve(int playerNo, String input){
        String inputSubstring = input.substring(8, input.length());
        PlayerDeckImpl playerDeck = getPlayer(playerNo).getPlayerDeck();

        if(gameBoard.checkDevelopment(inputSubstring)){
            CardImpl developmentToReserve = gameBoard.getDevelopment(inputSubstring);
            if(playerDeck.checkReserve()==2){
                playerDeck.reserve(gameBoard.takeDevelopment(inputSubstring), gameBoard.getGold());
                if(gameBoard.getGold()>0){
                    gameBoard.takeGold();
                }
                return true;
            }
            else{
                //Error: Can't have more than 3 reserves
                printErrorMessageTakeGems(11);
                return false;
            }
        }
        else{
            //Error: Invalid development number
            printErrorMessageTakeGems(9);
            return false;
        }
    }

    /**
     * Process input when user calls the buy (buy development) command. First checks whether or not
     * the input is valid by calling the checkDevelopment() from GameBoard that checks through everything
     * including input format. Next, similar to the pay command, the method checks whether or not player
     * has sufficient amount of gems, or if they need 1 more gem to pay then ask if they'd like to use their
     * gold gem. If all criteria are met then make the purchase.
     * @param playerNo
     * @param input
     * @return whether or not buy is successful
     */
    protected boolean processInputBuy(int playerNo, String input){
        String inputSubstring = input.substring(4, input.length());
        PlayerDeckImpl playerDeck = getPlayer(playerNo).getPlayerDeck();

        if(gameBoard.checkDevelopment(inputSubstring)){
            CardImpl developmentToTake = gameBoard.getDevelopment(inputSubstring);
            int retVal = playerDeck.checkDevelopment(developmentToTake);
            //If gems are sufficient
            if(retVal >= 0){
                int[] payment = playerDeck.addDevelopment(gameBoard.takeDevelopment(inputSubstring));
                gameBoard.receiveGemPayment(payment, false);
                return true;
            }
            //If -1 gem and player has a gold gem (wild card)
            else if(retVal == -1 && playerDeck.getGold()>0){
                boolean retValUseGold = useGold();
                if(retValUseGold){
                    int[] payment = playerDeck.addDevelopment(gameBoard.takeDevelopment(inputSubstring));
                    gameBoard.receiveGemPayment(payment, true);
                    playerDeck.takeGold();
                    return true;
                }
                else{
                    //If player refuse to use gold gem then purchase cancelled
                    System.out.println("** Purchase cancelled **");
                    return false;
                }
            }
            //If insufficient gems
            else{
                //Error: Insufficient gems
                printErrorMessageTakeGems(10);
                return false;
            }
        }
        //If invalid development code
        else{
            //Error: Invalid development number
            printErrorMessageTakeGems(9);
            return false;
        }
    }

    /**
     * Process input when user calls the take command (take gems). First the method calls the
     * checkInputTakeGems() method from GameBoard to check the input format (the method will return
     * an error number, and if the number is 200 then no error is found and ready to proceed). If no
     * error is found (errorNo=200), then convert the raw input into a processable format using the
     * convertRawInputToArray() method from GameBoard and checkGems() from GameBoard as well to check
     * if the gems are sufficient. Finally checks if player is having more than 10 gems on their hand
     * (a player can only have up to 10 hand gems at a time). If all above criteria are met then proceed
     * on taking the gems and giving it to the player.
     * @param playerNo
     * @param input
     * @return whether or not take successfully
     */
    protected boolean processInputTake(int playerNo, String input){
        String inputSubstring = input.substring(5, input.length()).toUpperCase();
        PlayerDeckImpl playerDeck = getPlayer(playerNo).getPlayerDeck();
        int returnErrorNo = gameBoard.checkInputTakeGems(inputSubstring);

        if(returnErrorNo == 200){
            int[] rawInputArray = gameBoard.convertRawInputToArray(inputSubstring);
            if(playerDeck.checkGems(rawInputArray)){
                gameBoard.takeGems(rawInputArray);
                playerDeck.addHandGems(rawInputArray);
                return true;
            }
            else{
                //Error: Can't have more than 10 gems
                printErrorMessageTakeGems(8);
                return false;
            }
        }
        else{
            //Different error codes can be seen on checkInputTakeGems() from GameBoard
            printErrorMessageTakeGems(returnErrorNo);
            return false;
        }
    }

    /**
     * Helper method to ask if user would like to use their gold gem. Used in both inputProcessPay()
     * and inputProcessBuy()
     * @return whether or not user agree on using gold gem
     */
    protected boolean useGold(){
        Scanner scanner = new Scanner(System.in);
        boolean useGem = false;
        int flag=-1;
        do {
            System.out.print("You need 1 more gem. Use gold? (Y/N) >");
            String yesOrNo = scanner.nextLine();
            if(yesOrNo.toLowerCase().equals("y")){
                useGem=true;
                flag=0;
            }
            else if(yesOrNo.toLowerCase().equals("n")){
                useGem=false;
                flag=0;
            }
            else{
                flag=-1;
            }
        } while(flag==-1);
        return useGem;
    }

    /**
     * Prints error messages based on given errorNo
     * @param errorNo
     */
    protected void printErrorMessageTakeGems(int errorNo){
        String errorMessage="";
        if(errorNo==1){
            errorMessage = "You can't take the gold gem. Please try again";
        }
        else if(errorNo==2){
            errorMessage = "Invalid take gem format. Please look up the help page ";
        }
        else if(errorNo==22){
            errorMessage = "You can't take only 1 gem";
        }
        else if(errorNo==3){
            errorMessage = "Invalid gem code. (must be one of the following: W,R,G,O,B)";
        }
        else if(errorNo==4){
            errorMessage = "Invalid format, please look up the help page for take gem format";
        }
        else if(errorNo==5){
            errorMessage = "You must take 2 same coloured gems OR 3 different coloured gems";
        }
        else if(errorNo==6){
            errorMessage = "You must take 3 different coloured gems OR 2 same coloured gems";
        }
        else if(errorNo==7){
            errorMessage = "Not enough gems to take";
        }
        else if(errorNo==75){
            errorMessage = "You can't take 2 same coloured gems from a tile with less than 2 gems after taking";
        }
        else if(errorNo==8){
            errorMessage = "You can't have more than 10 gems on your hand!";
        }
        else if(errorNo==9){
            errorMessage = "Invalid development number. (Dev no can be seen on the card)";
        }
        else if(errorNo==10){
            errorMessage = "Insufficient gems";
        }
        else if(errorNo==11){
            errorMessage = "You can't have more than 3 reserves!";
        }
        else if(errorNo==12){
            errorMessage = "You don't have enough gems to pay";
        }
        else if(errorNo==13){
            errorMessage = "Invalid reserve number. Please choose 1/2/3 from your reserves";
        }
        else if(errorNo==14){
            errorMessage = "Can't find the reserve card. Please choose 1/2/3 from your reserves";
        }
        else if(errorNo==15){
            errorMessage = "Invalid command. Please look up the help page";
        }
        else if(errorNo==16){
            errorMessage = "Please specify the gems";
        }
        else if(errorNo==17){
            errorMessage = "Please specify the development you'd like to buy";
        }
        else if(errorNo==18){
            errorMessage = "Please specify the development you'd like to reserve";
        }
        else if(errorNo==19){
            errorMessage = "Please specify the reserved you'd like to pay";
        }
        System.out.println("** " + errorMessage + " **");
    }

    /**
     * Prints empty lines to create effect of clearing screen
     */
    protected void clearScreen(){
        for(int i=0;i<100;i++){
            System.out.println("");
        }
    }

    /**
     * Calls the printHelp() from data/HelpPage that prints the how to play part for
     * GameEngine
     */
    protected void drawHelp(){
        clearScreen();
        helpPage.printHelp();
    }

    /**
     * Prints player's private board
     * @param playerNo
     */
    protected void printPrivateBoard(int playerNo){
        getPlayer(playerNo).getPlayerDeck().printPersonalDeck();
        System.out.println("");
    }

    /**
     * Prints opponent's public board
     * @param opponentNo
     */
    protected void printOpponentDeck(int opponentNo){
        getPlayer(opponentNo).getPlayerDeck().printPublicDeck();
        System.out.println("");
    }

    /**
     * Checks whether or not end game is triggered (15 prestige points)
     * @return whether or not game ends
     */
    protected boolean checkEndGame(){
        return player1.getPlayerDeck().getPrestige()>=15 || player2.getPlayerDeck().getPrestige()>=15;
    }

    /**
     * Returns a player based on given player number
     * @param no
     * @return selected player object
     */
    protected PlayerImpl getPlayer(int no){
        if(no==1){
            return player1;
        }
        else{
            return player2;
        }
    }
}
