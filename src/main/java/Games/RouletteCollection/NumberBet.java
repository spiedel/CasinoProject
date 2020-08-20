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
        int betAmount = scanner.nextInt();
        if (player.hasEnoughChipsToBet(betAmount)){
            this.amountBet = betAmount;
            System.out.println("What number do you want to bet on?");
            int number = scanner.nextInt();
            this.numberBetOn = number;
        } else {
            System.out.println("Sorry you don't have enough chips to bet with. Please try again.");
            makeBet(scanner, player);
        }
    }
}
