package model;

public class Player {
    private String name;
    private int playerNo;
    private PlayerDeck playerDeck;
    private int[] gems;

    public Player(int no, String name){
        this.playerNo = no;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public PlayerDeck getPlayerDeck() {
        return playerDeck;
    }

    public void setPlayerDeck(PlayerDeck playerDeck) {
        this.playerDeck = playerDeck;
    }
}
