package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class Dynamite extends Card {
    private static final String CARD_NAME = "DYNAMIT";
    private final Random dynamiteProbability;

    public Dynamite() {
        dynamiteProbability = new Random();
    }

    @Override
    public String getCardName() {
        return CARD_NAME;
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        player.throwCard(player.hasCard(Dynamite.class, player.getPlayerCards()), player.getBlueCards(), player.getPlayerCards());
        System.out.println("VYLOZIL SI PRED SEBA DYNAMIT!");
        return false;
    }

    public void checkDynamite(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        if(dynamiteProbability.nextInt(8) == 0) {
            System.out.println("HRACOVI " + player.getName() + " VYBUCHOL DYNAMIT!");
            for(int i=0; i<3; i++){
                player.decrementLife();
            }
            if(player.getLife()>0) {
                player.throwCard(player.hasCard(Dynamite.class, player.getBlueCards()), playingCards, player.getBlueCards());
            }
            player.checkLife(players, playingCards);
        } else {
            int dynamiteIndex = players.indexOf(player);
            Player previousPlayer = players.get((dynamiteIndex - 1 + players.size()) % players.size());
            player.throwCard(player.hasCard(Dynamite.class, player.getBlueCards()), previousPlayer.getBlueCards(), player.getBlueCards());
            System.out.println("HRACOVI " + player.getName() + " DYNAMIT NEVYBUCHOL A POSIELA HO HRACOVI " + previousPlayer.getName());
        }
    }
}
