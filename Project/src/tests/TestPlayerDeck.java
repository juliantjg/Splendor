package tests;

import model.implementations.CardImpl;
import model.implementations.NobleImpl;
import model.implementations.PlayerImpl;
import model.implementations.PlayerDeckImpl;

import java.util.Scanner;

public class TestPlayerDeck {
    private static PlayerImpl player1;
    private static PlayerDeckImpl playerDeck1;

    /*
    testFormat(): To test print public and private player decks with 1 noble and development each
     */
    private static void testFormat(){
        //Initialize player and playerDeck
        player1 = new PlayerImpl(1, "Jul");
        playerDeck1 = new PlayerDeckImpl(player1.getName());

        //Initialize a development card
        int[] priceInput = new int[]{0,2,1,0,0};
        CardImpl inputCard = new CardImpl(3, priceInput, 'G');

        //Initialize a noble
        int[] priceNoble = new int[]{1,1,0,0,0};
        NobleImpl inputNoble = new NobleImpl(5, priceNoble);

        //Put development card and noble into playerDeck
        playerDeck1.addDevelopment(inputCard);
        playerDeck1.addNoble(inputNoble);
        playerDeck1.reserve(inputCard,2);

        //Initialize hand gems and put into playerDeck
        int[] handGems = new int[]{1,0,2,3,0};
        playerDeck1.addHandGems(handGems);

        //Print the public and private deck
        System.out.println("+++Public Deck:+++\n");
        playerDeck1.printPublicDeck();
        System.out.println("\n\n+++Private Deck:+++\n");
        playerDeck1.printPersonalDeck();
    }

    /*
    testAddingNoblesDevelopments(): Test print playerDeck with 3 nobles and developments each
     */
    private static void testAddingNoblesDevelopments(){
        //Initialize player and playerDeck
        player1 = new PlayerImpl(2, "Jil");
        playerDeck1 = new PlayerDeckImpl(player1.getName());

        //Initialize 3 development cards
        int[] priceInput = new int[]{0,2,1,0,0};
        CardImpl inputCard = new CardImpl(3, priceInput, 'G');
        CardImpl inputCard2 = new CardImpl(3, priceInput, 'O');
        CardImpl inputCard3 = new CardImpl(3, priceInput, 'R');

        //Initialize 3 nobles
        int[] priceNoble = new int[]{1,1,0,0,0};
        NobleImpl inputNoble = new NobleImpl( 2, priceNoble);
        NobleImpl inputNoble2 = new NobleImpl( 1, priceNoble);
        NobleImpl inputNoble3 = new NobleImpl( 3, priceNoble);

        //Put development card and noble into playerDeck
        playerDeck1.addDevelopment(inputCard);
        playerDeck1.addDevelopment(inputCard2);
        playerDeck1.addDevelopment(inputCard3);
        playerDeck1.addNoble(inputNoble);
        playerDeck1.addNoble(inputNoble2);
        playerDeck1.addNoble(inputNoble3);

        //Print the public and private deck
        System.out.println("+++Public Deck:+++\n");
        playerDeck1.printPublicDeck();
        System.out.println("\n\n+++Private Deck:+++\n");
        playerDeck1.printPersonalDeck();
    }

    /*
    testCheckNoble(): Test the checkNoble() method from PlayerDeck class. If a player has sufficient
    gems for a neighbour visit then will print out result accordingly
     */
    private static void testCheckNoble(){
        //Initialize player and playerDeck
        player1 = new PlayerImpl(3, "Joel");
        playerDeck1 = new PlayerDeckImpl(player1.getName());

        //Initialize a noble
        int[] priceNoble = new int[]{1,1,0,0,0};
        NobleImpl inputNoble = new NobleImpl( 2, priceNoble);

        //Initialize 2 developments to cause a noble visit
        int[] priceInput = new int[]{0,2,1,0,0};
        CardImpl inputCard = new CardImpl(3, priceInput, 'W');
        CardImpl inputCard2 = new CardImpl(3, priceInput, 'R');

        playerDeck1.addDevelopment(inputCard);
        playerDeck1.addDevelopment(inputCard2);

        boolean retVal = playerDeck1.checkNoble(inputNoble);

        if(retVal){
            System.out.println("Neighbour visit");
        }
        else{
            System.out.println("No neighbour visit");
        }
    }

    /*
    testCheckDevelopment(): Test the checkDevelopment() method from PlayerDeck class. If a player has
    sufficient gems to buy a development then will return accordingly. However, if a player has 1 less
    gems AND if they have more than 0 gold then will ask whether player wants to use the gold. If they
    do then the gold will be taken away from them and the purchase will be made successful, otherwise
    purchase failed.
     */
    private static void testCheckDevelopment(){
        Scanner keyboard2 = new Scanner(System.in);

        //Initialize player and playerDeck
        player1 = new PlayerImpl(4, "Jane");
        playerDeck1 = new PlayerDeckImpl(player1.getName());

        //Initialize hand gems and put into playerDeck
        int[] handGems = new int[]{0,1,1,0,1};
        playerDeck1.addHandGems(handGems);

        //Give player a gold
        playerDeck1.addGold();

        //Gives player a permanent gem
        playerDeck1.setPermanentGems(new int[]{0,1,0,0,0});

        //Initialize new development the player's trying to buy
        int[] priceInput2 = new int[]{0,3,1,0,0};
        CardImpl inputCard2 = new CardImpl(3, priceInput2, 'G');

        int retVal = playerDeck1.checkDevelopment(inputCard2);
        System.out.println(retVal);
        if(retVal>=0){
            System.out.println("Sufficient gems");
            playerDeck1.printPersonalDeck();
        }
        else if (retVal==-1 && playerDeck1.getGold()>0){
            System.out.println("You need 1 more gem. Use gold? (Y/N)");
            String answer = keyboard2.nextLine();
            if(answer.equals("Y")){
                playerDeck1.addDevelopment(inputCard2);
                playerDeck1.takeGold();
                System.out.println("Purchase successful");
                playerDeck1.printPersonalDeck();
            }
            else{
                System.out.println("Purchase failed");
            }
        }
        else{
            System.out.println("Insufficient gems");
        }

        int[] test = playerDeck1.getHandGems();

        for(int i=0;i<5;i++){
            System.out.println(test[i]);
        }
    }

