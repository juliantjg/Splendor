package client;

import model.GameEngineCLI2Players;
import model.GameEngineCLI3Players;
import model.data.HelpPage;

import java.util.Scanner;

public class Main {

    private static HelpPage helpPage;

    public static void main(String[] args){
        clearScreen();
        printSign();
        System.out.format("\n%36s", "[Now Loading...]\n");

        printLoading();

        mainMenu();
    }

    private static void mainMenu(){
        clearScreen();
        Scanner keyboard = new Scanner(System.in);
        menu();
        System.out.println("");
        int inputCheck=-1;
        while(inputCheck==-1){
            System.out.print(">");
            String input = keyboard.nextLine();

            if(input.equals("1")){
                inputCheck=1;
                newGame2Players();
            }
            else if(input.equals("2")){
                inputCheck=1;
                newGame3Players();
            }else if(input.equals("3")){
                printHelp();
                String helpInput = "";
                do{
                    System.out.println("Input 'back' to go back");
                    System.out.print(">");
                    helpInput = keyboard.nextLine();
                }while(!helpInput.toLowerCase().equals("back"));
                mainMenu();
            }
            else if(input.equals("4")){
                inputCheck=1;
                System.out.println("GOODBYE!");
                System.exit(0);
            }
            else{
                System.out.println("Input invalid, please try again (Choose from above 1/2/3)");
                inputCheck=-1;
            }
        }
    }

    private static void newGame3Players(){
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter name for Player 1: ");
        System.out.print(">");
        String playerName1 = keyboard.nextLine();
        System.out.println("Enter name for Player 2: ");
        System.out.print(">");
        String playerName2 = keyboard.nextLine();
        System.out.println("Enter name for Player 3: ");
        System.out.print(">");
        String playerName3 = keyboard.nextLine();

        printLoading2();

        GameEngineCLI3Players gameEngine = new GameEngineCLI3Players(3, playerName1, playerName2, playerName3);
        clearScreen();
        gameEngine.playGame();
        playAgain();
    }

    private static void printHelp(){
        helpPage = new HelpPage();
        clearScreen();
        helpPage.printHowToPlay();
    }

    private static void newGame2Players(){
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter name for Player 1: ");
        System.out.print(">");
        String playerName1 = keyboard.nextLine();
        System.out.println("Enter name for Player 2: ");
        System.out.print(">");
        String playerName2 = keyboard.nextLine();

        printLoading2();

        GameEngineCLI2Players gameEngine = new GameEngineCLI2Players(2, playerName1, playerName2);
        clearScreen();
        gameEngine.playGame();
        playAgain();
    }

    private static void playAgain(){
        Scanner keyboard = new Scanner(System.in);
        String backInput = "";
        boolean flag = false;
        do {
            System.out.print("Back to main menu? (Y/N) >");
            backInput = keyboard.nextLine();
            if(backInput.toLowerCase().equals("y")){
                mainMenu();
                flag=true;
            }
            else if(backInput.toLowerCase().equals("n")){
                System.exit(0);
                flag=true;
            }
            else{
                flag=false;
            }
        } while(!flag);
    }

    private static void printLoading2(){
        System.out.println("\n Game starting in:");
        sleep(400);
        System.out.println(" 3..");
        sleep(1000);
        System.out.println(" 2..");
        sleep(1000);
        System.out.println(" 1..");
        sleep(1000);
        System.out.println("\n --START--");
        sleep(500);
    }

    private static void menu(){
        System.out.println("WELCOME TO SPLENDOR!" +
                "\nPlease set the window to fullscreen for better gameplay." +
                "\n 1. New Game (2 Players)" +
                "\n 2. New Game (3 Players)" +
                "\n 3. How to play" +
                "\n 4. Quit"
        );
    }

    private static void printLoading(){
        for(int i=0;i<40;i++){
            sleep(37);
            System.out.print("*");
        }
        for(int i=0;i<11;i++){
            sleep(100);
            System.out.print("*");
        }
        for(int i=0;i<5;i++){
            sleep(200);
            System.out.print("*");
        }
    }

    private static void printSign(){
        System.out.println("" +
                "  _____ _____  _      ______ _   _ _____   ____  _____  \n" +
                " / ____|  __ \\| |    |  ____| \\ | |  __ \\ / __ \\|  __ \\ \n" +
                "| (___ | |__) | |    | |__  |  \\| | |  | | |  | | |__) |\n" +
                " \\___ \\|  ___/| |    |  __| | . ` | |  | | |  | |  _  / \n" +
                " ____) | |    | |____| |____| |\\  | |__| | |__| | | \\ \\ \n" +
                "|_____/|_|    |______|______|_| \\_|_____/ \\____/|_|  \\_\\");
    }

    private static void clearScreen(){
        for(int i=0;i<100;i++){
            System.out.println("");
        }
    }

    private static void sleep(int time){
        try
        {
            Thread.sleep(time);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
