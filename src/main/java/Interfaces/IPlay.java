package Interfaces;

import People.Player;

import java.util.Scanner;

public interface IPlay {

    void addPlayer(Player player);
    void removePlayer(Player player);
    void play(Scanner scanner);
    int numOfPlayers();
}
