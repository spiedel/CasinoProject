package Games.RouletteCollection;

public class CombinationBet implements IRouletteBet {
    //Allows player to make a combination of two bets (later allocate higher return if sucessful).
    //eg: A player can bet the number will be 17 and black simultaneously.
    //Allows any combination of the roulette bets.

    private IRouletteBet bet;
    private IRouletteBet bet2;
    private int amountBet;

    public CombinationBet(IRouletteBet bet, IRouletteBet bet2){
        this.bet = bet;
        this.bet2 = bet2;
        this.amountBet = bet.getBetAmount() + bet2.getBetAmount();
    }


    public boolean isBetSuccessful(RouletteSetUp rouletteSetUp) {

        if(bet.isBetSuccessful(rouletteSetUp) && bet2.isBetSuccessful(rouletteSetUp)){
            return true;
        }return false;
    }

    public int getBetAmount() {
        return this.amountBet;
    }

}
