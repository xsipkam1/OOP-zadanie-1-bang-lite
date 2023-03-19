package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public abstract class Card {
    protected String name;

    public Card(String name) {
        this.name = name;
    }

    public abstract boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players);

    public String getCardName() {
        return name;
    }

}

