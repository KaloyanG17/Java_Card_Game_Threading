package GameCard;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;

public class Player {

    //Initializes the player variables and the counter for the number of turns
    private ArrayList<Card> player;
    private int playerID;
    private static int playerCount = 0;

    //Get constructs for the player 
    public int getPlayerID() {return playerID;}
    public synchronized ArrayList<Card> getPlayer(){return player;}
    public int getPlayerCount(){return playerCount;}

    //Constructor for the player
    public Player(){
        this.player = new ArrayList<Card>();
        this.playerID = 0;
    }

    public Player(ArrayList<Card> player){ 
        this.player = player;
        this.playerID = ++playerCount;
    }

    // Gets the discard card from the player using a stratergy 
    public synchronized int getDiscardCard() {
        Boolean pref = true;
        int temp = 0;
        // If the players ID is not the same as the random card chosen then they will discard it
        while (pref== true) {
            int strat = (int) Math.floor(Math.random()*(3-0+1)+0);
            if (this.player.get(strat).getValue() != this.playerID) {
                pref = false;
                temp = this.player.get(strat).getValue();
                //It then removes the card from the players hand
                this.player.remove(strat);
            }
            // else pick another card
        }
        //Returns the card that was discarded
        return temp;
    }

    // adds a card to the bottom of the deck
    public synchronized void putBottom(int card) {
        Card cardTemp = new Card(card);
        player.add(cardTemp);
    }

    // Players turn
    public synchronized Boolean playerTurn(List<Deck> deckArr){
        //Find the index of the discard 
        int discardDeckPos = this.playerID;
        // The last player will always discard to the first deck in the array
        if(discardDeckPos == deckArr.size()){discardDeckPos = 0;}
        // Gets the deck object for that index
        Deck discardDeck = deckArr.get(discardDeckPos);
        int discardCard = getDiscardCard();
        // Adds the discarded card to the bottom of the deck
        discardDeck.putBottom(discardCard);
        // gets the top card from the previous deck
        Deck drawDeck = deckArr.get(this.playerID-1);
        int drawCard = drawDeck.getTopCard();
        // Adds the drawn card to the bottom of the deck
        putBottom(drawCard);
        // Writes the players hand and moves to the file
        writeContentsToFile(drawCard, discardCard);
        // Checks if the player has won
        return checkWin();
    }

    // Checks if the player has won
    public Boolean checkWin(){
        // Check if each of the cards are qual to the first one
        if(player.size() == 4){
            for(int i=1; i<=(player.size()); i++){
                if((player.get(0).getValue() == player.get(1).getValue())&& (player.get(0).getValue() == player.get(2).getValue())&& (player.get(0).getValue()== player.get(3).getValue())){  
                    // If all equal return true
                    return true;    
                }
            }
        } 
        // If not return false
        return false;
    }
    

    //This method is used to create a file which will hold the players actions and decks
   public void createFile() {
        try {
            // Create a file object
            File playerFile = new File("player" + this.playerID + "_output.txt");
            // Try to create a new file if it doesn't exist write to it
            if (playerFile.createNewFile()) {
                BufferedWriter playerWriter;
                try {
                    // Write to the file using the FileWriter method
                    playerWriter = new BufferedWriter(new FileWriter("player" + this.playerID + "_output.txt", true));
                    // Write the players initial hand to the file by appending to a string to get in correct format
                    String handTemp = "";
                    for (int i=0; i<player.size(); i++) {
                        handTemp += player.get(i).getValue() + " ";
                    }
                    playerWriter.write("Player " + this.playerID + " initial hand is " + handTemp);
                    playerWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                // If file already exists, delete it and create a new one
                playerFile.delete();
                createFile();
            }
        } catch (IOException e) {
            // Catch exception if any occurs while creating file
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Writes the contents of the deck to file and the players actions
    public void writeContentsToFile(int draws, int discards) {
        // Create a file writer object
        BufferedWriter playerWriter;
        try {
            // Write to the file using the FileWriter method and appends to the file
            playerWriter = new BufferedWriter(new FileWriter("player" + this.playerID + "_output.txt", true));
            // Writes the card that is drawn from the deck before it
            playerWriter.newLine();
            playerWriter.newLine();
            playerWriter.write("Player " + this.playerID + " draws a " + draws + " from deck " + this.playerID);
            playerWriter.newLine();
            // Writes the players discarded card and which deck they discard it to
            int tempNextDeck = this.playerID + 1;
            if (tempNextDeck >= playerCount + 1) {
                tempNextDeck = 1;
            }
            playerWriter.write("Player " + this.playerID + " discards a " + discards + " to deck " + tempNextDeck);
            playerWriter.newLine();
            // Writes the players hand after drawing and discard a card ready for the next turn
            String handTemp = "";
            for (int i=0; i<player.size(); i++) {
                handTemp += player.get(i).getValue() + " ";
            }
            playerWriter.write("Player " + this.playerID + " current hand is " + handTemp);
            playerWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    // Writes to the file that the player has won
    public void winner(){
        // Create a file writer object
        BufferedWriter playerWriter;
        try {
            // Write to the file using the FileWriter method and appends to the file and there final hand
            playerWriter = new BufferedWriter(new FileWriter("player" + this.playerID + "_output.txt", true));
            // Writes the card that is drawn from the deck before it
            playerWriter.newLine();
            playerWriter.newLine();
            playerWriter.write("Player " + this.playerID + " wins");
            playerWriter.newLine();
            playerWriter.write("Player " + this.playerID + " exits");
            playerWriter.newLine();
            String handTemp = "";
            for (int i=0; i<player.size(); i++) {
                handTemp += player.get(i).getValue() + " ";
            }
            playerWriter.write("Player " + this.playerID + " current hand is " + handTemp);
            playerWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    // Writes to the file which player has won and there final hand
    public void loser(int winner){
        // Create a file writer object
        BufferedWriter playerWriter;
        try {
            // Write to the file using the FileWriter method and appends to the file
            playerWriter = new BufferedWriter(new FileWriter("player" + this.playerID + "_output.txt", true));
            // Writes the card that is drawn from the deck before it
            playerWriter.newLine();
            playerWriter.newLine();
            playerWriter.write("Player " + winner + " has informed Player " + this.playerID + " that Player " + winner + " has won");
            playerWriter.newLine();
            playerWriter.write("Player " + this.playerID + " exits");
            playerWriter.newLine();
            String handTemp = "";
            for (int i=0; i<player.size(); i++) {
                handTemp += player.get(i).getValue() + " ";
            }
            playerWriter.write("Player " + this.playerID + " current hand is " + handTemp);
            playerWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
 