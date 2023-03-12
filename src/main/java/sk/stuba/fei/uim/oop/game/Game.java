package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {
    private final ArrayList<Card> playingCards;
    private final ArrayList<Player> players;
    private int currentPlayer;
    private final Random dynamiteProbability;

    public Game(){
        playingCards = new ArrayList<>();
        players = new ArrayList<>();
        dynamiteProbability = new Random();

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
            playerTurn(activePlayer);
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

    private void checkDynamite(Player player) {
        player.discardCard(Dynamite.class, player.getBlueCards());
        if(dynamiteProbability.nextInt(8) == 0) {
            System.out.println("HRACOVI " + player.getName() + " VYBUCHOL DYNAMIT!");
            for(int i=0; i<3; i++){
                player.decrementLife();
            }
            if(player.getLife()>0) {
                playingCards.add(new Dynamite());
            }
            player.checkLife(players, playingCards);
        } else {
            int dynamiteIndex = players.indexOf(player);
            Player previousPlayer = players.get((dynamiteIndex - 1 + players.size()) % players.size());
            previousPlayer.getBlueCards().add(new Dynamite());
            System.out.println("HRACOVI " + player.getName() + " DYNAMIT NEVYBUCHOL A POSIELA HO HRACOVI " + previousPlayer.getName());
        }
    }

    private void playerTurn(Player player) {
        System.out.println("------------------------------------------");

        if(player.hasCard(Dynamite.class, player.getBlueCards())) {
            checkDynamite(player);
            if(player.getLife()<=0) {
                return;
            }
        }
        if(player.hasCard(Prison.class, player.getBlueCards())){
            if(!player.escapedPrison(playingCards)) {
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
            }
            else if (choice == 2) {
                player.throwCardToDeck(player.chooseCard(), playingCards, player.getPlayerCards());
            }
            else if (choice == 3) {
                if(player.endTurn()){
                    break;
                }
            }
        }
    }

}
