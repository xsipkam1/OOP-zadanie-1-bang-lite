package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Barrel extends BlueCard {
    private static final String CARD_NAME = "BARREL";

    public Barrel() {
        super(CARD_NAME);
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        if (player.hasCard(Barrel.class, player.getBlueCards()) > -1) {
            System.out.println("UZ PRED SEBOU JEDEN BARREL MAS!");
        } else {
            player.throwCard(player.hasCard(Barrel.class, player.getPlayerCards()), player.getBlueCards(), player.getPlayerCards());
            System.out.println("VYLOZIL SI PRED SEBA BARREL!");
        }
        return false;
    }

    @Override
    public boolean checkEffect(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        if (blueCardProbability.nextInt(4) == 0) {
            System.out.println("HRAC " + player.getName() + " SA UHOL POMOCOU KARTY BARREL!");
            return true;
        }
        System.out.println("HRACOVI " + player.getName() + " KARTA BARREL NEZAFUNGOVALA!");
        return false;
    }
}
