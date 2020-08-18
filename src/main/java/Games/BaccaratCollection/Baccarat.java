package Games.BaccaratCollection;

import DeckOfCardsClasses.Card;
import DeckOfCardsClasses.CardDeck;
import DeckOfCardsClasses.CardRank;
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

//    public void setUpCardValues(){
//        // 10/Jack/Queen/King = 0
//        CardRank.TEN.setValue(0);
//        CardRank.JACK.setValue(0);
//        CardRank.QUEEN.setValue(0);
//        CardRank.KING.setValue(0);
//    }

    public void initialDeal(Player player, Dealer dealer){
            dealer.dealCard(player);
            dealer.dealCard(player);
            dealer.dealCard(dealer);
            dealer.dealCard(dealer);
            Card playerCard1 = player.getHand().get(0);
            Card playerCard2 = player.getHand().get(1);
            Card dealerCard1 = dealer.getHand().get(0);
            Card dealerCard2 = dealer.getHand().get(1);
            System.out.printf("Player %s has %s of %s and %s of %s \n", player.getName(), playerCard1.getRank(), playerCard1.getSuit(), playerCard2.getRank(), playerCard2.getSuit());
            System.out.printf("Dealer %s has %s of %s and %s of %s", dealer.getName(), dealerCard1.getRank(), dealerCard1.getSuit(), dealerCard2.getRank(), dealerCard2.getSuit());
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

        if (dealerHandTotal == playerHandTotal){
            return BaccaratOutcome.DRAW; // draw
        } else if (dealerHandTotal > playerHandTotal){
            return BaccaratOutcome.LOSS; // dealer wins
        } else {
            return BaccaratOutcome.WIN; // player wins
        }
    }

    public void playersMakeBets(Scanner scanner){
        String input = scanner.nextLine();
        BaccaratOutcome betType;
        for (Player player: players) {
            while(true) {
                if(input.equalsIgnoreCase("win")) {
                    betType = BaccaratOutcome.WIN;
                    break;
                } else if(input.equalsIgnoreCase("loss")){
                    betType = BaccaratOutcome.LOSS;
                    break;
                } else if(input.equalsIgnoreCase("draw")){
                    betType = BaccaratOutcome.DRAW;
                    break;
                } else {
                    System.out.println("Please make a bet");
                    input = scanner.nextLine();
                }
            }
            System.out.println("How much do you want to bet?");
            int betAmount = scanner.nextInt();
            BaccaratBet bet = new BaccaratBet(player, betAmount, betType);
            betList.add(bet);
        }
    }

    public void payOutBets(BaccaratOutcome betType){
        for (BaccaratBet bet: betList) {
            if (bet.getBaccaratOutcome() == betType){
                bet.getPlayer().addChips(bet.getBetAmount());
                dealer.removeChips(bet.getBetAmount());
            } else {
                bet.getPlayer().removeChips(bet.getBetAmount());
                dealer.addChips(bet.getBetAmount());
            }
        }
    }

    public void play(Scanner scanner){
        playersMakeBets(scanner);
        initialDeal(players.get(0), dealer);
        if (isThirdCardDrawnToPlayer(players.get(0))){
            dealer.dealCard(players.get(0));
        }
        if (isThirdCardDrawnToDealer(dealer, players.get(0))){
            dealer.dealCard(dealer);
        }
        BaccaratOutcome betType = getWinner(players.get(0), dealer);
        payOutBets(betType);
    }



}
