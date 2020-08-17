package Games.BaccaratCollection;

import People.Player;

public class BaccaratBet {

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

    public int getBetAmount() {
        return betAmount;
    }

    public BaccaratOutcome getBaccaratOutcome() {
        return baccaratOutcome;
    }



}
