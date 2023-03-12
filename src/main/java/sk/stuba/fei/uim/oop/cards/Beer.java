package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Beer extends Card {
    private static final String CARD_NAME = "PIVO";

    public String getCardName(){
        return CARD_NAME;
    }
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        player.incrementLife();
        System.out.println("HRAC " + player.getName() + " SI DOPLNIL ZIVOT, TERAZ MA " + player.getLife() + " ZIVOTY/OV!");
        return true;
    }
}
