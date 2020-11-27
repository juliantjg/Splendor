package model.data;

public class HelpPage {
    public void printHowToPlay(){
        printSign();
        printOverview();
        printHelpPart();

        System.out.println("Tip: You can always look up the commands above by entering the command “help” in-game." +
                "\n\n--Please scroll up for more content--\n");
    }

    public void printHelp(){
        System.out.println("QUICK HELP [You can find in-depth explanation of everything in the main menu 'how to play']");
        printHelpPart();
        System.out.println("--Please scroll up for more content--\n");
    }

    private void printOverview(){
        System.out.println("" +
                "- Note: I wrote this command line interface version of the original board game Splendor for fun with" +
                "\nno intention on commercial use. If you are interested, please buy the original board game published" +
                "\nby Space Cowboys: https://www.spacecowboys.fr/splendor -" +
                "\n-------------------------------------------------------------------------------------------------------" +
                "\nSplendor is a card development board game published by Space cowboys, where player would acquire/" +
                "\ncollect gems and use them to purchase gem mines (development card) that will provide the player with" +
                "\nprestige points and a gem discount (permanent gems). When you have acquired enough permanent gems, " +
                "\nyou will automatically receive a noble visit that will provide you with more prestige points. At the" +
                "\nend of the game, player with the most prestige points wins" +
                "\n" +
                "\nThe game has 2 player and 3 player modes. There will be 3 nobles and 4 each gems for TWO players mode," +
                "\nand 4 nobles and 5 each gems for a THREE players mode" +
                "\n-------------------------------------------------------------------------------------------------------" +
                "\nGame Objective:" +
                "\n" +
                "\nThe game ends when someone has acquired 15 prestige points. If it is the first player to acquire 14 " +
                "\nprestige points, the round will still run through the rest of the remaining players before it finally" +
                "\nshows the end-game results" +
                "\n" +
                "\nWinner:" +
                "\n" +
                "\nPlayer with most prestige points wins. If two players acquired equal prestige points, then the one " +
                "\nwith the least number of developments purchased wins." +
                "\n-------------------------------------------------------------------------------------------------------" +
                "\nGame Units:" +
                "\n" +
                "\n1. GEMS - Gems are the unit of currency in Splendor. There are two types of gems: HAND GEMS and" +
                "\n   PERMANENT GEMS. Hand gems can be taken from the game board and will be used to buy development" +
                "\n   cards. Permanent gems can be acquired through purchasing a development, and will be used as a " +
                "\n   discount for each development purchases. Purchasing a development will only reduce your hand gems " +
                "\n   without taking any permanent gems. Acquiring enough permanent gems will trigger a noble visit" +
                "\n     *NOTE: TOTAL GEMS is your official net worth, refer to Total Gems when you are to buy a " +
                "\n            development card. (Just refer to Total Gems when buying if you are confused)" +
                "\n" +
                "\n2. GOLD GEMS - Gold Gems are marked as 'E' and can only be acquired through reserving a card. Cannot" +
                "\n   be taken by player. A single Gold Gem can be used as a Wild Card, for instance if you need 1 more" +
                "\n   gem to pay for a development/reserve, you can use your Gold Gem." +
                "\n" +
                "\n3. NOBLES - Nobles can be found on the first section of the game board. Provides player with prestige" +
                "\n   points. Cannot be bought, as they will be given to the player once they have the sufficient number" +
                "\n   of permanent gems, and will happen automatically without player buying them." +
                "\n" +
                "\n4. DEVELOPMENTS - Developments can be found on the second section of the game board. Each development" +
                "\n   has a 'No.' assigned to it for user to identify it when buying/reserving. Can be bought using your" +
                "\n   gems (refer to TOTAL GEMS when buying developments). Each developments will provide player with " +
                "\n   a permanent gem. Some development provides prestige points while others don't" +
                "\n" +
                "\n5. PLAYER DECK - On each turn, a player will be shown their personal player deck that elaborates the" +
                "\n   player's development, noble, and reserve lists. Other players' player deck will be shown but with" +
                "\n   minimum information." +
                "\n" +
                "\n6. PRESTIGE POINTS - The end-game will be triggered when a player has acquired 15 prestige points. " +
                "\n   The rest of the round will still go on when end-game is triggered.");

    }

