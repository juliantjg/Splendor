package tests;

import model.Card;
import model.Noble;
import model.Player;
import model.PlayerDeck;

import java.util.Scanner;

public class TestPlayerDeck {
    private static Player player1;
    private static PlayerDeck playerDeck1;

    /*
    testFormat(): To test print public and private player decks with 1 noble and development each
     */
    private static void testFormat(){
        //Initialize player and playerDeck
        player1 = new Player(1, "Jul");
        playerDeck1 = new PlayerDeck(player1.getName());

        //Initialize a development card
        int[] priceInput = new int[]{0,2,1,0,0};
        Card inputCard = new Card(1, 3, priceInput, 'G');

        //Initialize a noble
        int[] priceNoble = new int[]{1,1,0,0,0};
        Noble inputNoble = new Noble(1, 5, priceNoble);

        //Put development card and noble into playerDeck
        playerDeck1.addDevelopment(inputCard);
        playerDeck1.addNoble(inputNoble);

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
        player1 = new Player(2, "Jil");
        playerDeck1 = new PlayerDeck(player1.getName());

        //Initialize 3 development cards
        int[] priceInput = new int[]{0,2,1,0,0};
        Card inputCard = new Card(1, 3, priceInput, 'G');
        Card inputCard2 = new Card(2, 3, priceInput, 'O');
        Card inputCard3 = new Card(3, 3, priceInput, 'R');

        //Initialize 3 nobles
        int[] priceNoble = new int[]{1,1,0,0,0};
        Noble inputNoble = new Noble(1, 2, priceNoble);
        Noble inputNoble2 = new Noble(2, 1, priceNoble);
        Noble inputNoble3 = new Noble(3, 3, priceNoble);

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
        player1 = new Player(3, "Joel");
        playerDeck1 = new PlayerDeck(player1.getName());

        //Initialize a noble
        int[] priceNoble = new int[]{1,1,0,0,0};
        Noble inputNoble = new Noble(1, 2, priceNoble);

        //Initialize 2 developments to cause a noble visit
        int[] priceInput = new int[]{0,2,1,0,0};
        Card inputCard = new Card(1, 3, priceInput, 'W');
        Card inputCard2 = new Card(1, 3, priceInput, 'R');

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
        player1 = new Player(4, "Jane");
        playerDeck1 = new PlayerDeck(player1.getName());

        //Initialize 2 developments to cause a noble visit
        int[] priceInput = new int[]{0,2,1,0,0};
        Card inputCard = new Card(1, 3, priceInput, 'R');

        //Initialize hand gems and put into playerDeck
        int[] handGems = new int[]{0,0,1,0,0};
        playerDeck1.addHandGems(handGems);
        playerDeck1.addDevelopment(inputCard);

        //Give player a gold
        playerDeck1.addGold();

        int[] priceInput2 = new int[]{0,2,1,0,0};
        Card inputCard2 = new Card(2, 3, priceInput2, 'G');

        int retVal = playerDeck1.checkDevelopment(inputCard2);

        if(retVal>=0){
            System.out.println("Sufficient gems");
        }
        else if (retVal==-1 && playerDeck1.getGold()>0){
            System.out.println("You need 1 more gem. Use gold? (Y/N)");
            String answer = keyboard2.nextLine();
            if(answer.equals("Y")){
                playerDeck1.addDevelopment(inputCard2);
                playerDeck1.takeGold();
                System.out.println("Purchase successful");
            }
            else{
                System.out.println("Purchase failed");
            }
        }
        else{
            System.out.println("Insufficient gems");
        }
    }

    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);

        while(true){
            System.out.println("\n\nChoose a test case: " +
                    "\n a. testFormat()" +
                    "\n b. testAddingNoblesDevelopments()" +
                    "\n c. testCheckNoble()" +
                    "\n d. testCheckDevelopment()" +
                    "\n e. exit" +
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
            else{
                System.exit(0);
            }
        }
    }
}
