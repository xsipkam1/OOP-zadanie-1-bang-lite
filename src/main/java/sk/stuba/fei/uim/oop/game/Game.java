package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.cards.brown.*;
import sk.stuba.fei.uim.oop.cards.blue.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private final ArrayList<Card> playingCards;
    private final ArrayList<Player> players;
    private int currentPlayer;

    public Game(){
        playingCards = new ArrayList<>();
        players = new ArrayList<>();

        initializePlayers();
        initializeCardStack();
        dealCards();
        startGame();
    }

    private void initializePlayers() {
        int numOfPlayers;
        do {
            numOfPlayers=ZKlavesnice.readInt("Zadaj pocet hracov (min 2 max 4): ");
        } while(numOfPlayers < 2 || numOfPlayers > 4);

        for(int i=0; i<numOfPlayers; i++) {
            String name = ZKlavesnice.readString("Zadaj meno hraca c." + (i+1) + ": ");
            players.add(new Player(name));
        }
    }

    private void initializeCardStack() {
        addCardsToDeck(new Bang(), 30);
        addCardsToDeck(new Missed(), 15);
        addCardsToDeck(new Beer(), 8);
        addCardsToDeck(new CatBalou(), 6);
        addCardsToDeck(new Stagecoach(), 4);
        addCardsToDeck(new Indians(), 2);
        addCardsToDeck(new Barrel(), 2);
        addCardsToDeck(new Prison(), 3);
        addCardsToDeck(new Dynamite(), 1);
    }

    private void addCardsToDeck(Card card, int amount) {
        for (int i = 0; i < amount; i++) {
            playingCards.add(card);
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

    private void startGame() {
        System.out.println("----------------HRA ZACALA----------------");
        while(players.size() != 1){
            Player activePlayer = players.get(currentPlayer);
            int numOfPlayersBeforePlayerTurn = players.size();
            playerTurn(activePlayer);
            if(numOfPlayersBeforePlayerTurn > players.size()) {
                currentPlayer = players.indexOf(activePlayer);
            }
            chooseCurrentPlayer();
        }
        System.out.println("----------------KONIEC HRY----------------");
        System.out.println("VITAZ JE " + players.get(0).getName());
    }

    private void chooseCurrentPlayer() {
        currentPlayer++;
        if(currentPlayer > players.size()-1) {
            currentPlayer=0;
        }
    }

    private void playerTurn(Player player) {
        Card dynamite = player.getCard(Dynamite.class, player.getBlueCards());
        if(dynamite != null) {
            if(((Dynamite) dynamite).checkEffect(player, playingCards, players) && player.getLife()<=0) {
                return;
            }
        }
        Card prison = player.getCard(Prison.class, player.getBlueCards());
        if(prison != null) {
            if(!((Prison) prison).checkEffect(player, playingCards, players)) {
                return;
            }
        }

        System.out.println("HRAC " + player.getName() + " ZACAL SVOJ TAH! MA ESTE " + player.getLife() + " ZIVOT/Y/OV");
        player.drawCards(playingCards);

        while (player.getPlayerCards().size()>0 && players.size()>1) {
            player.printCards();
            int choice=player.chooseAction();

            if (choice == 1) {
                player.playCard(players, playingCards);
            } else if (choice == 2) {
                player.endTurn(playingCards);
                break;
            }
        }

        if(players.size() > 1 ) {
            System.out.println("HRAC " + player.getName() + " UKONCIL SVOJ TAH, ZOSTALI MU " + player.getLife() + " ZIVOT/Y/OV!");
            System.out.println("\n------------------------------------------\n");
        }

    }

}