    private void printHelpPart(){
        System.out.println("" +
                "\n-------------------------------------------------------------------------------------------------------" +
                "\nHow to play:" +
                "\n" +
                "\nOn each turn, a player may:" +
                "\n(1) Take gems from the provided GEMS on game board (You can find the gems under the 'G E M S' section" +
                "\n    of the game board. Each player may only take 3 different gems OR 2 same gems. A player may only " +
                "\n    have up to 10 HAND GEMS at a time (Will be provided in-game)" +
                "\n" +
                "\n(2) Buy development. A player may purchase a development from the provided development cards if they " +
                "\n    have sufficient amount of gems. (You can just refer to your Total Gems when you are to purchase " +
                "\n    a new development card). Some developments provide player with prestige points while others" +
                "\n    don’t, but all developments will provide with 1 permanent gem." +
                "\n" +
                "\n(3) Reserve a development card. A player may reserve any card from the game board and receive a gold" +
                "\n    gem (if there is any on the game board). A player can have up to 3 reserves." +
                "\n" +
                "\n(4) Pay reserve. A player may pay for one of the reserves they own." +
                "\n-------------------------------------------------------------------------------------------------------" +
                "\nGameplay commands: (IMPORTANT)" +
                "\n" +
                "\n1. take" +
                "\n   The take command consists of two different versions: take 3 different gems OR take 2 same gems." +
                "\n   COMMAND:" +
                "\n     >take W R G" +
                "\n     >take W W" +
                "\n   The first one takes the gems W,R and G, while the second command takes W and W. It is important " +
                "\n   to note that you cannot take 2 same gems from a pile that has less than 4 gems in it. The format " +
                "\n   is also important as more/less space will not work." +
                "\n" +
                "\n2. buy" +
                "\n   The buy command lets player buy a development." +
                "\n   COMMAND:" +
                "\n     >buy a1" +
                "\n     >buy b1" +
                "\n     >buy c1" +
                "\n   The card no is provided under each development cards" +
                "\n" +
                "\n3. reserve" +
                "\n   The reserve command lets player reserve a development" +
                "\n   COMMAND:" +
                "\n     >reserve a1" +
                "\n     >reserve b1" +
                "\n     >reserve c1" +
                "\n   Similar to buy, card no is provided under each development" +
                "\n4. pay" +
                "\n   The pay command lets player pay for the reserves they own" +
                "\n   COMMAND:" +
                "\n     >pay 1" +
                "\n     >pay 2" +
                "\n     >pay 3" +
                "\n   The reserve no is provided in your player deck. Can be seen as: " +
                "\n        Res 1 (...) <-- the No. for this card will be 1" +
                "\n5. quit" +
                "\n   If you are bored and you want to quit" +
                "\n" +
                "\nNOTE: The commands are case-insensitive, but formatting is very important (spaces), and it is " +
                "\nimportant NOT to end every command with an extra space." +
                "\n-------------------------------------------------------------------------------------------------------");
    }

    private void printSign(){
        System.out.println("__          __  _                            _           _____       _                _            \n" +
                "\\ \\        / / | |                          | |         / ____|     | |              | |           \n" +
                " \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___  | |_ ___   | (___  _ __ | | ___ _ __   __| | ___  _ __ \n" +
                "  \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\   \\___ \\| '_ \\| |/ _ \\ '_ \\ / _` |/ _ \\| '__|\n" +
                "   \\  /\\  /  __/ | (_| (_) | | | | | |  __/ | || (_) |  ____) | |_) | |  __/ | | | (_| | (_) | |   \n" +
                "    \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/  |_____/| .__/|_|\\___|_| |_|\\__,_|\\___/|_|   \n" +
                "                                                              | |                                  \n" +
                "                                                              |_|                                  ");
    }
}
