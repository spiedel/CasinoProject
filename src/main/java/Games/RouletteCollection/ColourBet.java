package Games.RouletteCollection;

public class ColourBet implements IRouletteBet {

    private String colourBetOn;
    private int amountBet;


    //Player uses constructor to make bet.
    public ColourBet(String colourBetOn, int amountBet){
        this.amountBet = amountBet;
        this.colourBetOn = colourBetOn;

    }


    public boolean isBetSuccessful(/*Colour from game*/) {

        //is colour from game the same as the colour the person bet on.
        return false;
    }

    public int getBetAmount() {
        return amountBet;
    }


    //Getters
    public String getColourBetOn() {
        return colourBetOn;
    }
}
