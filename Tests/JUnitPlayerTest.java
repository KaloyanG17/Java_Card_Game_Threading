package Tests;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;
import GameCard.Card;
import GameCard.Player;


public class JUnitPlayerTest {

    // Test creation of a player with no cards
    @Test
    public void testCreatePlayer() {
        Player p = new Player();
        assertEquals(0, p.getPlayerID());
        assertNotEquals(1, p.getPlayerID());
    }

    // Test creation of a player with 4 card
    @Test
    public void testCreatePlayer1() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        Player p = new Player(deck);
        assertEquals(4, p.getPlayer().size());
        assertNotEquals(1, p.getPlayer().size());
    }

    // Test creating of a player with a negative card value
    @Test
    public void testCreatePlayerNegative() {
        try {
            Card c1 = new Card(-1);
            ArrayList<Card> deck = new ArrayList<Card>();
            deck.add(c1);
            Player p = new Player(deck);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertEquals(e.getMessage(), "Card value cannot be negative");
        }
    }

    // Test creation of player with card value 0
    @Test
    public void testCreatePlayer0() {
        Card c1 = new Card(0);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        Player p = new Player(deck);
        assertEquals(1, p.getPlayer().size());
        assertNotEquals(0, p.getPlayer().size());
    }

    // Test creation of 2 players with 4 cards each
    @Test
    public void testCreate2Player() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        Player p1 = new Player(deck);
        Player p2 = new Player(deck);
        assertEquals(4, p1.getPlayer().size());
        assertEquals(4, p2.getPlayer().size());
        assertNotEquals(1, p1.getPlayer().size());
        assertNotEquals(1, p2.getPlayer().size());
    }
    
    // Test creation of 2 players with negative card value
    @Test
    public void testCreate2PlayerNegative() {
        try {
            Card c1 = new Card(-1);
            ArrayList<Card> deck = new ArrayList<Card>();
            deck.add(c1);
            Player p1 = new Player(deck);
            Player p2 = new Player(deck);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertEquals(e.getMessage(), "Card value cannot be negative");
        }
    }

    // Test creation of 2 players with card value 0
    @Test
    public void testCreate2Player0() {
        Card c1 = new Card(0);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        Player p1 = new Player(deck);
        Player p2 = new Player(deck);
        assertEquals(1, p1.getPlayer().size());
        assertEquals(1, p2.getPlayer().size());
        assertNotEquals(0, p1.getPlayer().size());
        assertNotEquals(0, p2.getPlayer().size());
    }

    // Test getDiscradCard() method with 4 card which isnt its playerID (3 for this player)
    @Test
    public void testGetDiscardCard1() {
        Card c1 = new Card(3);
        Card c2 = new Card(2);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c2);
        deck.add(c2);
        deck.add(c2);
        deck.add(c1);
        Player pDis = new Player(deck);
        int card = pDis.getDiscardCard();
        assertEquals(2, card);
        assertNotEquals(3, card);
    }

    // Test getDiscardCard() method with 0 card value
    @Test
    public void testGetDiscardCard0() {
        Card c1 = new Card(0);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        Player pDis = new Player(deck);
        int card = pDis.getDiscardCard();
        assertEquals(0, card);
        assertNotEquals(1, card);
    }

    // Test putBottom() method with 4 card
    @Test
    public void testPutBottom() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        int c2 = 2;
        Player p = new Player(deck);
        p.putBottom(c2);
        assertEquals(5, p.getPlayer().size());
        assertNotEquals(4, p.getPlayer().size());
        assertEquals(2, p.getPlayer().get(4).getValue());
        assertNotEquals(1, p.getPlayer().get(4).getValue());
    }

    // Test putBottom() method with 0 cards
    @Test
    public void testPutBottomEmpty() {
        Player p = new Player();
        int c1 = 1;
        p.putBottom(c1);
        assertEquals(1, p.getPlayer().size());
        assertNotEquals(0, p.getPlayer().size());
        assertEquals(1, p.getPlayer().get(0).getValue());
        assertNotEquals(0, p.getPlayer().get(0).getValue());
    }

    // Test putBottom() method with negative card value
    @Test
    public void testPutBottomNegative() {
        try {
            Card c1 = new Card(1);
            ArrayList<Card> deck = new ArrayList<Card>();
            deck.add(c1);
            deck.add(c1);
            deck.add(c1);
            deck.add(c1);
            int c2 = -1;
            Player p = new Player(deck);
            p.putBottom(c2);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertEquals(e.getMessage(), "Card value cannot be negative");
        }
    }

    // Test putBottom() method with 0 card value
    @Test
    public void testPutBottom0() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        int c2 = 0;
        Player p = new Player(deck);
        p.putBottom(c2);
        assertEquals(5, p.getPlayer().size());
        assertNotEquals(4, p.getPlayer().size());
        assertEquals(0, p.getPlayer().get(4).getValue());
        assertNotEquals(1, p.getPlayer().get(4).getValue());
    }

    // Test checkWin() method with 4 cards
    @Test
    public void testCheckWinWinner() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        Player p = new Player(deck);
        boolean win = p.checkWin();
        assertEquals(true, win);
        assertNotEquals(false, win);
    }

    // Test checkWin() method with 4 cards
    @Test
    public void testCheckWinLoser() {
        Card c1 = new Card(1);
        Card c2 = new Card(2);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c2);
        deck.add(c2);
        deck.add(c1);
        Player p = new Player(deck);
        boolean win = p.checkWin();
        assertEquals(false, win);
        assertNotEquals(true, win);
    }

    // Test checkWin() method with 0 cards
    @Test
    public void testCheckWinEmpty() {
        Player p = new Player();
        boolean win = p.checkWin();
        assertEquals(false, win);
        assertNotEquals(true, win);
    }

    // Test checkWin() method with 2 card
    @Test
    public void testCheckWin2() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        Player p = new Player(deck);
        boolean win = p.checkWin();
        assertEquals(false, win);
        assertNotEquals(true, win);
    }

    // Test createFile() method with 4 cards
    @Test
    public void testCreateFile() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        Player p = new Player(deck);
        Scanner scanner;
        try {
            p.createFile();
            scanner = new Scanner(new File("player1.txt"));
            assertEquals("Player 1 initial hand is 1 1 1 1", scanner);
            assertNotEquals("Player 1 initial hand is  1 1 1 2", scanner);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
    // Test writeContentToFile() method with 4 cards
    @Test
    public void testWriteContentsToFile() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        Player p = new Player(deck);
        Scanner scanner;
        try {
            p.createFile();
            p.writeContentsToFile(1,2);
            scanner = new Scanner(new File("player1.txt"));
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Test winner() method with 4 cards
    @Test
    public void testWinner() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        Player p = new Player(deck);
        try {
            p.createFile();
            p.winner();
            Scanner scanner = new Scanner(new File("player1.txt"));
            assertEquals("Player 1 wins!", scanner);
            assertNotEquals("Player 1 loses!", scanner);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Test loser() method with 4 cards
    @Test
    public void testLoser() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        Player p = new Player(deck);
        try {
            p.createFile();
            p.loser(2);
            Scanner scanner = new Scanner(new File("player1.txt"));
            assertEquals("Player 1 loses!", scanner);
            assertNotEquals("Player 1 wins!", scanner);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
