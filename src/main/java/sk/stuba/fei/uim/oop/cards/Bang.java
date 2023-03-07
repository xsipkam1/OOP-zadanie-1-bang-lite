package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Bang extends Card{
    private final String cardName = "BANG";

    @Override
    public String getCardName() {
        return cardName;
    }

    @Override
    public void action(Player player) {
        if(player.hasCard(Mancato.class)) {
            System.out.println("HRAC " + player.getName() + " SA UHOL!");
            player.discardCard(Mancato.class);
        } else {
            System.out.println("ZASAH!");
            player.decrementLife();
        }
    }
}
