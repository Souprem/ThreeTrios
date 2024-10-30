package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * A representation of a model for the three trios card game.
 * @param <C> The type of card used in the game.
 */
public interface TriosModel<C extends Card> {

  /**
   * Returns the player whose turn it currently is.
   * @return a String representing the current player.
   */
  String getCurrentPlayer();

  /**
   * Plays a card to the game board, updating the board and inciting
   * battle moves, which recursively occur until there are no longer
   * opposing cards to battle or every battle is lost.
   * @param cardIndex the index in the hand of the card being played.
   * @param row the row being played to.
   * @param col the column being played to.
   */
  void playCard(int cardIndex, int row, int col);

  /**
   * Initializes the boards, deck, and hands to be used in playing of the game.
   * @param numCardCells number of card cells on the board.
   * @param cardFile a string representing the file path of a card file.
   * @param boardFile a string representing the file path of a board file.
   */
  void startGame(int numCardCells, String cardFile, String boardFile);

  /**
   * Returns the hand based on the inputted player.
   * @param player player whose hand is being returned.
   * @return a list of c representing the hand.
   */
  List<C> getHand(Player player);

  /**
   * Returns the current hand.
   * @return a list of c representing the current hand.
   */
  List<C> getcurrentHand();

  /**
   * Returns the current board of cards as a 2D array.
   * @return a 2D array of C representing the card board.
   */
  C [][] getCardBoard();

  /**
   * Returns the current board of statuses as a 2D array. The statuses
   * represent whether a space is a hole, filled by a card, or empty but can
   * be filled by a card.
   * @return a 2D array of Statuses representing the board of statuses.
   */
  Status[][] getStatusBoard();

  /**
   * Returns a Player representing the winner of the game.
   * @return a Player representing the winner.
   */
  Player getWinner();

  /**
   * Returns a boolean representing whether the game is over.
   * @return a boolean representing the status of the game.
   */
  boolean isGameOver();

}
