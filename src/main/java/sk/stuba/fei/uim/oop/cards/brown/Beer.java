package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Beer extends Card {
    private static final String CARD_NAME = "PIVO";

    public Beer() {
        super(CARD_NAME);
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players, ArrayList<Card> discardedCards) {
        player.incrementLife();
        System.out.println("HRAC " + player.getName() + " SI DOPLNIL ZIVOT, TERAZ MA " + player.getLife() + " ZIVOTY/OV!");
        return true;
    }
}
