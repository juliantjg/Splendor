package tests;

import model.GameEngineCLI2Players;

public class TestGameEngineCLI2Players {
    private static GameEngineCLI2Players gameEngine;

    private static void testFormat(){
        gameEngine = new GameEngineCLI2Players(2, "Andy", "Bobby");
        gameEngine.playGame();
    }

    public static void main(String[] args){
        testFormat();
    }
}
