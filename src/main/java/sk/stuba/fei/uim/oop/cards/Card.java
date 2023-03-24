package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public abstract class Card {
    private final String cardName;

    public Card(String name) {
        cardName = name;
    }

    public String getCardName() {
        return cardName;
    }

    public abstract boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players);

}

