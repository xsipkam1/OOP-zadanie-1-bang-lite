package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.*;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Player {
    private int life;
    private String name;
    private ArrayList<? extends Card> playerCards;

    public Player(String name) {
        this.life=4;
        this.name=name;
        this.playerCards=new ArrayList<>();
    }

    public int getLife() {
        return life;
    }
    public String getName(){
        return name;
    }
    public void decrementLife() {
        life--;
    }
    public void setCards(ArrayList<Card> cards){
        playerCards=cards;
    }
    public boolean isActive() {
        return life > 0;
    }
    public void printCards() {
        System.out.println("HRAC " + this.getName() + " MA TIETO KARTY: ");
        for(int i=0; i<playerCards.size(); i++){
            System.out.println((i+1) + " " + playerCards.get(i).getCardName());
        }
    }
    public boolean hasCard(Class<?> t){
        for(Card i : this.playerCards) {
            if(i.getClass().equals(t)) {
                return true;
            }
        }
        return false;
    }
}
