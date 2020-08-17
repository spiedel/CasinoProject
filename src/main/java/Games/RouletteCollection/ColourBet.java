package Games.RouletteCollection;

public class ColourBet implements IRouletteBet {

    private String colourBetOn;
    private int amountBet;


    //Player uses constructor to make bet.
    public ColourBet(String colourBetOn, int amountBet){
        this.amountBet = amountBet;
        this.colourBetOn = colourBetOn;

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
}
