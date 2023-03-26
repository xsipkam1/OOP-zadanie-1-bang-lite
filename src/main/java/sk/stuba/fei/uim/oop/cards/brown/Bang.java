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
    public boolean equals(Object obj) {
        if (obj instanceof Bang) {
            Bang other = (Bang) obj;
            return this.getClass().equals(other.getClass());
        } else {
            return false;
        }
    }

    @Override
    public boolean action(Player player, ArrayList<Player> players, ArrayList<Card> discardedCards) {
        Player chosenOpponent = player.chooseOpponent(CARD_NAME, players);

        Card barrel = chosenOpponent.getBarrel(chosenOpponent.getBlueCards());
        if (barrel != null) {
            if (((Barrel) barrel).checkEffect(chosenOpponent, discardedCards, players)) {
                return true;
            }
        }

        int opponentMissed = chosenOpponent.getPlayerCards().indexOf(new Missed());
        if (opponentMissed > -1) {
            System.out.println("HRAC " + chosenOpponent.getName() + " SA UHOL POMOCOU KARTY VEDLA!");
            chosenOpponent.throwCard(opponentMissed, discardedCards, chosenOpponent.getPlayerCards());
        } else {
            chosenOpponent.decrementLife();
            System.out.println("ZASAH!");
            chosenOpponent.checkLife(players, discardedCards);
        }
        return true;
    }
}
