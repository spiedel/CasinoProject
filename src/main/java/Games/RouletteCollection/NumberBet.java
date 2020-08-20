package Games.RouletteCollection;

import Interfaces.IRouletteBet;
import People.Player;

import java.util.Scanner;

public class NumberBet implements IRouletteBet {

    private int numberBetOn;
    private int amountBet;


    //Player uses constructor to make bet.
    public NumberBet(){

    }

    public boolean isBetSuccessful(RouletteSetUp rouletteValue) {
        if (rouletteValue.getValue() == numberBetOn){
            return true;
        } else {
            return false;
        }
    }

    public int getNumberBetOn() {
        return numberBetOn;
    }

    public int getReturn() {
        return getBetAmount() * 35;
    }

    public int getBetAmount() {
        return amountBet;
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
            System.out.println("What number do you want to bet on? Please select a number between 1 and 36.");
            int number = scanner.nextInt();
            if (number >= 1 && number <= 36){
                this.numberBetOn = number;
            } else {
                System.out.println("You must select a number between 1 and 36. Please try again.");
                makeBet(scanner, player);
            }
        } else {
            System.out.println("Sorry you don't have enough chips to bet with. Please try again.");
            makeBet(scanner, player);
        }
    }
}
