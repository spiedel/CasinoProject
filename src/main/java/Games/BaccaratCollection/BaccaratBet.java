package Games.BaccaratCollection;

import Interfaces.IBet;
import People.Player;

public class BaccaratBet implements IBet {

    Player player;
    int betAmount;
    BaccaratOutcome baccaratOutcome;

    public BaccaratBet(Player player, int betAmount, BaccaratOutcome baccaratOutcome) {
        this.player = player;
        this.betAmount = betAmount;
        this.baccaratOutcome = baccaratOutcome;
    }

    public Player getPlayer() {
        return player;
    }

    public int getReturn() {
        switch (baccaratOutcome) {
            case PLAYER:
                return betAmount;
            case TIE:
                return betAmount * 9;
            case DEALER:
                return (int) Math.round(betAmount*.9);
            default:
                return 0;
        }

    }

    public int getBetAmount() {
        return betAmount;
    }

    public BaccaratOutcome getBaccaratOutcome() {
        return baccaratOutcome;
    }



}
