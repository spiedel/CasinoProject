package Casino;

import Interfaces.IPlay;
import People.Player;

import java.util.ArrayList;

public class Casino {

    private String name;
    private int capacity;
    private ArrayList<IPlay> games;
    private double amountOfMoney;
    private int numberOfChips;
    private ArrayList<Player> players;

    public Casino(String name, int capacity, double amountOfMoney) {
        this.name = name;
        this.capacity = capacity;
        this.games = new ArrayList<IPlay>();
        this.amountOfMoney = amountOfMoney;
        this.numberOfChips = 10000;
        this.players = new ArrayList<Player>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<IPlay> getGames() {
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
