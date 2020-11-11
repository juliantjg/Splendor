package tests;

import model.Card;
import model.Noble;
import model.Player;
import model.PlayerDeck;

public class TestPrintPlayerDeck {
    private static Player player1, player2;
    private static PlayerDeck playerDeck1, playerDeck2;

    private static void testFormat(){
        player1 = new Player(1, "Jul");

        playerDeck1 = new PlayerDeck(player1.getName());

        int[] priceInput = new int[]{0,2,1,0,0};
        Card inputCard = new Card(1, 3, priceInput, 'G');

        int[] priceNoble = new int[]{1,1,0,0,0};
        Noble inputNoble = new Noble(1, 5, priceNoble);

        playerDeck1.addDevelopment(inputCard);
        playerDeck1.addNoble(inputNoble);

        int[] handGems = new int[]{1,0,2,3,0};
        playerDeck1.addHandGems(handGems);

        playerDeck1.printPublicDeck();

        System.out.println("\n");

        playerDeck1.printPersonalDeck();
    }


    public static void main(String[] args){
        testFormat();
    }
}
