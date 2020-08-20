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
import java.util.concurrent.TimeUnit;

import static Runner.Runner.pause;

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

    public void play(Scanner scanner) {
        System.out.println("Welcome to the Roulette Game!");
        for (Player player : players) {
            System.out.println(player.getName() + " Please make a bet.");
            IRouletteBet bet = player.makeRouletteBet(scanner);
            player.addRouletteBet(bet);
        }

        System.out.println("Spinning...");
        pause(1);
        RouletteSetUp rouletteValue = spin();
        System.out.println("The winning number is " + rouletteValue.getValue() +  " and its colour is " + rouletteValue.getColour());


        for (Player player: players) {
            pause(1);
            ArrayList<IRouletteBet> bets = player.betList();
            for (IRouletteBet bet:bets) {
                if(bet.isBetSuccessful(rouletteValue)){
                    System.out.println(player.getName() + " Your bet was successful!" + " You have won " + bet.getReturn() + " chips");
                    player.addChips(bet.getReturn());
                    dealer.removeChips(bet.getReturn());
                } else {
                    player.removeChips(bet.getBetAmount());
                    dealer.removeChips(bet.getBetAmount());
                    System.out.println("Sorry " + player.getName() + " your bet was not successful." + " You have lost " + bet.getBetAmount() + " chips");
                }
                System.out.println(player.getName() + " you now have " + player.getNumberOfChips() + " chips");

            }

            player.removeRouletteBet();
        }

        this.players.clear();
    }


    public ArrayList<RouletteSetUp> getStartPoint() {
        return rouletteList;
    }

    public int numOfPlayers() {
        return players.size();
    }

}
