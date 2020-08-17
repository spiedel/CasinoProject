package Games.BlackJackCollection;

import DeckOfCardsClasses.Card;
import DeckOfCardsClasses.CardDeck;
import Interfaces.IPlay;
import People.Dealer;
import People.Person;
import People.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Blackjack implements IPlay {

    private CardDeck cardDeck;
    private Dealer dealer;
    private ArrayList<Player> players;
    private int capacity;
    private HashMap<Player, Integer> betList;

    public Blackjack(CardDeck cardDeck, Dealer dealer, int capacity) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.capacity = capacity;
        this.players = new ArrayList<Player>();
        this.betList = new HashMap<Player, Integer>();
    }

    public CardDeck getCardDeck() {
        return cardDeck;
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

    public boolean isPlayerBust(Person person) {
        if (getHandTotal(person) > 21) {
            return true;
        } else {
            return false;
        }
    }

    public int getDealersScore() {
        while (getHandTotal(dealer) < 17) {
            dealer.dealCard(dealer);
        }
        return getHandTotal(dealer);
    }

    public int getPlayerScore(Scanner scanner, Player player) {
        System.out.println("Do you want to hit or stand?");
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("stand")) {
            if (input.equalsIgnoreCase("hit")) {
                dealer.dealCard(player);
                if (isPlayerBust(player)) {
                    return 0;
                }
            } else {
                System.out.println("Please enter hit or stand.");
                input = scanner.nextLine();
            }

        }
        return getHandTotal(player);
    }

    public void play(Scanner scanner) {
        HashMap<Player, Integer> scores = new HashMap();
        for (Player player : players) {
            int playerScore = getPlayerScore(scanner, player);
            if (playerScore == 0) {
                player.removeChips(10);
            } else {
                scores.put(player, playerScore);
            }
        }
        if (scores.isEmpty()) {
            return;
        } else {
            int dealerScore = getDealersScore();
            for (Player player: scores.keySet()) {
                if (scores.get(player) > dealerScore){
                    player.addChips(10);
                    dealer.removeChips(10);
                } else if (scores.get(player) < dealerScore){
                player.removeChips(10);
                dealer.addChips(10);
            }
        }
    }
}
}



