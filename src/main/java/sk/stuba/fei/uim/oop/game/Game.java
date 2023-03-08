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
        System.out.println("---------------HRA ZACALA------------------");
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
                opponents.add(players.get(i));
            }
        }
        return opponents;
    }

    private void printOpponents(ArrayList<Player> opponents) {
        System.out.println("V HRE SU TITO HRACI: ");
        int index=1;
        for(Player opponent : opponents) {
            System.out.println((index) + " " + opponent.getName());
            index++;
        }
    }

    private void checkLife(Player player) {
        if(player.getLife()<=0) {
            System.out.println("HRAC " + player.getName() + " JE MRTVY!");
            players.remove(player);
            removeCards(player);
        }
        else {
            System.out.println("HRAC " + player.getName() + " MA ESTE " + player.getLife() + " ZIVOTY!");
        }
    }

    private void removeCards(Player player) {
        for(int i=0; i<player.getPlayerCards().size(); i++) {
            discardedCards.add(player.getPlayerCards().remove(i));
        }
    }

    private int chooseCard(Player player) {
        int choiceCard;
        do {
            choiceCard=ZKlavesnice.readInt("Ktoru kartu chces pouzit? (cislo 1 az " + player.getPlayerCards().size() + ") ");
        } while(choiceCard < 1 || choiceCard > player.getPlayerCards().size());
        return choiceCard;
    }

    private Player chooseOpponent(Player player, int choiceCard) {
        ArrayList<Player> opponents = getOpponents(player);
        printOpponents(opponents);
        int choiceOpponent;
        do {
            choiceOpponent=ZKlavesnice.readInt("Na ktoreho hraca chces kartu " + player.getPlayerCards().get(choiceCard-1).getCardName() + " zahrat? (cislo 1 az " + opponents.size() + ") ");
        } while(choiceOpponent < 1 || choiceOpponent > players.size());
        Player opponent = opponents.get(choiceOpponent-1);
        return opponent;
    }

    private void drawCards(Player player) {
        System.out.println("HRAC " + player.getName() + " SI TAHA 2 KARTY!");
        for(int i=0; i<2; i++) {
            player.addCard(playingCards.remove(0));
        }
    }

    private void playerTurn(Player player) {
        System.out.println("-------------------------------------------");
        System.out.println("HRAC " + player.getName() + " ZACAL SVOJ TAH!");
        drawCards(player);

        while (true) {
            printCards(player);
            System.out.println();
            System.out.println("1 zahrat kartu");
            System.out.println("2 zahodit kartu");
            System.out.println("3 ukoncit tah");

            int choice;
            do {
                choice=ZKlavesnice.readInt("Vyber si svoj tah (cislo 1 az 3) ");
            } while(choice != 1 && choice != 2 && choice != 3);

            if (choice==1) {
                int choiceCard = chooseCard(player);
                Player choiceOpponent = chooseOpponent(player, choiceCard);
                Card card = player.getPlayerCards().get(choiceCard-1);
                int choiceOpponentLivesBeforePlayerTurn = choiceOpponent.getLife();

                if (card.action(choiceOpponent)) {
                    player.discardCard(card.getClass());
                    if (choiceOpponent.getLife() < choiceOpponentLivesBeforePlayerTurn) {
                        checkLife(choiceOpponent);
                    }
                }
            }
            else if (choice==2) {
                int choiceCard = chooseCard(player);
                player.getPlayerCards().remove(choiceCard-1);
            }
            else if (choice == 3) {
                if(player.getPlayerCards().size()>player.getLife()){
                    System.out.println("NEMOZES SKONCIT TAH LEBO MAS VIAC KARIET AKO ZIVOTOV! (mas "+player.getLife()+" zivoty)");
                    continue;
                }
                System.out.println("HRAC " + player.getName() + " UKONCIL SVOJ TAH, ZOSTALI MU " + player.getLife() + " ZIVOTY!");
                break;
            }

        }
        //discardedCards.add(card);
        //printCards(player);
    }
}
