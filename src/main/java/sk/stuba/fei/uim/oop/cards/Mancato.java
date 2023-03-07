package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Mancato extends Card{
    private final String cardName = "VEDLA";

    @Override
    public String getCardName() {
        return cardName;
    }
    @Override
    public void action(Player player) {

    }
}
