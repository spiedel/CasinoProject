package Games;

import Interfaces.IPlay;
import People.Dealer;
import People.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Roulette implements IPlay {

    private Dealer dealer;
    private ArrayList<Player> players;
    private int capacity;
    private Random random;
    private HashMap<Player, HashMap<Integer, Integer>> bets;

    public Roulette(Dealer dealer) {
        this.dealer = dealer;
        this.players = new ArrayList<Player>();
        this.capacity = 7;
        random = new Random(3);
        bets = new HashMap<Player, HashMap<Integer, Integer>>();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNumberOfPlayers() {
        return this.players.size();
    }

    public HashMap<Player, HashMap<Integer, Integer>> getBets() {
        return bets;
    }

    public void addPlayer(Player player) {
        if (getNumberOfPlayers() < capacity ) {
            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        if ( players.contains(player) ) {
            players.remove(player);
        }
    }

    public int spin() {
        return random.nextInt(37);
    }

    public void bet(int numberOfChips, ArrayList<Integer> betNumbers, Player player) {
        HashMap<Integer, Integer> playerBets = new HashMap<Integer, Integer>();
        for (int betNumber : betNumbers ) {
            player.removeChips(numberOfChips);
            playerBets.put(betNumber, numberOfChips);
        }
        bets.put(player, playerBets);
    }

    public void play() {
        int result = spin();
        for ( Player player : bets.keySet() ) {
            HashMap<Integer, Integer> playerBets = bets.get(player);

            for (Integer betNumber : playerBets.keySet()) {
                //for (IBetRoulette bet : playerBets.get(player))
                if ( result == betNumber) {
                    //if ( bet.isBetSuccessful(result) )
                    player.addChips(playerBets.get(betNumber) * 2);
                    //player.addChips(bet.getBetReturn);
                    System.out.println("You won on number " + betNumber );

                } else {
                    System.out.println("You lost on number " + betNumber );
                    dealer.addChips(playerBets.get(betNumber));
                    //dealer.addChips(bet.getAmountBet)
                }
            }
        }
        bets.clear();
    }
}
