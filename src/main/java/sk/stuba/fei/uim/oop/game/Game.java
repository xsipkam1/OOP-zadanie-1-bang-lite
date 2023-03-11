package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {
    private ArrayList<Card> playingCards;
    private ArrayList<Player> players;
    private int currentPlayer;

    public Game(){
        playingCards = new ArrayList<>();
        players = new ArrayList<>();
        int numOfPlayers;
        do {
            numOfPlayers=ZKlavesnice.readInt("Zadaj pocet hracov (min 2 max 4): ");
        } while(numOfPlayers < 2 || numOfPlayers > 4);

        for(int i=0; i<numOfPlayers; i++) {
            String name = ZKlavesnice.readString("Zadaj meno hraca c." + (i+1) + ": ");
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
        System.out.println("----------------KONIEC HRY----------------");
        System.out.println("VITAZ JE " + players.get(0).getName());
    }

    private void initializeCardStack() {
        addCardsToDeck(playingCards, new Bang(), 20);
        addCardsToDeck(playingCards, new Missed(), 10);
        addCardsToDeck(playingCards, new Beer(), 8);
        addCardsToDeck(playingCards, new CatBalou(), 6);
        addCardsToDeck(playingCards, new Stagecoach(), 4);
        addCardsToDeck(playingCards, new Indians(), 2);
        addCardsToDeck(playingCards, new Barrel(), 2);
        addCardsToDeck(playingCards, new Prison(), 3);
        addCardsToDeck(playingCards, new Dynamite(), 1);
    }

    private void addCardsToDeck(ArrayList<Card> playingCards, Card card, int amount) {
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

    private void chooseCurrentPlayer() {
        currentPlayer++;
        if(currentPlayer > players.size()-1) {
            currentPlayer=0;
        }
    }

    private void checkDynamite(Player player, ArrayList<Card> playingCards) {
        player.discardCard(Dynamite.class, player.getBlueCards());
        if(new Random().nextInt(8) == 0) {
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
            checkDynamite(player, playingCards);
            if(player.getLife()<=0) return;
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
                int choiceCard = player.chooseCard();
                Card card = player.getPlayerCards().get(choiceCard-1);
                if(card.action(player, playingCards, players)){
                    player.throwCardToDeck(choiceCard, playingCards, player.getPlayerCards());
                }
            }
            else if (choice == 2) {
                int choiceCard = player.chooseCard();
                player.throwCardToDeck(choiceCard, playingCards, player.getPlayerCards());
            }
            else if (choice == 3) {
                if(player.getPlayerCards().size()>player.getLife()){
                    System.out.println("NEMOZES SKONCIT TAH LEBO MAS VIAC KARIET AKO ZIVOTOV! (mas " + player.getLife() + " zivot/y/ov)");
                    continue;
                }
                System.out.println("HRAC " + player.getName() + " UKONCIL SVOJ TAH, ZOSTALI MU " + player.getLife() + " ZIVOT/Y/OV!");
                break;
            }

        }
    }
}
