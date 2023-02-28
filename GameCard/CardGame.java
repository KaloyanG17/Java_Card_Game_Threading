package GameCard;
import java.util.*;
import javax.lang.model.element.Element;

import java.io.*;
import java.lang.*;
import java.math.*;


public class CardGame{
    
    //Initializes the variables for the game
    public static ArrayList<Deck> deckArr = new ArrayList<>();
    public static ArrayList<Player> playerArr = new ArrayList<>();  
    public static int numPlayers;
    public static String fileName;
    public static List<Integer> deck = new ArrayList<Integer>(); // deck of cards through the user input


    // Get number of players and pack from user though scanners and readers through the terminal
    public static void initialiseGame() {

        // Scanner for the number of players
        System.out.println("Please enter the number of players:");
        Scanner p = new Scanner(System.in);
        numPlayers = p.nextInt();
        // Scanner for the pack location
        System.out.println("Please enter location of pack to load:");
        Scanner f = new Scanner(System.in);
        fileName = f.nextLine();
        System.out.println("Number of players is " + numPlayers);
        System.out.println("Location of pack is " + fileName);

        // Scanner which reads the given file and adds the cards to the deck
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
            while(scanner.hasNextInt()){deck.add(scanner.nextInt());}
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        // Check if pack is valid
        if (deck.size() != numPlayers*8 ) {
            System.out.println("Pack is invalid");
            System.exit(0);
        }
        Collections.shuffle(deck);
        p.close();
        f.close();
    }

    // Sort the deck and players hands using round-robin approach
    public static void initialiseDeck() {
        List<Integer> deckSorted = new ArrayList<Integer>();
        for(int j = 1; j < ((numPlayers+1)); j++){
            for(int i = 0; i < (deck.size()); i++){
                if(i%numPlayers == (j-1)){                   
                    deckSorted.add(deck.get(i));
                }
            }
        }
        int j = 0;
        int z = 0;
        for(int i=1; i<(((numPlayers)*2)+1); i++){
            if(i<(numPlayers+1)){
                ArrayList<Card> player = new ArrayList<>();
                Card card1 = new Card(deckSorted.get(j));
                Card card2 = new Card(deckSorted.get(j+1));
                Card card3 = new Card(deckSorted.get(j+2));
                Card card4 = new Card(deckSorted.get(j+3));
                player.add(card1);
                player.add(card2);
                player.add(card3);
                player.add(card4);
                Player temp = new Player(player);;
                temp.createFile();
                playerArr.add(temp);
                j=j+8;
            }else{
                ArrayList<Card> deck = new ArrayList<>();
                Card card1 = new Card(deckSorted.get(z));
                Card card2 = new Card(deckSorted.get(z+1));
                Card card3 = new Card(deckSorted.get(z+2));
                Card card4 = new Card(deckSorted.get(z+3));
                deck.add(card1);
                deck.add(card2);
                deck.add(card3);
                deck.add(card4);
                Deck temp = new Deck(deck);
                deckArr.add(temp);
                z=z+8;
            }
        }

        //Cleans up files which were made for previous games by using the max players and checking if there are files 
        //with a name which exeeds that for both decks and players to free up some space and prevent confusions 
        int k = deckArr.size()+1;
        boolean flagDeck  = true;
        boolean flagPlayer = true;
        while((flagDeck) || (flagPlayer)){
            File deckFile = new File("deck" + k + "_output.txt");
            File playerFile = new File("player" + k + "_output.txt");
            try {
                if (flagDeck){
                    if (deckFile.createNewFile()) {
                        deckFile.delete();
                        flagDeck = false;
                    } else {
                        deckFile.delete();
                    }
                }
                if (flagPlayer){
                    if (playerFile.createNewFile()) {
                        playerFile.delete();
                        flagPlayer = false;
                    } else {
                        playerFile.delete();
                    }
                }
                k++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Returns the winner ID of the game
    public static int winner(){
        for(int i=1; i<=(playerArr.size()); i++){
            ArrayList<Card> temp = playerArr.get(i-1).getPlayer();
            if((temp.get(0).getValue() == temp.get(1).getValue())&& (temp.get(0).getValue() == temp.get(2).getValue())&& (temp.get(0).getValue()== temp.get(3).getValue())){  
                return i;    
            }
        }
        return 0;
    }

    // Starts a new thread for each player for there turns
    public void startThreads(){
        for(int i = 0 ; i < (playerArr.size()) ; i++){
            MultiThread thread = new MultiThread(i+1 , numPlayers , playerArr , deckArr);
            thread.start();
        }
    }

    // Main method starting the game by calling all the methods
    public void startGame() {
        initialiseGame();
        initialiseDeck();
        startThreads();
        winner();
    }

    public static void main(String[] args) throws FileNotFoundException {
        CardGame game = new CardGame();
        game.startGame();
    }
}