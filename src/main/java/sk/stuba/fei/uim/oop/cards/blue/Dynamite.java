package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Dynamite extends BlueCard {
    private static final String CARD_NAME = "DYNAMIT";

    public Dynamite() {
        super(CARD_NAME);
    }

    @Override
    public boolean action(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        player.throwCard(player.hasCard(Dynamite.class, player.getPlayerCards()), player.getBlueCards(), player.getPlayerCards());
        System.out.println("VYLOZIL SI PRED SEBA DYNAMIT!");
        return false;
    }

    @Override
    public boolean checkEffect(Player player, ArrayList<Card> playingCards, ArrayList<Player> players) {
        if(blueCardProbability.nextInt(8) == 0) {
            System.out.println("HRACOVI " + player.getName() + " VYBUCHOL DYNAMIT!");
            for(int i=0; i<3; i++){
                player.decrementLife();
            }
            if(player.getLife()>0) {
                player.throwCard(player.hasCard(Dynamite.class, player.getBlueCards()), playingCards, player.getBlueCards());
            }
            player.checkLife(players, playingCards);
            return true;
        }
        int dynamiteIndex = players.indexOf(player);
        Player previousPlayer = players.get((dynamiteIndex - 1 + players.size()) % players.size());
        player.throwCard(player.hasCard(Dynamite.class, player.getBlueCards()), previousPlayer.getBlueCards(), player.getBlueCards());
        System.out.println("HRACOVI " + player.getName() + " DYNAMIT NEVYBUCHOL A POSIELA HO HRACOVI " + previousPlayer.getName());
        return false;
    }
}
