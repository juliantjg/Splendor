package model;

public class Player {
    String name;
    int playerNo;
    int prestige;
    PlayerDeck playerDeck;
    int[] gems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void setPlayerNo(int playerNo) {
        this.playerNo = playerNo;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public PlayerDeck getPlayerDeck() {
        return playerDeck;
    }

    public void setPlayerDeck(PlayerDeck playerDeck) {
        this.playerDeck = playerDeck;
    }
}
