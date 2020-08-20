package Games.BaccaratCollection;

import DeckOfCardsClasses.Card;
import Interfaces.IPlay;
import People.Dealer;
import People.Person;
import People.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Baccarat implements IPlay {

    private Dealer dealer;
    private ArrayList<Player> players;
    private int capacity;
    private ArrayList<BaccaratBet> betList;

    public Baccarat(Dealer dealer, int capacity) {
        this.dealer = dealer;
        this.players = new ArrayList<Player>();
        this.capacity = capacity;
        this.betList = new ArrayList<BaccaratBet>();
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

    public ArrayList<BaccaratBet> getBetList() {
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

    public void initialDeal(Player player, Dealer dealer){
            dealer.dealCard(player);
            dealer.dealCard(player);
            dealer.dealCard(dealer);
            dealer.dealCard(dealer);
            Card playerCard1 = player.getHand().get(0);
            Card playerCard2 = player.getHand().get(1);
            Card dealerCard1 = dealer.getHand().get(0);
            Card dealerCard2 = dealer.getHand().get(1);
            System.out.printf("Player has %s of %s and %s of %s \n", playerCard1.getRank(), playerCard1.getSuit(), playerCard2.getRank(), playerCard2.getSuit());
            System.out.printf("Dealer has %s of %s and %s of %s", dealerCard1.getRank(), dealerCard1.getSuit(), dealerCard2.getRank(), dealerCard2.getSuit());
    }

    public int getHandTotal(Person person) {
        int total = 0;
        for (Card card : person.getHand()) {
            total += card.getRankValue();
        }
        return total % 10;
    }

    public boolean isThirdCardDrawnToPlayer(Player player){
        if (getHandTotal(player) < 5){
            return true;
        } else {
            return false;
        }
    }

    public boolean isThirdCardDrawnToDealer(Dealer dealer, Player player) {

        int dealerCardTotal = getHandTotal(dealer);

        if (player.getNumberOfCards() == 3) {
            int valueOfPlayersThirdCard = player.getHand().get(2).getRankValue();

            if (dealerCardTotal <= 2) {
                return true;
            } else if (dealerCardTotal == 3) {
                if (valueOfPlayersThirdCard != 8) {
                    return true;
                } else {
                    return false;
                }
            } else if (dealerCardTotal == 4) {
                if (valueOfPlayersThirdCard > 1 && valueOfPlayersThirdCard < 8) {
                    return true;
                } else {
                    return false;
                }
            } else if (dealerCardTotal == 5) {
                if (valueOfPlayersThirdCard > 3 && valueOfPlayersThirdCard < 8) {
                    return true;
                } else {
                    return false;
                }
            } else if (dealerCardTotal == 6) {
                if (valueOfPlayersThirdCard > 5 && valueOfPlayersThirdCard < 8) {
                    return true;
                } else {
                    return false;
                }
            } else if (dealerCardTotal >= 7) {
                return false;
            }
        } else{
            if (dealerCardTotal < 6){
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public BaccaratOutcome getWinner(Player player, Dealer dealer){
        int playerHandTotal = getHandTotal(player);
        int dealerHandTotal = getHandTotal(dealer);
        System.out.println("\nThe player's hand total is: " + playerHandTotal);
        System.out.println("\nThe dealer's hand total is: " + dealerHandTotal);
        if (dealerHandTotal == playerHandTotal){
            System.out.println("\nThe player and the dealer drew.");
            return BaccaratOutcome.TIE; // draw
        } else if (dealerHandTotal > playerHandTotal){
            System.out.println("\nThe player lost against the dealer.");
            return BaccaratOutcome.DEALER; // dealer wins
        } else {
            System.out.println("\nThe player won against the dealer.");
            return BaccaratOutcome.PLAYER; // player wins
        }

    }

    public void playersMakeBets(Scanner scanner){
        for (Player player: players) {
            BaccaratBet bet = player.makeBaccaratBet(scanner);
            betList.add(bet);
        }
    }

    public void payOutBets(BaccaratOutcome betType){
        for (BaccaratBet bet: betList) {
            if (bet.getBaccaratOutcome() == betType){
                bet.getPlayer().addChips(bet.getReturn());
                dealer.removeChips(bet.getReturn());
                System.out.printf("\nCongrats %s, your bet was successful! You have gained %d chips and now have a total of %d chips\n", bet.getPlayer().getName(), bet.getReturn(), bet.getPlayer().getNumberOfChips());
            } else {
                bet.getPlayer().removeChips(bet.getBetAmount());
                dealer.addChips(bet.getBetAmount());
                System.out.printf("\nSorry %s, your bet was not successful! You have lost %d chips and now have a total of %d chips\n", bet.getPlayer().getName(), bet.getBetAmount(), bet.getPlayer().getNumberOfChips());
            }
        }
    }

    public void play(Scanner scanner){
        System.out.println("Welcome to the Baccarat Game!");
        playersMakeBets(scanner);
        dealer.getDeck().addDeck();
        dealer.getDeck().shuffle();
        initialDeal(players.get(0), dealer);
        if (isThirdCardDrawnToPlayer(players.get(0))){
            Card card = dealer.dealCard(players.get(0));
            System.out.printf("\nPlayer has been given a third card which is a %s of %s", card.getRank(), card.getSuit());
        }
        if (isThirdCardDrawnToDealer(dealer, players.get(0))){
            Card card = dealer.dealCard(dealer);
            System.out.printf("The dealer has been given a third card which is a %s of %s", card.getRank(), card.getSuit());
        }
        BaccaratOutcome betType = getWinner(players.get(0), dealer);
        payOutBets(betType);

        for (Player player : players ) {
            player.getHand().clear();
        }
        dealer.getDeck().clear();
        dealer.getHand().clear();
        this.players.clear();
        this.betList.clear();
    }

    public int numOfPlayers() {
        return players.size();
    }



}
