package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Bang extends Card{
    private final String CARD_NAME = "BANG";

    @Override
    public String getCardName() {
        return CARD_NAME;
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards) {
        if(player.hasCard(Mancato.class)) {
            System.out.println("HRAC " + player.getName() + " SA UHOL!");
            player.discardCard(Mancato.class);
            playingCards.add(new Mancato());
        } else {
            System.out.println("ZASAH!");
            player.decrementLife();
        }
        return true;
    }
}
