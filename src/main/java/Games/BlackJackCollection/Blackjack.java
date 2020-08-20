package Games.BlackJackCollection;

import DeckOfCardsClasses.Card;
import DeckOfCardsClasses.CardDeck;
import DeckOfCardsClasses.CardRank;
import Interfaces.IPlay;
import People.Dealer;
import People.Person;
import People.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Blackjack implements IPlay {

    private Dealer dealer;
    private ArrayList<Player> players;
    private int capacity;
    private HashMap<Player, Integer> betList;

    public Blackjack(Dealer dealer, int capacity) {
        this.dealer = dealer;
        this.capacity = capacity;
        this.players = new ArrayList<Player>();
        this.betList = new HashMap<Player, Integer>();
    }


    public Dealer getDealer() {
        return dealer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getCapacity() {
        return capacity;
    }

    public HashMap<Player, Integer> getBetList() {
        return betList;
    }

    public int getNumberOfPlayers() {
        return this.players.size();
    }


    public void addPlayer(Player player) {
        if (getNumberOfPlayers() < capacity) {
            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        if (players.contains(player)) {
            players.remove(player);
        }
    }

    public int getHandTotal(Person person) {
        int total = 0;
        for (Card card : person.getHand()) {
            total += card.getRankValue();
        }
        return total;
    }

    public boolean isBust(Person person) {
        if (getHandTotal(person) > 21) {
            return true;
        } else {
            return false;
        }
    }

    public void changeAceValue(Person person){
        for (Card card : person.getHand()) {
            if (card.getRank() == CardRank.ACE && isBust(person)) {
                card.setValue(1);
            }
        }
    }

    public void makeBets(Scanner scanner) {
        for (Player player: players) {
            int bet = player.makeBlackjackBet(scanner);
            betList.put(player, bet);
        }
    }

    public void initialDealerDeal(){
        dealer.dealCard(dealer);
        dealer.dealCard(dealer);
        Card card = dealer.getHand().get(0);
        System.out.printf("Dealer has %s of %s \n", card.getRank(), card.getSuit());
    }

    public void initialPlayerDeal(){
        for (Player player: players) {
            dealer.dealCard(player);
            dealer.dealCard(player);
            Card card = player.getHand().get(0);
            Card card2 = player.getHand().get(1);
            System.out.printf("Player %s has %s of %s and %s of %s \n", player.getName(), card.getRank(), card.getSuit(), card2.getRank(), card2.getSuit());
        }
    }

    public int getDealersScore() {
        while (getHandTotal(dealer) < 17) {
            Card card = dealer.dealCard(dealer);
            if (isBust(dealer)) {
                System.out.println("The dealer has gone bust.");
                return 0;
            }
            changeAceValue(dealer);
            System.out.printf( "The dealer got a %s of %s.\n", card.getRank(), card.getSuit());

        }
        System.out.printf( "The dealer's score is %d.", getHandTotal(dealer));

        return getHandTotal(dealer);
    }

    public int getPlayerScore(Scanner scanner, Player player) {
        System.out.printf("%s you have a current hand value of %d. Do you want to hit or stand?", player.getName(), getHandTotal(player));
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("stand")) {
            if (input.equalsIgnoreCase("hit")) {
                if (isBust(player)) {
                    return 0;
                }
                Card card = dealer.dealCard(player);
                changeAceValue(player);

                System.out.printf( "You got a %s of %s.\n Your current hand value is %d.\n Do you want to hit or stand?", card.getRank(), card.getSuit(),getHandTotal(player));
                input = scanner.nextLine();
            } else {
                System.out.println("Please enter hit or stand.");
                input = scanner.nextLine();
            }

        }
        return getHandTotal(player);
    }

    public void play(Scanner scanner) {
        System.out.println("Welcome to the BlackJack Game!");
        makeBets(scanner);
        dealer.getDeck().addDeck();
        dealer.getDeck().shuffle();
        initialPlayerDeal();
        initialDealerDeal();
        HashMap<Player, Integer> scores = new HashMap();
        for (Player player : players) {
            int playerScore = getPlayerScore(scanner, player);
            if (playerScore == 0) {
                int betAmount = betList.get(player);
                player.removeChips(betAmount);
                System.out.printf("Sorry %s, you have gone bust.\n You've lost %d chips and you now have a total of %d chips.\n", player.getName(), betAmount, player.getNumberOfChips());
            } else {
                scores.put(player, playerScore);
            }
        }
        if (scores.isEmpty()) {
            return;
        } else {
            int dealerScore = getDealersScore();
            for (Player player: scores.keySet()) {
                int betAmount = betList.get(player);
                if (scores.get(player) > dealerScore){
                    player.addChips(betAmount);
                    dealer.removeChips(betAmount);
                    System.out.printf("\nCongrats %s, you have won against the dealer!\n You've gain %d chips and you now have a total of %d chips.\n", player.getName(), betAmount, player.getNumberOfChips());
                } else if (scores.get(player) < dealerScore){
                player.removeChips(betAmount);
                dealer.addChips(betAmount);
                    System.out.printf("\nSorry %s, you have lost against the dealer.\n You've lost %d chips and you now have a total of %d chips.\n", player.getName(), betAmount, player.getNumberOfChips());
                }

                player.getHand().clear();
            }
            dealer.getDeck().clear();
            dealer.getHand().clear();
            this.players.clear();
            this.betList.clear();

        }
    }

    public int numOfPlayers() {
        return players.size();
    }
}



