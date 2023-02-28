package GameCard;
import java.util.ArrayList;
import java.util.List;

public class MultiThread extends Thread{

    // Initialise the variables of the class
    private List<Player> playerArr;
    private List<Deck> deckArr;
    private int player;
    private int numPlayers;

    // Constructor for the multi-threading class
    public MultiThread(int player,int numPlayers, List<Player> playerArr, List<Deck> deckArr) {
        this.playerArr = playerArr;
        this.deckArr = deckArr;
        this.player = player;
        this.numPlayers = numPlayers;
    }

    // Run method for the multi-threading class
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                // Synchronise the lists of players and decks
                synchronized (deckArr) {
                    synchronized (playerArr){
                        Player playerTurns = playerArr.get(player-1);
                        if(CardGame.winner() != 0){
                            if(CardGame.winner() == playerTurns.getPlayerID()){
                                playerTurns.winner();
                                System.out.println("Player " + player + " has won the game");
                                deckArr.get(player-1).createFile();
                            } else {
                                playerTurns.loser(CardGame.winner());
                                deckArr.get(player-1).createFile();
                            }
                            break;
                        }
                        playerTurns.playerTurn(this.deckArr);
                        //System.out.println("Player " + player + " has finished their turn");
                    }
                }
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
