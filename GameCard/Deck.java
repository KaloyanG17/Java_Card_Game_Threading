package GameCard;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;

public class Deck {
    private ArrayList<Card> deck;
    private int deckID;
    private static int deckCount = 0;

    public int getDeckID() {return deckID;}
    public synchronized ArrayList<Card> getDeck(){return deck;}
    public int getDeckCount(){return deckCount;}

    public Deck(){
        this.deck = new ArrayList<Card>();
        this.deckID = 0;
    }

    public Deck(ArrayList<Card> deck){ // create deck
        this.deck = deck;
        this.deckID = ++deckCount;
    }

    // returns the first card in the deck
    public synchronized int getTopCard() {
        if((deck == null) || (deck.size() == 0)) {
            return -1;
        } else {
            Card temp = deck.get(0);
            deck.remove(0);
            return temp.getValue();
        }
    }

    // adds a card to the bottom of the deck
    public synchronized void putBottom(int card) {
        Card temp = new Card(card);
        deck.add(temp);
    }

    public void createFile() {
        try {
            // Create a file object
            File deckFile = new File("deck" + this.deckID + "_output.txt");
            // Try to create a new file if it doesn't exist write to it
            if (deckFile.createNewFile()) {
                BufferedWriter deckWriter;
                try {
                    // Write to the file using the FileWriter method
                    deckWriter = new BufferedWriter(new FileWriter("deck" + this.deckID + "_output.txt", true));
                    // Write the decks final hand to the file by appending to a string to get in correct format
                    String handTemp = "";
                    for (int i=0; i<deck.size(); i++) {
                        handTemp += deck.get(i).getValue() + " ";
                    }
                    deckWriter.write("Deck " + this.deckID + " contents: " + handTemp);
                    deckWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                // If file already exists, delete it and create a new one
                deckFile.delete();
                createFile();
            }
        } catch (IOException e) {
            // Catch exception if any occurs while creating file
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}


    
