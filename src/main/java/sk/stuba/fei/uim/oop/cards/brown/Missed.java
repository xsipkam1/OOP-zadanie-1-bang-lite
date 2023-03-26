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
    public boolean equals(Object obj) {
        if (obj instanceof Missed) {
            Missed other = (Missed) obj;
            return this.getClass().equals(other.getClass());
        } else {
            return false;
        }
    }

    @Override
    public boolean action(Player player, ArrayList<Player> players, ArrayList<Card> discardedCards) {
        System.out.println("KARTU VEDLA NEMOZES ZAHRAT!");
        return false;
    }
}
