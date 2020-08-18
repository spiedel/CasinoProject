package Interfaces;

import Games.RouletteCollection.RouletteSetUp;
import People.Player;

import java.util.Scanner;

public interface IRouletteBet extends IBet {

    boolean isBetSuccessful(RouletteSetUp rouletteSetUp);
    void makeBet(Scanner scanner, Player player);


}
