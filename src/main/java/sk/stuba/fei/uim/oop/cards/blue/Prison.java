package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Prison extends BlueCard {
    private static final String CARD_NAME = "VAZENIE";

     public Prison() {
        super(CARD_NAME);
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        Player chosenOpponent = player.chooseOpponent(CARD_NAME, players);
        if(chosenOpponent.hasCard(Prison.class, chosenOpponent.getBlueCards()) > -1) {
            System.out.println("HRAC " + chosenOpponent.getName() + " UZ JE VO VAZENI! TUTO KARTU NANHO NEMOZES POUZIT!");
        } else {
            player.throwCard(player.hasCard(Prison.class, player.getPlayerCards()), chosenOpponent.getBlueCards(), player.getPlayerCards());
            System.out.println("UVAZNIL SI HRACA " + chosenOpponent.getName() + "!");
        }
        return false;
    }

    @Override
    public boolean checkEffect(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        player.throwCard(player.hasCard(Prison.class, player.getBlueCards()), playingCards, player.getBlueCards());
        if(blueCardProbability.nextInt(4) == 0) {
            System.out.println("HRACOVI " + player.getName() + " SA PODARILO UJST Z VAZENIA A ZACINA SVOJ TAH!");
            return true;
        }
        System.out.println("HRACOVI " + player.getName() + " SA NEPODARILO UJST Z VAZENIA!");
        return false;
    }

}
