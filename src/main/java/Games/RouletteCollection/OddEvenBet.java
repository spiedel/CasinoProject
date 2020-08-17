package Games.RouletteCollection;

public class OddEvenBet implements IRouletteBet {
    private int amountBet;
    private String oddOREven;


    public OddEvenBet(int amountBet, String oddOREven){
        this.amountBet = amountBet;
        this.oddOREven = oddOREven;
    }


    //Is sucessful is implemented via interface
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
}

