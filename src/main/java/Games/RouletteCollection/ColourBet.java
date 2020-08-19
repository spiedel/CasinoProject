package Games.RouletteCollection;

import Interfaces.IRouletteBet;
import People.Player;

import java.util.Scanner;

public class ColourBet implements IRouletteBet {

    private String colourBetOn;
    private int amountBet;


    //Player uses constructor to make bet.
    public ColourBet(){

    }


    public boolean isBetSuccessful(RouletteSetUp rouletteValue) {
        if (rouletteValue.getColour().equals(colourBetOn)){
            return true;
        } else
            {
            return false;
        }
        //is colour from game the same as the colour the person bet on.

    }


    //Getters
    public int getBetAmount() {
        return amountBet;
    }

    public String getColourBetOn() {
        return colourBetOn;
    }

    public int getReturn() {
        return getBetAmount();
    }

    public void makeBet(Scanner scanner, Player player){
        System.out.println("How many chips do you want to bet?");
        int betAmount = scanner.nextInt();
        if (player.hasEnoughChipsToBet(betAmount)){
            this.amountBet = betAmount;
            System.out.println("What colour do you want to bet on?");
            String colour = scanner.next();
            this.colourBetOn = colour;
        }

    }
}
