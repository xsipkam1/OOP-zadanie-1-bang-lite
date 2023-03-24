package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Indians extends Card {
    private static final String CARD_NAME = "INDIANI";

    public Indians() {
        super(CARD_NAME);
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        ArrayList<Player> opponents = player.getOpponents(players);
        System.out.println();
        for (Player opponent : opponents) {
            int opponentBang = opponent.hasCard(Bang.class, opponent.getPlayerCards());
            if (opponentBang > -1) {
                opponent.throwCard(opponentBang, playingCards, opponent.getPlayerCards());
                System.out.println("HRAC " + opponent.getName() + " POUZIL KARTU BANG!");
            } else {
                opponent.decrementLife();
                System.out.println("HRAC " + opponent.getName() + " STRATIL ZIVOT!");
                opponent.checkLife(players, playingCards);
            }
        }
        System.out.println();
        return true;
    }
}
