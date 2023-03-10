package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Indians extends Card {
    private static final String CARD_NAME = "INDIANI";

    @Override
    public String getCardName() {
        return CARD_NAME;
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        ArrayList<Player> opponents = player.getOpponents(players);
        for(Player opponent : opponents) {
            if(opponent.hasCard(Bang.class)) {
                opponent.discardCard(Bang.class);
                System.out.println("HRAC " + opponent.getName() + " POUZIL KARTU BANG!");
                playingCards.add(new Bang());
            }
            else {
                opponent.decrementLife();
                System.out.println("HRAC " + opponent.getName() + " STRATIL ZIVOT!");
                opponent.checkLife(players, playingCards);
            }
        }
        return true;
    }
}
