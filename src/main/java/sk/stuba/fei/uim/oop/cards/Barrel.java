package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Barrel extends Card {
    private static final String CARD_NAME = "BARREL";

    @Override
    public String getCardName() {
        return CARD_NAME;
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        if(player.hasCard(Barrel.class, player.getBlueCards())){
            System.out.println("UZ PRED SEBOU JEDEN BARREL MAS!");
        } else {
            player.getBlueCards().add(new Barrel());
            player.discardCard(Barrel.class, player.getPlayerCards());
            System.out.println("VYLOZIL SI PRED SEBA BARREL!");
        }
        return false;
    }
}
