package Runner;

import Casino.Casino;
import Interfaces.IPlay;
import People.Player;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Runner {


    public static void main(String[] args) {
        Casino casino = new Casino();
        Scanner input = new Scanner(System.in);
        System.out.println("Hello, Welcome to the Casino!");
        addAllPlayersToCasino(input, casino);

        //buy chips
        for (Player player: casino.getPlayers()) {
            player.buyChips(input);
        }

        //play first games
        chooseAndPlayGames(input, casino);

        //play other games
        System.out.println("Do you want to play again?");
        String lineInput = input.nextLine();
        while (!lineInput.equalsIgnoreCase("no")) {
            if (lineInput.equalsIgnoreCase("yes")) {
                chooseAndPlayGames(input, casino);
                System.out.println("Do you want to play again?");
            } else {
                System.out.println("Please enter yes or no.");
            }
            lineInput = input.nextLine();
        }
        casino.closeCasino();

    }

    public static void addPlayerToGame(Scanner input, Casino casino) {
        System.out.println("Please enter your name: ");
        String name = input.nextLine();
        System.out.println("Please enter your age: ");
        while(!input.hasNextInt()){
            System.out.println("Please enter a number.");
            input.nextLine();
        }
        int age = Integer.parseInt(input.nextLine());
        System.out.println("Please enter the amount of money you are betting with: ");
        while(!input.hasNextInt()){
            System.out.println("Please enter a number.");
            input.nextLine();
        }
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

    public static void chooseAndPlayGames(Scanner input, Casino casino) {
        for (Player player1 : casino.getPlayers()) {
            System.out.printf("%s, please select a game to play: \n", player1.getName());
            System.out.println("1: Roulette \n2: BlackJack \n3: Baccarat\n4: No game");
            while(!input.hasNextInt()){
                System.out.println("Please enter a number.");
                input.nextLine();
            }
            player1.chooseGame(input, casino.getGames());
        }
        for (int gameNum : casino.getGames().keySet()) {
            IPlay game = casino.getGames().get(gameNum);
            if (game.numOfPlayers() >= 1) {
                game.play(input);
            }
        }
    }

    public static void pause(int timeInSeconds) {
        try {
            TimeUnit.SECONDS.sleep(timeInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
