package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class CatBalou extends Card {
    private static final String CARD_NAME = "CAT BALOU";

    public  String getCardName(){
        return CARD_NAME;
    }
    public  boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players){
        Player chosenOpponent = player.chooseOpponent(CARD_NAME, players);
        int chosenOpponentNumOfCards = chosenOpponent.getPlayerCards().size();
        if (chosenOpponentNumOfCards==0) {
            System.out.println("HRAC " + chosenOpponent.getName() + " NEMA ZIADNE KARTY! TUTO KARTU NANHO TERAZ NEMOZES POUZIT!");
            return false;
        } else {
            Random random = new Random();
            int randomCard = random.nextInt(chosenOpponentNumOfCards);
            System.out.println("HRAC " + chosenOpponent.getName() + " PRISIEL O KARTU " + chosenOpponent.getPlayerCards().get(randomCard).getCardName());
            chosenOpponent.getPlayerCards().remove(randomCard);
            playingCards.add(new CatBalou());
        }
        return true;
    }
}