    /*
    testCheckGems(): Test the checkGems method from PlayerDeck class. A player can't have more than
    ten HAND gems.
     */
    private static void testCheckGems(){
        //Initialize player and playerDeck
        player1 = new PlayerImpl(5, "John");
        playerDeck1 = new PlayerDeckImpl(player1.getName());

        //Initialize hand gems and put into playerDeck
        playerDeck1.addHandGems(new int[]{2,2,2,2,1});

        boolean retVal = playerDeck1.checkGems(new int[]{0,0,0,0,2});

        if(retVal){
            System.out.println("Can take gems");
        }
        else{
            System.out.println("Can't have more than 10 hand gems");
        }
    }

    private static void testReserveDevelopment(){
        //Initialize player and playerDeck
        player1 = new PlayerImpl(6, "Juno");
        playerDeck1 = new PlayerDeckImpl(player1.getName());

        int[] priceInput2 = new int[]{0,2,1,0,0};
        CardImpl inputCard2 = new CardImpl(3, priceInput2, 'G');

        int retVal = playerDeck1.checkReserve();
        if(retVal==2){
            playerDeck1.reserve(inputCard2,1);
            playerDeck1.printPublicDeck();
            playerDeck1.printPersonalDeck();
        }
        else{
            System.out.println("Can't have more than 3 reserves!");
        }
    }

    private static void testMoreThan3Reserves(){
        //Initialize player and playerDeck
        player1 = new PlayerImpl(6, "Juno");
        playerDeck1 = new PlayerDeckImpl(player1.getName());

        int[] priceInput2 = new int[]{0,2,1,0,0};
        CardImpl inputCard2 = new CardImpl(3, priceInput2, 'G');
        CardImpl inputCard3 = new CardImpl(2, priceInput2, 'W');
        CardImpl inputCard4 = new CardImpl(1, priceInput2, 'R');

        playerDeck1.reserve(inputCard2,1);
        playerDeck1.reserve(inputCard3,1);
        playerDeck1.reserve(inputCard4,0);

        int retVal = playerDeck1.checkReserve();

        if(retVal==1){
            System.out.println("Can't have more than 3 reserves!");
        }
        else{
            System.out.println("True");
        }
    }

    public static void testCheckBuyReserve(){
        //Initialize player and playerDeck
        player1 = new PlayerImpl(7, "Bob Dylan");
        playerDeck1 = new PlayerDeckImpl(player1.getName());

        int[] priceInput2 = new int[]{2,2,1,0,0};
        CardImpl inputCard2 = new CardImpl(3, priceInput2, 'G');

        playerDeck1.reserve(inputCard2,3);

        playerDeck1.addHandGems(new int[]{2,2,0,0,0});

        int checkBuyReserveRetVal = playerDeck1.checkBuyReserve("1");
        if(checkBuyReserveRetVal > -1){
            System.out.println("Successful");
            playerDeck1.buyReserve("1");
        }
        else if(checkBuyReserveRetVal==-1 && playerDeck1.getGold()>0){
            Scanner keyboard = new Scanner(System.in);
            System.out.println("You need 1 more gem. Use 1 Gold(E) gem? (Y/N)");
            String output = keyboard.nextLine();
            if(output.equals("Y")){
                System.out.println("Successful");
                playerDeck1.takeGold();
                playerDeck1.buyReserve("1");
            }
            else{
                System.out.println("Cancel payment");
            }
        }
        else{
            System.out.println("Insufficient gems");
        }

        playerDeck1.printPublicDeck();
        playerDeck1.printPersonalDeck();
    }

    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);

        while(true){
            System.out.println("\n\nChoose a test case: " +
                    "\n a. testFormat()" +
                    "\n b. testAddingNoblesDevelopments()" +
                    "\n c. testCheckNoble()" +
                    "\n d. testCheckDevelopment()" +
                    "\n e. testCheckGems()" +
                    "\n f. testReserveDevelopment()" +
                    "\n g. testMoreThan3Reserves()" +
                    "\n h. testCheckBuyReserve()" +
                    "\n i. exit" +
                    "\n\n");
            String input = keyboard.nextLine();
            if(input.equals("a")){
                testFormat();
            }
            else if(input.equals("b")){
                testAddingNoblesDevelopments();
            }
            else if(input.equals("c")){
                testCheckNoble();
            }
            else if(input.equals("d")){
                testCheckDevelopment();
            }
            else if(input.equals("e")){
                testCheckGems();
            }
            else if(input.equals("f")){
                testReserveDevelopment();
            }
            else if(input.equals("g")){
                testMoreThan3Reserves();
            }
            else if(input.equals("h")){
                testCheckBuyReserve();
            }
            else{
                System.exit(0);
            }
        }
    }
}
