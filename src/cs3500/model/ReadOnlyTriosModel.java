package cs3500.model;

import java.util.List;

public interface ReadOnlyTriosModel<C extends Card> {

  /**
   * Returns the player whose turn it currently is.
   * @return a String representing the current player.
   */
  String getCurrentPlayer();


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
  List<C> getCurrentHand();

  /**
   * Returns the current board of cards as a 2D array.
   * @return a 2D array of C representing the cardBoard.
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
