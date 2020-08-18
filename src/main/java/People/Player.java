package People;

import Games.BaccaratCollection.Baccarat;
import Games.BaccaratCollection.BaccaratBet;
import Games.BaccaratCollection.BaccaratOutcome;
import Games.BlackJackCollection.Blackjack;
import Games.Roulette;
//import Games.RouletteCollection.ColourBet;
//import Games.RouletteCollection.IRouletteBet;
import Interfaces.IPlay;

import Interfaces.IRouletteBet;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Player extends Person{

    private double moneyInWallet;
    private ArrayList<IRouletteBet> playerBets;
    private Roulette roulette;
    private Blackjack blackjack;
    private Baccarat baccarat;
    private Dealer dealer;


    public Player(String name, int age, double startingMoney) {
        super(name, age);
        this.moneyInWallet = startingMoney;
        this.playerBets = new ArrayList<IRouletteBet>();
        this.dealer = new Dealer ("John", 40);
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


    public void chooseGame(Scanner scanner, HashMap<Integer, IPlay> gameList){
        int gameNum = scanner.nextInt();
        IPlay game = gameList.get(gameNum);
        game.addPlayer(this);
    }

    public boolean hasEnoughChipsToBet(int betAmount) {


        if (betAmount <= numberOfChips) {
            return true;
        }
        else {
            return false;
        }

    }

    public BaccaratBet makeBaccaratBet(Scanner scanner) {
        String input = scanner.nextLine();
        BaccaratOutcome betType;
        while(true) {
            if(input.equalsIgnoreCase("win")) {
                betType = BaccaratOutcome.WIN;
                break;
            } else if(input.equalsIgnoreCase("loss")){
                betType = BaccaratOutcome.LOSS;
                break;
            } else if(input.equalsIgnoreCase("draw")){
                betType = BaccaratOutcome.DRAW;
                break;
            } else {
                System.out.println("Please make a bet");
                input = scanner.nextLine();
            }
        }
        System.out.println("How much do you want to bet?");
        int betAmount = scanner.nextInt();
        return new BaccaratBet(this, betAmount, betType);
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
