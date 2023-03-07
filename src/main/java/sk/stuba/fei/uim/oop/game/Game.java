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
        activePlayer.printCards();
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


}
