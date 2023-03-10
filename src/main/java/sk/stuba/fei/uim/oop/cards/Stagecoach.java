package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;


public class Stagecoach extends Card {
    private static final String CARD_NAME = "DOSTAVNIK";

    public  String getCardName(){
        return CARD_NAME;
    }
    public  boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players){
        System.out.println("HRAC " + player.getName() + " POUZIL KARTU DOSTAVNIK!");
        player.drawCards(playingCards);
        return true;
    }
}
