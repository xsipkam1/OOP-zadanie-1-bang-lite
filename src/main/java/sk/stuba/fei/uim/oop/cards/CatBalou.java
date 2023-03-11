package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Random;

public class CatBalou extends Card {
    private static final String CARD_NAME = "CAT BALOU";

    public String getCardName(){
        return CARD_NAME;
    }

    public void pickRandomCardAndThrowItToDeck(Player chosenOpponent, ArrayList<Card> playingCards, ArrayList<Card> playerCards) {
        Random random = new Random();
        int chosenOpponentNumOfCards = playerCards.size();
        int randomCard = random.nextInt(chosenOpponentNumOfCards);
        System.out.println("HRAC " + chosenOpponent.getName() + " PRISIEL O KARTU " + playerCards.get(randomCard).getCardName());
        chosenOpponent.throwCardToDeck(randomCard+1, playingCards, playerCards);
    }

    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players){
        Player chosenOpponent = player.chooseOpponent(CARD_NAME, players);

        if(chosenOpponent.getBlueCards().size()>0 && chosenOpponent.getPlayerCards().size()>0) {
            System.out.println("CHCES HRACOVI " + chosenOpponent.getName() + " ODSTRANIT KRATU Z RUKY ALEBO ZO STOLA?");
            System.out.println("1 z ruky");
            System.out.println("2 zo stola");
            int choice;
            do {
                choice = ZKlavesnice.readInt("Vyber si (1 alebo 2): ");
            } while(choice != 1 && choice != 2);

            if(choice == 1) {
                pickRandomCardAndThrowItToDeck(chosenOpponent, playingCards, chosenOpponent.getPlayerCards());
                return true;
            } else {
                pickRandomCardAndThrowItToDeck(chosenOpponent, playingCards, chosenOpponent.getBlueCards());
                return true;
            }
        } else if (chosenOpponent.getBlueCards().size()>0 && chosenOpponent.getPlayerCards().size()<1) {
            pickRandomCardAndThrowItToDeck(chosenOpponent, playingCards, chosenOpponent.getBlueCards());
            return true;
        } else if (chosenOpponent.getBlueCards().size()<1 && chosenOpponent.getPlayerCards().size()>0) {
            pickRandomCardAndThrowItToDeck(chosenOpponent, playingCards, chosenOpponent.getPlayerCards());
            return true;
        }
        System.out.println("HRAC " + chosenOpponent.getName() + " NEMA ZIADNE KARTY! TUTO KARTU NANHO TERAZ NEMOZES POUZIT!");
        return false;
    }
}
