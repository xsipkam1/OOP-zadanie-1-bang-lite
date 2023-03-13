package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class Prison extends Card {
    private static final String CARD_NAME = "VAZENIE";
    private final Random escapePrisonProbability;

    public Prison() {
        escapePrisonProbability=new Random();
    }

    @Override
    public String getCardName() {
        return CARD_NAME;
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        Player chosenOpponent = player.chooseOpponent(CARD_NAME, players);
        if(chosenOpponent.hasCard(Prison.class, chosenOpponent.getBlueCards()) > -1) {
            System.out.println("TENTO HRAC UZ JE VO VAZENI! TUTO KARTU NANHO NEMOZES POUZIT!");
        } else {
            player.throwCard(player.hasCard(Prison.class, player.getPlayerCards()), chosenOpponent.getBlueCards(), player.getPlayerCards());
            System.out.println("UVAZNIL SI HRACA " + chosenOpponent.getName() + "!");
        }
        return false;
    }

    public boolean escapedPrison(Player player, ArrayList<Card> playingCards) {
        player.throwCard(player.hasCard(Prison.class, player.getBlueCards()), playingCards, player.getBlueCards());
        if(escapePrisonProbability.nextInt(4) == 0) {
            System.out.println("HRACOVI " + player.getName() + " SA PODARILO UJST Z VAZENIA A ZACINA SVOJ TAH!");
        } else {
            System.out.println("HRACOVI " + player.getName() + " SA NEPODARILO UJST Z VAZENIA!");
            return false;
        }
        return true;
    }

}
