package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

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
    public ArrayList<? extends Card> getPlayerCards(){
        return this.playerCards;
    }

    public boolean hasCard(Class<?> t) {
        for(Card i : this.playerCards) {
            if(i.getClass().equals(t)) {
                return true;
            }
        }
        return false;
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
