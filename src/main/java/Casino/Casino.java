package Casino;

import Games.BaccaratCollection.Baccarat;
import Games.BlackJackCollection.Blackjack;
import Games.Roulette;
import Interfaces.IPlay;
import People.Dealer;
import People.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Casino {

    private String name;
    private int capacity;
    private HashMap<Integer,IPlay> games;
    private double amountOfMoney;
    private int numberOfChips;
    private ArrayList<Player> players;


    public Casino() {
        this.name = "CodeClan Casino";
        this.capacity = 1000;
        //this.games = new ArrayList<IPlay>();
        this.games= new HashMap<Integer, IPlay>();
        this.amountOfMoney = 1000000;
        this.numberOfChips = 10000;
        this.players = new ArrayList<Player>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public HashMap<Integer, IPlay> getGames() {
        return games;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public int getNumberOfChips() {
        return numberOfChips;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public void addPlayerToGame(Player player, IPlay game){
        game.addPlayer(player);
    }
    public void removePlayerFromGame(Player player, IPlay game){
        game.removePlayer(player);
    }

    public void addGameToCasino(int key, IPlay game){
        this.games.put(key,game);
    }




    public void addPlayerToCasino(Player player) {
        if (player.isOldEnoughToEnter()) {
            players.add(player);
        }
    }
    public void removePlayerFromCasino(Player player) {
        if (players.contains(player)) {
            players.remove(player);
        }
    }
}
