package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Player {
    private int life;
    private final String name;
    private ArrayList<Card> playerCards;
    private final ArrayList<Card> blueCards;

    public Player(String name) {
        this.life = 4;
        this.name = name;
        this.playerCards = new ArrayList<>();
        this.blueCards = new ArrayList<>();
    }

    public int getLife() {
        return this.life;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Card> getPlayerCards() {
        return this.playerCards;
    }

    public ArrayList<Card> getBlueCards() {
        return this.blueCards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.playerCards = cards;
    }

    public void decrementLife() {
        this.life--;
    }

    public void incrementLife() {
        this.life++;
    }

    public void addCard(Card card) {
        this.playerCards.add(card);
    }

    public int hasCard(Class cardClass, ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (cardClass.isInstance(card)) {
                return i;
            }
        }
        return -1;
    }

    public Card getCard(Class cardClass, ArrayList<Card> cards) {
        for (Card card : cards) {
            if (cardClass.isInstance(card)) {
                return card;
            }
        }
        return null;
    }

    public void printCards() {
        System.out.println("\nHRAC " + this.getName() + " MA V RUKE TIETO KARTY: ");
        for (int i = 0; i < this.getPlayerCards().size(); i++) {
            System.out.println((i + 1) + " " + this.getPlayerCards().get(i).getCardName());
        }
        if (this.getBlueCards().size() > 0) {
            System.out.println("PRED SEBOU MA TIETO MODRE KARTY: ");
            for (int i = 0; i < this.getBlueCards().size(); i++) {
                System.out.println((i + 1) + " " + this.getBlueCards().get(i).getCardName());
            }
        }
    }

    public void removeCards(ArrayList<Card> playingCards, ArrayList<Card> playerCards) {
        for (int i = 0; i < playerCards.size(); i++) {
            throwCard(i, playingCards, playerCards);
        }
    }

    public void drawCards(ArrayList<Card> playingCards) {
        System.out.println("HRAC " + this.getName() + " SI TAHA 2 KARTY!");
        for (int i = 0; i < 2; i++) {
            this.addCard(playingCards.remove(0));
        }
    }

    public void checkLife(ArrayList<Player> players, ArrayList<Card> playingCards) {
        if (this.getLife() <= 0) {
            System.out.println("\n!!! HRAC " + this.getName() + " JE MRTVY !!!\n");
            this.removeCards(playingCards, this.getPlayerCards());
            this.removeCards(playingCards, this.getBlueCards());
            players.remove(this);
        } else {
            System.out.println("HRAC " + this.getName() + " MA ESTE " + this.getLife() + " ZIVOT/Y!");
        }
    }

    public int chooseCard() {
        int choiceCard;
        do {
            choiceCard = ZKlavesnice.readInt("Ktoru kartu chces pouzit? (cislo 1 az " + this.getPlayerCards().size() + ") ");
        } while (choiceCard < 1 || choiceCard > this.getPlayerCards().size());
        return choiceCard - 1;
    }

    public ArrayList<Player> getOpponents(ArrayList<Player> players) {
        ArrayList<Player> opponents = new ArrayList<>();
        for (Player player : players) {
            if (player != this) {
                opponents.add(player);
            }
        }
        return opponents;
    }

    public void printOpponents(ArrayList<Player> opponents) {
        System.out.println("V HRE SU TITO HRACI: ");
        int index = 1;
        for (Player opponent : opponents) {
            System.out.println((index) + " " + opponent.getName());
            index++;
        }
    }

    public Player chooseOpponent(String choiceCard, ArrayList<Player> players) {
        ArrayList<Player> opponents = this.getOpponents(players);
        printOpponents(opponents);
        int choiceOpponent;
        do {
            choiceOpponent = ZKlavesnice.readInt("Na ktoreho hraca chces kartu " + choiceCard + " zahrat? (cislo 1 az " + opponents.size() + ") ");
        } while (choiceOpponent < 1 || choiceOpponent > opponents.size());
        return opponents.get(choiceOpponent - 1);
    }

    public int chooseAction() {
        System.out.println();
        System.out.println("1 zahrat kartu");
        System.out.print("2 ukoncit tah");
        if (this.getPlayerCards().size() > this.getLife()) {
            System.out.print(" (zahrna vyhadzovanie prebytocnych kariet)");
        }
        System.out.println();

        int choice;
        do {
            choice = ZKlavesnice.readInt("Vyber si svoj tah (cislo 1 az 2) ");
        } while (choice != 1 && choice != 2);
        return choice;
    }

    public void playCard(ArrayList<Player> players, ArrayList<Card> playingCards) {
        int choiceCard = this.chooseCard();
        Card card = this.getPlayerCards().get(choiceCard);
        if (card.action(this, playingCards, players)) {
            this.throwCard(choiceCard, playingCards, this.getPlayerCards());
        }
    }

    public void endTurn(ArrayList<Card> playingCards) {
        if (this.getPlayerCards().size() <= this.getLife()) {
            return;
        }
        while (this.getPlayerCards().size() > this.getLife()) {
            System.out.println("------------------------------------------");
            System.out.println("Z RUKY MUSIS VYHODIT ESTE " + (this.getPlayerCards().size() - this.getLife()) + " KARTY/U");
            this.printCards();
            this.throwCard(this.chooseCard(), playingCards, this.getPlayerCards());
        }
    }

    public void throwCard(int choiceCard, ArrayList<Card> playingCards, ArrayList<Card> playerCards) {
        playingCards.add(playerCards.remove(choiceCard));
    }

}
