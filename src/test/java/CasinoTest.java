import Casino.Casino;
import Games.Roulette;
import Interfaces.IPlay;
import People.Dealer;
import People.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class CasinoTest {

    Casino casino;
    Dealer dealer;

    @Before
    public void setUp(){
        casino = new Casino();
        dealer = new Dealer("John", 40);
    }

    @Test
    public void getName(){
        assertEquals("CodeClan Casino", casino.getName());
    }
    @Test
    public void getCapacity(){
        assertEquals(1000, casino.getCapacity());
    }
    @Test
    public void getAmountOfMoney(){
        assertEquals(1000000, casino.getAmountOfMoney(),0.0);
    }
    @Test
    public void getNumberOfChips(){
        assertEquals(10000, casino.getNumberOfChips());
    }

    @Test
    public void startWithZeroPlayers(){
        assertEquals(0, casino.getPlayers().size());
    }

    @Test
    public void canSetName(){
        casino.setName("Casino1");
        assertEquals("Casino1", casino.getName());
    }

    @Test
    public void canSetCapacity(){
        casino.setCapacity(500);
        assertEquals(500, casino.getCapacity());
    }
    @Test
    public void canSetAmountOfMoney(){
        casino.setAmountOfMoney(500);
        assertEquals(500, casino.getAmountOfMoney(),0.0);
    }
    @Test
    public void canAddPlayerToCasinoIfOldEnough(){
        Player player = new Player ("John",21,600);
        casino.addPlayerToCasino(player);
        assertEquals(1, casino.getPlayers().size() );
    }
    @Test
    public void cantAddPlayerToCasinoIfNotOldEnough(){
        Player player = new Player ("John",17,600);
        casino.addPlayerToCasino(player);
        assertEquals(0, casino.getPlayers().size() );
    }

    @Test
    public void canRemovePlayerFromCasino(){
        Player player = new Player ("John",21,600);
        casino.addPlayerToCasino(player);
        casino.removePlayerFromCasino(player);
        assertEquals(0, casino.getPlayers().size());
    }

    @Test
    public void cantRemovePlayerIfNotInCasino(){
        Player player = new Player ("John",21,600);
        casino.removePlayerFromCasino(player);
        assertEquals(0, casino.getPlayers().size());
    }

    @Test
    public void canAddGameToCasino(){
        Roulette roulette = new Roulette(dealer);
        casino.addGameToCasino(1, roulette);
        assertEquals(1, casino.getGames().size()); 
    }







}
