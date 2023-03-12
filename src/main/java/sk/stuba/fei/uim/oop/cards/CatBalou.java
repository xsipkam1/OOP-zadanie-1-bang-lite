package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Random;

public class CatBalou extends Card {
    private static final String CARD_NAME = "CAT BALOU";
    private final Random chooseRandomCard;

    public CatBalou() {
        chooseRandomCard=new Random();
    }

    public String getCardName(){
        return CARD_NAME;
    }

    private void pickRandomCardAndThrowItToDeck(Player chosenOpponent, ArrayList<Card> playingCards, ArrayList<Card> playerCards) {
        int chosenOpponentNumOfCards = playerCards.size();
        int randomCard = chooseRandomCard.nextInt(chosenOpponentNumOfCards);
        System.out.println("HRAC " + chosenOpponent.getName() + " PRISIEL O KARTU " + playerCards.get(randomCard).getCardName());
        chosenOpponent.throwCardToDeck(randomCard, playingCards, playerCards);
    }

    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players){
        Player chosenOpponent = player.chooseOpponent(CARD_NAME, players);

        if (chosenOpponent.getBlueCards().isEmpty() && chosenOpponent.getPlayerCards().isEmpty()) {
            System.out.println("HRAC " + chosenOpponent.getName() + " NEMA ZIADNE KARTY! TUTO KARTU NANHO TERAZ NEMOZES POUZIT!");
            return false;
        }
        else if (chosenOpponent.getBlueCards().size()>0 && chosenOpponent.getPlayerCards().size()>0){
            System.out.println("CHCES HRACOVI " + chosenOpponent.getName() + " ODSTRANIT KRATU Z RUKY ALEBO ZO STOLA?");
            System.out.println("1 z ruky");
            System.out.println("2 zo stola");
            int choice;
            do {
                choice = ZKlavesnice.readInt("Vyber si (1 alebo 2): ");
            } while(choice != 1 && choice != 2);
            pickRandomCardAndThrowItToDeck(chosenOpponent, playingCards, choice == 1 ? chosenOpponent.getPlayerCards() : chosenOpponent.getBlueCards());
            return true;
        }
        ArrayList<Card> targetCards = chosenOpponent.getBlueCards().isEmpty() ? chosenOpponent.getPlayerCards() : chosenOpponent.getBlueCards();
        pickRandomCardAndThrowItToDeck(chosenOpponent, playingCards, targetCards);
        return true;

    }
}
