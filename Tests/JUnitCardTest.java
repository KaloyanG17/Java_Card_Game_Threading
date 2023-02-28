package Tests;
import org.junit.Test;
import static org.junit.Assert.*;
import GameCard.Card;


public class JUnitCardTest {

    // Test creation of a card with value 1
    @Test
    public void testCreateCard1() {
        Card c = new Card(1);
        assertEquals(1, c.getValue());
        assertNotEquals(2, c.getValue());
    }

    // Test creation of a card with value 2
    @Test
    public void testCreateCard2() {
        Card c = new Card(2);
        assertEquals(2, c.getValue());
        assertNotEquals(1, c.getValue());
    }

    // Test creation of a card with negative value
    @Test
    public void testCreateCardNegative() {
        try {
            Card card = new Card(-1);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertEquals(e.getMessage(), "Card value cannot be negative");
        }
    }

    // Test setting the value of a card to 0
    @Test
    public void testCreateCard0() {
        Card c = new Card(0);
        assertEquals(0, c.getValue());
        assertNotEquals(1, c.getValue());
    }

    // Test setting the value of a card to 1 from value 2
    @Test
    public void testSetValue1() {
        Card c = new Card(2);
        c.setValue(1);
        assertEquals(1, c.getValue());
        assertNotEquals(2, c.getValue());
    }

    // Test setting the value of a card to 2 from value 1
    @Test
    public void testSetValue2() {
        Card c = new Card(1);
        c.setValue(2);
        assertEquals(2, c.getValue());
        assertNotEquals(1, c.getValue());
    }

    // Test setting the value of a card to negative value
    @Test
    public void testSetValueNegative() {
        try {
            Card card = new Card(1);
            card.setValue(-1);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
            assertEquals(e.getMessage(), "Card value cannot be negative");
        }
    }

    // Test setting the value of a card to 0 from value 1
    @Test
    public void testSetValue0() {
        Card c = new Card(1);
        c.setValue(0);
        assertEquals(0, c.getValue());
        assertNotEquals(1, c.getValue());
    }    

}