package Games.RouletteCollection;

import Interfaces.IRouletteBet;
import People.Player;

import java.util.Scanner;

public class OddEvenBet implements IRouletteBet {
    private int amountBet;
    private String oddOREven;


    public OddEvenBet(){
    }


    //Is successful is implemented via interface
    //Here: Because two different bets possible: checks which bet is being made
    //Then:: calls a method to check whether the bet is true or false based on whther the peron is predicting an even or odd number.
    public boolean isBetSuccessful(RouletteSetUp rouletteValue) {
        if(this.oddOREven.equalsIgnoreCase("odd")){
            return testOdd( rouletteValue);
        } else{
            return testEven(rouletteValue);
        }
    }

    private boolean testEven(RouletteSetUp rouletteValue) {
        return rouletteValue.getValue()%2 ==0;
    }

    private boolean testOdd(RouletteSetUp rouletteValue) {
        return rouletteValue.getValue()%2 ==1;
    }


    //Getters
    public int getBetAmount() {
        return this.amountBet;
    }

    public String getOddOREven(){
        return this.oddOREven;
    }

    public int getReturn() {
        return getBetAmount();
    }

    public void makeBet(Scanner scanner, Player player){
        System.out.println("How many chips do you want to bet?");
        while(!scanner.hasNextInt()){
            System.out.println("Please enter a number.");
            scanner.nextLine();
        }
        int betAmount = scanner.nextInt();
        if (player.hasEnoughChipsToBet(betAmount)){
            this.amountBet = betAmount;
            System.out.println("Do you want to bet odd or even?");
            String oddOrEven = scanner.next();
            if (oddOrEven.equalsIgnoreCase("odd") || oddOrEven.equalsIgnoreCase("even")){
                this.oddOREven = oddOrEven;
            } else {
                System.out.println("You must select either odd or even. Please try again.");
                makeBet(scanner, player);
            }
        } else {
            System.out.println("Sorry you don't have enough chips to bet with. Please try again.");
            makeBet(scanner, player);
        }

    }
}

