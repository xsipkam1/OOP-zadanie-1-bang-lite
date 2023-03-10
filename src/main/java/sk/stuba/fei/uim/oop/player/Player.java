package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Player {
    private int life;
    private String name;
    private ArrayList<Card> playerCards;

    public Player(String name) {
        this.life=4;
        this.name=name;
        this.playerCards=new ArrayList<>();
    }

    public int getLife() {
        return this.life;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Card> getPlayerCards(){
        return this.playerCards;
    }

    public void setCards(ArrayList<Card> cards){
        this.playerCards=cards;
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

    public boolean hasCard(Class<? extends Card> cardClass) {
        for (Card i : playerCards) {
            if (cardClass.isAssignableFrom(i.getClass())) {
                return true;
            }
        }
        return false;
    }

    public void printCards() {
        System.out.println("HRAC " + this.getName() + " MA TIETO KARTY: ");
        for(int i=0; i<this.getPlayerCards().size(); i++){
            System.out.println((i+1) + " " + this.getPlayerCards().get(i).getCardName());
        }
    }

    public void removeCards(ArrayList<Card> playingCards) {
        for(int i=0; i<this.getPlayerCards().size(); i++) {
            playingCards.add(this.getPlayerCards().remove(i));
        }
    }

    public void drawCards(ArrayList<Card> playingCards) {
        System.out.println("HRAC " + this.getName() + " SI TAHA 2 KARTY!");
        for(int i=0; i<2; i++) {
            this.addCard(playingCards.remove(0));
        }
    }

    public void checkLife(ArrayList<Player> players, ArrayList<Card> playingCards) {
        if(this.getLife()<=0) {
            System.out.println("HRAC " + this.getName() + " JE MRTVY!");
            this.removeCards(playingCards);
            players.remove(this);
        }
        else {
            System.out.println("HRAC " + this.getName() + " MA ESTE " + this.getLife() + " ZIVOT/Y!");
        }
    }

    public int chooseCard() {
        int choiceCard;
        do {
            choiceCard= ZKlavesnice.readInt("Ktoru kartu chces pouzit? (cislo 1 az " + this.getPlayerCards().size() + ") ");
        } while(choiceCard < 1 || choiceCard > this.getPlayerCards().size());
        return choiceCard;
    }

    public ArrayList<Player> getOpponents(ArrayList<Player> players){
        ArrayList<Player> opponents = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != this) {
                opponents.add(players.get(i));
            }
        }
        return opponents;
    }

    public void printOpponents(ArrayList<Player> opponents) {
        System.out.println("V HRE SU TITO HRACI: ");
        int index=1;
        for(Player opponent : opponents) {
            System.out.println((index) + " " + opponent.getName());
            index++;
        }
    }

    public Player chooseOpponent(String choiceCard, ArrayList<Player> players) {
        ArrayList<Player> opponents = this.getOpponents(players);
        printOpponents(opponents);
        int choiceOpponent;
        do {
            choiceOpponent=ZKlavesnice.readInt("Na ktoreho hraca chces kartu " + choiceCard + " zahrat? (cislo 1 az " + opponents.size() + ") ");
        } while(choiceOpponent < 1 || choiceOpponent > players.size());
        Player opponent = opponents.get(choiceOpponent-1);
        return opponent;
    }

    public int chooseAction(){
        System.out.println();
        System.out.println("1 zahrat kartu");
        System.out.println("2 zahodit kartu");
        System.out.println("3 ukoncit tah");

        int choice;
        do {
            choice=ZKlavesnice.readInt("Vyber si svoj tah (cislo 1 az 3) ");
        } while(choice != 1 && choice != 2 && choice != 3);
        return choice;
    }

    public void discardCard(Class<?> t) {
        for(Card i : this.playerCards) {
            if(i.getClass().equals(t)) {
                this.playerCards.remove(i);
                break;
            }
        }
    }

}
