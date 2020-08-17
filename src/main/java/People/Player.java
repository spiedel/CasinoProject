package People;

import Games.RouletteCollection.ColourBet;
import Games.RouletteCollection.IRouletteBet;

import java.util.ArrayList;

public class Player extends Person{

    private double moneyInWallet;
    private ArrayList<IRouletteBet> playerBets;


    public Player(String name, int age, double startingMoney) {
        super(name, age);
        this.moneyInWallet = startingMoney;
        this.playerBets = new ArrayList<IRouletteBet>();
    }

    public double getMoneyInWallet() {
        return moneyInWallet;
    }

    public void buyChips(){
        this.addChips((int) (moneyInWallet / 5));
        this.moneyInWallet = 0;
    }

    public void cashInChips(){
        moneyInWallet += getNumberOfChips() * 5;
        this.removeChips(getNumberOfChips());
    }

    //Add a bet to list

    public void addRouletteBet(IRouletteBet bet){
        playerBets.add(bet);
    }

    //Remove bet from list
    public void removeRouletteBet (){
        playerBets.remove(0);
    }


    //Want to return the list
    public ArrayList<IRouletteBet> betList(){
        return playerBets;
    }


}
