package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private ArrayList<Card> playingCards;
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
        System.out.println("----------------HRA ZACALA----------------");
        while(players.size() != 1){
            Player activePlayer = players.get(currentPlayer);
            playerTurn(activePlayer);
            chooseCurrentPlayer();
        }
        System.out.println("VITAZ JE " + players.get(0).getName());
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

    private void chooseCurrentPlayer() {
        currentPlayer++;
        currentPlayer %= players.size();
    }

    private void playerTurn(Player player) {
        System.out.println("------------------------------------------");
        System.out.println("HRAC " + player.getName() + " ZACAL SVOJ TAH!");
        player.drawCards(playingCards);

        while (player.getPlayerCards().size()>0 && players.size()>1) {
            player.printCards();
            int choice=player.chooseAction();

            if (choice == 1) {
                int choiceCard = player.chooseCard();
                Player choiceOpponent = player.chooseOpponent(choiceCard, players);
                Card card = player.getPlayerCards().get(choiceCard-1);
                int choiceOpponentLivesBeforePlayerTurn = choiceOpponent.getLife();

                if (card.action(choiceOpponent, playingCards)) {
                    playingCards.add(player.getPlayerCards().remove(choiceCard-1));
                    if (choiceOpponent.getLife() < choiceOpponentLivesBeforePlayerTurn) {
                        choiceOpponent.checkLife(players, playingCards);
                    }
                }
            }
            else if (choice == 2) {
                int choiceCard = player.chooseCard();
                playingCards.add(player.getPlayerCards().remove(choiceCard-1));
            }
            else if (choice == 3) {
                if(player.getPlayerCards().size()>player.getLife()){
                    System.out.println("NEMOZES SKONCIT TAH LEBO MAS VIAC KARIET AKO ZIVOTOV! (mas " + player.getLife() + " zivot/y)");
                    continue;
                }
                System.out.println("HRAC " + player.getName() + " UKONCIL SVOJ TAH, ZOSTALI MU " + player.getLife() + " ZIVOT/Y!");
                break;
            }

        }
    }
}
