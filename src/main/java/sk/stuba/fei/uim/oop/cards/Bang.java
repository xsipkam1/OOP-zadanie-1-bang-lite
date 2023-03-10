package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Bang extends Card{
    private static final String CARD_NAME = "BANG";

    @Override
    public String getCardName() {
        return CARD_NAME;
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        Player chosenOpponent = player.chooseOpponent(CARD_NAME, players);

        if(chosenOpponent.hasCard(Missed.class)) {
            System.out.println("HRAC " + chosenOpponent.getName() + " SA UHOL!");
            chosenOpponent.discardCard(Missed.class);
            playingCards.add(new Missed());
        } else {
            System.out.println("ZASAH!");
            chosenOpponent.decrementLife();
            chosenOpponent.checkLife(players, playingCards);
        }
        return true;
    }
}
