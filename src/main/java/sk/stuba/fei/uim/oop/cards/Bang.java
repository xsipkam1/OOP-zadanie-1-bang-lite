package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class Bang extends Card {
    private static final String CARD_NAME = "BANG";
    private final Random barrelProbability;

    public Bang() {
        barrelProbability=new Random();
    }

    @Override
    public String getCardName() {
        return CARD_NAME;
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        Player chosenOpponent = player.chooseOpponent(CARD_NAME, players);

        if(chosenOpponent.hasCard(Barrel.class, chosenOpponent.getBlueCards())) {
            if(barrelProbability.nextInt(4) == 0) {
                System.out.println("HRAC " + chosenOpponent.getName() + " SA UHOL POMOCOU KARTY BARREL!");
                return true;
            } else {
                System.out.println("HRACOVI " + chosenOpponent.getName() + " KARTA BARREL NEZAFUNGOVALA!");
            }
        }

        if(chosenOpponent.hasCard(Missed.class, chosenOpponent.getPlayerCards())) {
            System.out.println("HRAC " + chosenOpponent.getName() + " SA UHOL POMOCOU KARTY VEDLA!");
            chosenOpponent.discardCard(Missed.class, chosenOpponent.getPlayerCards());
            playingCards.add(new Missed());
        } else {
            chosenOpponent.decrementLife();
            System.out.println("ZASAH!");
            chosenOpponent.checkLife(players, playingCards);
        }
        return true;
    }
}
