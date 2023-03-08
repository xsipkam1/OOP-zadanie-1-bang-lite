package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Mancato extends Card{
    private final String CARD_NAME = "VEDLA";

    @Override
    public String getCardName() {
        return CARD_NAME;
    }
    @Override
    public boolean action(Player player, ArrayList<Card> playingCards) {
        System.out.println("KARTU VEDLA NEMOZES ZAHRAT!");
        return false;
    }
}
