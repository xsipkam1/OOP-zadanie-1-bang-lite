package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Stagecoach extends Card {
    private static final String CARD_NAME = "DOSTAVNIK";
    private final ArrayList<Card> playingCards;

    public Stagecoach(ArrayList<Card> playingCards) {
        super(CARD_NAME);
        this.playingCards = playingCards;
    }

    @Override
    public boolean action(Player player, ArrayList<Player> players, ArrayList<Card> discardedCards) {
        System.out.println("HRAC " + player.getName() + " POUZIL KARTU DOSTAVNIK!");
        player.drawCards(playingCards, discardedCards);
        return true;
    }
}
