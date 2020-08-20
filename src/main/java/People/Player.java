package People;

import Games.BaccaratCollection.Baccarat;
import Games.BaccaratCollection.BaccaratBet;
import Games.BaccaratCollection.BaccaratOutcome;
import Games.BlackJackCollection.Blackjack;
import Games.RouletteCollection.*;
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


    public Player(String name, int age, double startingMoney) {
        super(name, age);
        this.moneyInWallet = startingMoney;
        this.playerBets = new ArrayList<IRouletteBet>();
    }

    public double getMoneyInWallet() {
        return moneyInWallet;
    }

    public void buyChips(Scanner scanner) {
        System.out.printf("5 chips costs 1 pound. How much do you want to spend on chips %s?", this.getName());
        while(!scanner.hasNextInt()){
            System.out.println("Please enter a number.");
            scanner.nextLine();
        }
        int spendingMoney = scanner.nextInt();
        scanner.nextLine();
        if (moneyInWallet >= spendingMoney) {
            this.addChips((int) (spendingMoney * 5));
            moneyInWallet -= spendingMoney;
            System.out.printf("You now have %s chips.\n", this.getNumberOfChips());
        } else {
            System.out.println("Sorry you do not have enough money to buy that many chips. Please try again.");
            buyChips(scanner);
        }
    }

    public void cashInChips () {
        moneyInWallet += getNumberOfChips() / 5;
        this.removeChips(getNumberOfChips());
    }

    public void chooseGame(Scanner scanner, HashMap<Integer, IPlay> gameList){
        int gameNum = scanner.nextInt();
        if (gameNum >3) {
            System.out.printf("Sorry you don't want to play a game %s, please enjoy the bar!\n", getName());
            return;
        }
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
        System.out.printf("%s, Do you want to bet on player, dealer or tie? \n", this.getName());
        String input = scanner.nextLine();
        BaccaratOutcome betType;
        while(true) {
            if(input.equalsIgnoreCase("player")) {
                betType = BaccaratOutcome.PLAYER;
                break;
            } else if(input.equalsIgnoreCase("dealer")){
                betType = BaccaratOutcome.DEALER;
                break;
            } else if(input.equalsIgnoreCase("tie")){
                betType = BaccaratOutcome.TIE;
                break;
            } else {
                System.out.println("Please select a bet type.");
                input = scanner.nextLine();
            }
        }
        System.out.println("How many chips do you want to bet?");
        while(!scanner.hasNextInt()){
            System.out.println("Please enter a number.");
            scanner.nextLine();
        }
        int betAmount = scanner.nextInt();
        if (this.hasEnoughChipsToBet(betAmount)) {
            return new BaccaratBet(this, betAmount, betType);
        } else {
            System.out.println("Sorry you don't have enough chips to bet with. Please try again.");
            return makeBaccaratBet(scanner);
        }
    }

    public int makeBlackjackBet(Scanner scanner) {
        System.out.println(this.getName() + " how many chips do you want to bet?");
        while(!scanner.hasNextInt()){
            System.out.println("Please enter a number.");
            scanner.nextLine();
        }
        int betAmount = scanner.nextInt();
        scanner.nextLine();
        if (this.hasEnoughChipsToBet(betAmount)) {
            return betAmount;
        } else {
            System.out.println("Sorry you don't have enough chips to bet with. Please try again.");
            return makeBlackjackBet(scanner);
        }
    }

    public IRouletteBet makeRouletteBet(Scanner scanner){
        System.out.println("Please select bet type.\n 1: Colour Bet \n 2: Odd or Even Bet \n 3: Combination Bet \n 4: Number Bet");
        while(!scanner.hasNextInt()){
            System.out.println("Please enter a number.");
            scanner.nextLine();
        }
        int input = scanner.nextInt();
        IRouletteBet bet;
        if (input == 1) {
            bet = new ColourBet();
            bet.makeBet(scanner, this);
            return bet;
        } else if (input == 2){
            bet = new OddEvenBet();
            bet.makeBet(scanner, this);
            return bet;
        } else if (input == 3){
            bet = new CombinationBet();
            bet.makeBet(scanner, this);
            return bet;
        } else if (input == 4){
            bet = new NumberBet();
            bet.makeBet(scanner, this);
            return bet;
        }

        return null;
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
