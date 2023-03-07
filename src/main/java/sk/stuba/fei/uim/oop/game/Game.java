package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private ArrayList<Card> playingCards;
    private ArrayList<Card> discardedCards;
    private ArrayList<Player> players;
    private int currentPlayer;

    public Game(){
        playingCards = new ArrayList<>();
        players = new ArrayList<>();

        int numPlayers;
        do {
            numPlayers=ZKlavesnice.readInt("Zadaj pocet hracov (min 2 max 4): ");
        } while(numPlayers < 2 || numPlayers > 4);

        for(int i=0; i<numPlayers; i++) {
            String name = ZKlavesnice.readString("Zadaj meno hraca c." + (i+1) + ": ");
            Player player = new Player(name);
            players.add(new Player(name));
        }

        initializeCardStack();
        dealCards();
        startGame();
    }

    private void startGame() {
        System.out.println("--- GAME STARTED ---");
        Player activePlayer = players.get(currentPlayer);
        playerTurn(activePlayer);
    }

    private void initializeCardStack() {
        for (int i = 0; i < 10; i++) {
            playingCards.add(new Bang());
        }
        for (int i = 0; i < 5; i++) {
            playingCards.add(new Mancato());
        }
    }

    private void dealCards() {
        Collections.shuffle(playingCards);
        for (Player player : players) {
            ArrayList<Card> newCards = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                newCards.add(playingCards.remove(0));
            }
            player.setCards(newCards);
        }
    }

    private void printCards(Player player) {
        System.out.println("HRAC " + player.getName() + " MA TIETO KARTY: ");
        for(int i=0; i<player.getPlayerCards().size(); i++){
            System.out.println((i+1) + " " + player.getPlayerCards().get(i).getCardName());
        }
    }

    private ArrayList<Player> getOpponents(Player player){
        ArrayList<Player> opponents = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != player) {
                Player originalElement = players.get(i);
                Player copiedElement = new Player(players.get(i).getName());
                opponents.add(copiedElement);
            }
        }
        return opponents;
    }
    private void printPlayers(ArrayList<Player> opponents) {
        System.out.println("V HRE SU TITO HRACI: ");
        int index=1;
        for(Player opponent : opponents) {
            System.out.println((index) + " " + opponent.getName());
            index++;
        }
    }

    private void playerTurn(Player player) {
        printCards(player);
        int choiceCard;
        do {
            choiceCard=ZKlavesnice.readInt("Ktoru kartu chces zahrat? (cislo 1 az " + player.getPlayerCards().size() + ") ");
        } while(choiceCard < 1 || choiceCard > player.getPlayerCards().size());

        ArrayList<Player> opponents = getOpponents(player);
        printPlayers(opponents);
        int choicePlayer;
        do {
            choicePlayer=ZKlavesnice.readInt("Na ktoreho hraca chces kartu " + player.getPlayerCards().get(choiceCard-1).getCardName() + " zahrat? (cislo 1 az " + opponents.size() + ") ");
        } while(choicePlayer < 1 || choicePlayer > players.size());

        Card card = player.getPlayerCards().get(choiceCard-1);
        Player opponent = opponents.get(choicePlayer-1);
        card.action(opponent);

        //discardedCards.add();
        player.discardCard(card.getClass());
        //printCards(player);

    }
}
