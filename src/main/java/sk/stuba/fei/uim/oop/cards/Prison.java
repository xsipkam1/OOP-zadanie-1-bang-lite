package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Prison extends Card {
    private static final String CARD_NAME = "VAZENIE";

    @Override
    public String getCardName() {
        return CARD_NAME;
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        Player chosenOpponent = player.chooseOpponent(CARD_NAME, players);
        if(chosenOpponent.hasCard(Prison.class, chosenOpponent.getBlueCards())) {
            System.out.println("TENTO HRAC UZ JE VO VAZENI! TUTO KARTU NANHO NEMOZES POUZIT!");
        } else {
            player.discardCard(Prison.class, player.getPlayerCards());
            chosenOpponent.getBlueCards().add(new Prison());
            System.out.println("UVAZNIL SI HRACA " + chosenOpponent.getName() + "!");
        }
        return false;
    }
}
