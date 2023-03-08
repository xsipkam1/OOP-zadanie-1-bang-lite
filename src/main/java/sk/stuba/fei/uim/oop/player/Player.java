package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.*;

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
        return life;
    }
    public String getName(){
        return name;
    }
    public void decrementLife() {
        this.life--;
    }
    public void setCards(ArrayList<Card> cards){
        playerCards=cards;
    }

    public void addCard(Card card) {
        playerCards.add(card);
    }

    public boolean isActive() {
        return life > 0;
    }
    public ArrayList<Card> getPlayerCards(){
        return this.playerCards;
    }

    public boolean hasCard(Class<? extends Card> cardClass) {
        for (Card i : playerCards) {
            if (cardClass.isAssignableFrom(i.getClass())) {
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
