package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Bang extends Card {
    private static final String CARD_NAME = "BANG";

    public Bang() {
        super(CARD_NAME);
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        Player chosenOpponent = player.chooseOpponent(CARD_NAME, players);

        Card barrel = chosenOpponent.getCard(Barrel.class, chosenOpponent.getBlueCards());
        if (barrel != null) {
            if (((Barrel) barrel).checkEffect(chosenOpponent, playingCards, players)) {
                return true;
            }
        }

        int opponentMissed = chosenOpponent.hasCard(Missed.class, chosenOpponent.getPlayerCards());
        if (opponentMissed > -1) {
            System.out.println("HRAC " + chosenOpponent.getName() + " SA UHOL POMOCOU KARTY VEDLA!");
            chosenOpponent.throwCard(opponentMissed, playingCards, chosenOpponent.getPlayerCards());
        } else {
            chosenOpponent.decrementLife();
            System.out.println("ZASAH!");
            chosenOpponent.checkLife(players, playingCards);
        }
        return true;
    }
}
