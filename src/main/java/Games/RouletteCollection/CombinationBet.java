package Games.RouletteCollection;

import Interfaces.IRouletteBet;
import People.Player;

import java.util.Scanner;

public class CombinationBet implements IRouletteBet {
    //Allows player to make a combination of two bets (later allocate higher return if successful).
    //eg: A player can bet the number will be 17 and black simultaneously.
    //Allows any combination of the roulette bets.

    private IRouletteBet bet;
    private IRouletteBet bet2;
    private int amountBet;

    public CombinationBet(){
    }

    public boolean isBetSuccessful(RouletteSetUp rouletteSetUp) {
        if(bet.isBetSuccessful(rouletteSetUp) && bet2.isBetSuccessful(rouletteSetUp)){
            return true;
        }
        return false;
    }

    public int getBetAmount() {
        return this.amountBet;
    }

    public int getReturn() {
        return 0;
    }

    public void makeBet(Scanner scanner, Player player){
        System.out.println("Make first bet");
        this.bet = player.makeRouletteBet(scanner);
        System.out.println("Make second bet");
        this.bet2 = player.makeRouletteBet(scanner);
        this.amountBet = bet.getBetAmount() + bet2.getBetAmount();
    }

}
