package Tests;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;
import GameCard.Deck;
import GameCard.Card;


public class JUnitDeckTest {

    // Test creation of a deck with no cards
    @Test
    public void testCreateDeck() {
        Deck d = new Deck();
        assertEquals(0, d.getDeckID());
        assertNotEquals(1, d.getDeckID());
    }

    // Test creation of a deck with 4 card
    @Test
    public void testCreateDeck1() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        Deck d = new Deck(deck);
        assertEquals(4, d.getDeck().size());
        assertNotEquals(1, d.getDeck().size());
    }

    // Test creation of 2 decks with 4 cards each
    @Test
    public void testCreateDeck2() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        Deck d1 = new Deck(deck);
        Deck d2 = new Deck(deck);
        assertEquals(4, d1.getDeck().size());
        assertEquals(4, d2.getDeck().size());
        assertNotEquals(1, d1.getDeck().size());
        assertNotEquals(1, d2.getDeck().size());
    }

    // Test creation of a deck with a negative card value
    @Test
    public void testCreateDeckNegative() {
        try {
            Card c1 = new Card(-1);
            ArrayList<Card> deck = new ArrayList<Card>();
            deck.add(c1);
            Deck d = new Deck(deck);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertEquals(e.getMessage(), "Card value cannot be negative");
        }
    }

    // Test creation of a deck with a card value of 0
    @Test
    public void testCreateDeck0() {
        Card c1 = new Card(0);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        Deck d = new Deck(deck);
        assertEquals(1, d.getDeck().size());
        assertNotEquals(0, d.getDeck().size());
    }

    // Test getTopCard() with a deck of 4 cards and removing of a card
    @Test
    public void testGetTopCard1() {
        Card c1 = new Card(1);
        Card c2 = new Card(2);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c2);
        deck.add(c2);
        Deck d = new Deck(deck);
        int cardValue = d.getTopCard();
        assertEquals(1, cardValue);
        assertNotEquals(2, cardValue);
        // Test the removing the top card
        assertNotEquals(2, d.getTopCard());
    }

    // Test getTopCard() with a deck of 0 cards and removing of a card
    @Test
    public void testGetTopCardEmpty() {
        Deck dEmpty = new Deck();
        int cardValue = dEmpty.getTopCard();
        assertEquals(-1, cardValue);
        assertNotEquals(1, cardValue);
    }

    // Test getTopCard() with a deck of 1 card and removing of a card
    @Test
    public void testGetTopCardEmpty2() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        Deck d = new Deck(deck);
        int cardValue = d.getTopCard();
        assertEquals(1, cardValue);
        assertNotEquals(2, cardValue);
        // Test the removing the top card to see if list is empty
        assertEquals(-1, d.getTopCard());
    }

    // Test putBottom() with a deck of 0 cards and adding a card
    @Test
    public void testPutBottomEmpty() {
        Deck dEmpty = new Deck();
        int c1 = 1;
        dEmpty.putBottom(c1);
        assertEquals(1, dEmpty.getDeck().size());
        assertNotEquals(0, dEmpty.getDeck().size());
    }

    // Test putBottom() with a deck of 1 card and adding a card
    @Test
    public void testPutBottom1() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        Deck d = new Deck(deck);
        int c2 = 2;
        d.putBottom(c2);
        assertEquals(2, d.getDeck().size());
        assertNotEquals(1, d.getDeck().size());
    }

    // Test putBottom() with a deck of 4 cards and adding a negative card
    @Test
    public void testPutBottomNegative() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        Deck d = new Deck(deck);
        try {
            int c2 = -1;
            d.putBottom(c2);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertEquals(e.getMessage(), "Card value cannot be negative");
        }
    }

    // Test putBottom() with a deck of 4 cards and adding a card value of 0
    @Test
    public void testPutBottom0() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        Deck d = new Deck(deck);
        int c2 = 0;
        d.putBottom(c2);
        assertEquals(5, d.getDeck().size());
        assertNotEquals(4, d.getDeck().size());
    }

    // Test createFile() with a deck of 0 cards
    @Test
    public void testCreateFileEmpty() {
        Deck dEmpty = new Deck();
        Scanner scanner;
        try {
            scanner = new Scanner(new File("deck1.txt"));
            scanner.close();
        dEmpty.createFile();
        assertEquals("Deck1 contents: ", scanner);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Test createFile() with a deck of 4 card
    @Test
    public void testCreateFile1() {
        Card c1 = new Card(1);
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        deck.add(c1);
        Deck d = new Deck(deck);
        Scanner scanner;
        try {
            d.createFile();
            scanner = new Scanner(new File("deck1.txt"));
            assertEquals("Deck1 contents: 1 1 1 1", scanner);
            assertNotEquals("Deck1 contents: 1 1 1 2", scanner);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    
}
