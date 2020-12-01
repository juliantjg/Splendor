/**
 * Main class: client/Main.java
 * Class: GameEngineCLI3Players.java
 * Author: Julian Tjiong (2020)
 */

package model.implementations;

import java.util.Scanner;

public class GameEngineCLI3Players extends GameEngineCLI2Players {
    private PlayerImpl player3;

    /**
     * Adds a new player 3 to the game
     * @param numOfPlayer
     * @param playerName1
     * @param playerName2
     * @param playerName3
     */
    public GameEngineCLI3Players(int numOfPlayer, String playerName1, String playerName2, String playerName3){
        super(numOfPlayer, playerName1, playerName2);
        player3 = new PlayerImpl(3, playerName3);
    }

    /**
     * This is the 3 player version of the previous 2 players playGame()
     */
    @Override
    public void playGame(){
        Scanner keyboard = new Scanner(System.in);
        while(!checkEndGame()){
            for(int playerNo=1;playerNo<4;playerNo++){
                checkNobleVisit(playerNo);
                int opponentNo1;
                int opponentNo2;
                if(playerNo==1){
                    opponentNo1=2;
                    opponentNo2=3;
                }
                else if(playerNo==2){
                    opponentNo1=3;
                    opponentNo2=1;
                }
                else{
                    opponentNo1=1;
                    opponentNo2=2;
                }
                boolean flag=false;
                do{
                    clearScreen();
                    printOpponentDeck(opponentNo1);
                    System.out.println("");
                    printOpponentDeck(opponentNo2);
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

        //End of game
        clearScreen();
        System.out.println("===  E N D   O F   G A M E ===" +
                "\n==============================" +
                "\n\nResult:" +
                "\n-------\n" +
                printResults(player1) +
                "\n-------\n" +
                printResults(player2) +
                "\n-------\n" +
                printResults(player3));

        int play1 = player1.getPlayerDeck().getPrestige();
        int play2 = player2.getPlayerDeck().getPrestige();
        int play3 = player3.getPlayerDeck().getPrestige();

        int play1Size = player1.getPlayerDeck().getDevelopments().size();
        int play2Size = player2.getPlayerDeck().getDevelopments().size();
        int play3Size = player3.getPlayerDeck().getDevelopments().size();

        String winner="";

        //If player 1 has most prestige
        if(play1>play2 && play1>play3){
            winner = player1.getName();
        }
        //If player 2 has most prestige
        else if(play2>play1 && play2>play3){
            winner = player2.getName();
        }
        //If player 3 has most prestige
        else if(play3>play1 && play3>play2){
            winner = player3.getName();
        }
        //If player 1 is tie with player 2
        else if(play1==play2 && play1>play3){
            if(play1Size<play2Size){
                winner = player1.getName();
            }
            else if(play2Size<play1Size){
                winner = player2.getName();
            }
            else{
                winner = player1.getName() + " and " + player2.getName();
            }
        }
        //If player 2 tie with player 3
        else if(play2==play3 && play2>play1){
            if(play2Size<play3Size){
                winner = player2.getName();
            }
            else if(play3Size<play2Size){
                winner = player3.getName();
            }
            else{
                winner = player2.getName() + " and " + player3.getName();
            }
        }
        //If player 1 tie with player 3
        else if(play1==play3 && play1>play2){
            if(play1Size<play3Size){
                winner = player1.getName();
            }
            else if(play3Size<play1Size){
                winner = player3.getName();
            }
            else{
                winner = player1.getName() + " and " + player3.getName();
            }
        }
        //If they all have equal prestige points
        else{
            winner = "draw";
        }
        printWinner(winner);
    }

    /**
     * Returns player for 3 players mode
     * @param no
     * @return a selected player object
     */
    @Override
    protected PlayerImpl getPlayer(int no){
        if(no==1){
            return player1;
        }
        else if(no==2){
            return player2;
        }
        else{
            return player3;
        }
    }
}
