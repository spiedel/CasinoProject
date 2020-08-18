import Casino.Casino;
import People.Player;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Hello, Welcome to the Casino!");

        System.out.println("Please enter your name: ");
        String name = input.nextLine();

        System.out.println("Please enter your age: ");
        int age = input.nextInt();

        System.out.println("Please enter the amount of money you are betting with: ");
        double moneyInWallet = input.nextInt();

        Player player = new Player(name, age, moneyInWallet);
        Casino casino = new Casino();


        if (player.isOldEnoughToEnter()) {
            System.out.println("You are old enough to enter the Casino, welcome!");
            casino.addPlayerToCasino(player);}
        else{
                System.out.println("Sorry you are not old enough to enter the Casino.");
            }

        for (Player player1 : casino.getPlayers()
             ) {
            System.out.printf("%s, please select a game to play: ", player1.getName());
            player.chooseGame(input, casino.getGames());
        }
        }
    }
