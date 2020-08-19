package Games.RouletteCollection;

import Interfaces.IRouletteBet;
import Games.RouletteCollection.RouletteSetUp;
import Interfaces.IPlay;
import People.Dealer;
import People.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Roulette implements IPlay {

    private Dealer dealer;
    private ArrayList<Player> players;
    private int capacity;
    private Random random;
    private ArrayList<RouletteSetUp> rouletteList;


    public Roulette(Dealer dealer) {
        this.dealer = dealer;
        this.players = new ArrayList<Player>();
        this.capacity = 7;
        random = new Random(3);
        rouletteList = new ArrayList<RouletteSetUp>();

        for (RouletteSetUp rouletteSetUp:RouletteSetUp.values()) {
            rouletteList.add(rouletteSetUp);
        }

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

    /*public HashMap<Player, HashMap<Integer, Integer>> getBets() {
        return bets;
    }*/

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

    public RouletteSetUp spin() {
        int index = random.nextInt(37);
        return rouletteList.get(index);
    }

    /*public void bet(int numberOfChips, ArrayList<Integer> betNumbers, Player player) {
        HashMap<Integer, Integer> playerBets = new HashMap<Integer, Integer>();
        for (int betNumber : betNumbers ) {
            player.removeChips(numberOfChips);
            playerBets.put(betNumber, numberOfChips);
        }
        bets.put(player, playerBets);
    } */

    public void play(Scanner scanner) {
        RouletteSetUp rouletteValue = spin();

        for (Player player: players) {
            player.makeRouletteBet(scanner);
            ArrayList<IRouletteBet> bets = player.betList();
            for (IRouletteBet bet:bets) {
                if(bet.isBetSuccessful(rouletteValue)){
                    player.addChips(bet.getBetAmount());
                } else {
                    player.removeChips(bet.getBetAmount());
                }
            }
        }
        //return bet.isBetSuccessful(rouletteValue);
    }


    public ArrayList<RouletteSetUp> getStartPoint() {
        return rouletteList;
    }

}
