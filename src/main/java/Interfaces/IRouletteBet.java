package Interfaces;

import Games.RouletteCollection.RouletteSetUp;

public interface IRouletteBet {

    boolean isBetSuccessful(RouletteSetUp rouletteSetUp);
    int getBetAmount();


}
