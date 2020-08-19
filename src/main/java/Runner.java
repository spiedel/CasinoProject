import Casino.Casino;
import Interfaces.IPlay;
import People.Player;

import java.util.Scanner;

public class Runner {


    public static void main(String[] args) {
        Casino casino = new Casino();
        Scanner input = new Scanner(System.in);
        System.out.println("Hello, Welcome to the Casino!");
        addAllPlayersToCasino(input, casino);
        for (Player player1 : casino.getPlayers()) {
            player1.buyChips(input);
            System.out.printf("%s, please select a game to play: \n", player1.getName());
            System.out.println("1: Roulette \n2: BlackJack \n3: Baccarat\n4: No game");
            player1.chooseGame(input, casino.getGames());
        }
        for (int gameNum : casino.getGames().keySet()) {
            IPlay game = casino.getGames().get(gameNum);
            if (game.numOfPlayers() >= 1) {
                game.play(input);
            }
        }
    }

    public static void addPlayerToGame(Scanner input, Casino casino) {
        System.out.println("Please enter your name: ");
        String name = input.nextLine();
        System.out.println("Please enter your age: ");
        int age = Integer.parseInt(input.nextLine());
        System.out.println("Please enter the amount of money you are betting with: ");
        double moneyInWallet = Integer.parseInt(input.nextLine());
        Player player = new Player(name, age, moneyInWallet);

        if (player.isOldEnoughToEnter()) {
            System.out.println("You are old enough to enter the Casino, welcome!");
            casino.addPlayerToCasino(player);
        } else {
            System.out.println("Sorry you are not old enough to enter the Casino.");
        }
    }



    public static void addAllPlayersToCasino(Scanner input, Casino casino) {
        System.out.println("Do you want to be added to the Casino?");
        String lineInput = input.nextLine();
        while (!lineInput.equalsIgnoreCase("no")) {
            if (lineInput.equalsIgnoreCase("yes")) {
                addPlayerToGame(input, casino);
                System.out.println("Does another player want to be added to the Casino?");
            } else {
                System.out.println("Please enter yes or no.");
            }
             lineInput = input.nextLine();
        }
    }
}
