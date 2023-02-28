package GameCard;

public class Card {
    private int value;

    // Constructor for the card class
    public Card(int value) {
        if(value >= 0) {
            this.value = value;
        } else {
            throw new NumberFormatException("Card value cannot be negative");
        }
    }

    // Get value of the card
    public int getValue() {
        return value;
    }
    
    // Set value of the card
    public void setValue(int value) {
        if(value >= 0) {
            this.value = value;
        } else {
            throw new NumberFormatException("Card value cannot be negative");
        }
    }
}