package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Random;

public abstract class BlueCard extends Card {
    protected final Random blueCardProbability;

    public BlueCard(String name) {
        super(name);
        blueCardProbability = new Random();
    }

    public abstract boolean checkEffect(Player player, ArrayList<Card> playingCards, ArrayList<Player> players);
}
