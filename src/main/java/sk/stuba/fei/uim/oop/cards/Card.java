package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public abstract class Card {
    public abstract String getCardName();
    public abstract boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players);
}
