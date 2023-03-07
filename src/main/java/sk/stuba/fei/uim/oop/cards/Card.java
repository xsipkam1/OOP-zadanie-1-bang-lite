package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public abstract class Card {
    //protected String color;
    public abstract String getCardName();
    public abstract void action(Player player);
}
