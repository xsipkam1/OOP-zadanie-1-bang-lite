package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Missed extends Card {
    private static final String CARD_NAME = "VEDLA";

    public Missed() {
        super(CARD_NAME);
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        System.out.println("KARTU VEDLA NEMOZES ZAHRAT!");
        return false;
    }
}
