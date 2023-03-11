package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Dynamite extends Card {
    private static final String CARD_NAME = "DYNAMIT";

    @Override
    public String getCardName() {
        return CARD_NAME;
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        player.discardCard(Dynamite.class, player.getPlayerCards());
        player.getBlueCards().add(new Dynamite());
        System.out.println("VYLOZIL SI PRED SEBA DYNAMIT!");
        return false;
    }
}
