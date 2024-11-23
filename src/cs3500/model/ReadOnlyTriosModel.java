package cs3500.model;

import java.util.List;

import cs3500.controller.ModelFeatures;

/**
 * A read only model interface for our Three trios model. This interface
 * is meant to hold only methods for the model which do not mutate the model.
 * This interface should be used to declare or pass in any model that should
 * only have viewing capabilities, not mutation capabilities.
 * @param <C> card
 */
public interface ReadOnlyTriosModel<C extends Card> {

  /**
   * Returns the player whose turn it currently is.
   *
   * @return a String representing the current player.
   */
  PlayerColor getCurrentPlayer();

  /**
   * Returns the hand based on the inputted player.
   * @param playerColor player whose hand is being returned.
   * @return a list of c representing the hand.
   */
  List<C> getHand(PlayerColor playerColor);

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
  PlayerColor getWinner();

  /**
   * Returns a boolean representing whether the game is over.
   * @return a boolean representing the status of the game.
   */
  boolean isGameOver();

  /**
   * Returns the number of rows the grid has.
   * @return the number of rows
   */
  int numRows();

  /**
   * Returns the number of columns the grid has.
   * @return the number of columns
   */
  int numCols();

  /**
   * Returns the card at a given position, returning null for an empty card cell.
   * @param row the row of the card
   * @param col the column of the card
   * @return the Card at the given position
   * @throws IllegalArgumentException if the position has a hole
   */
  Card cardAt(int row, int col);

  /**
   * Returns the status of a particular cell: whether that cell holds a hole, a card,
   * or an empty space that could be filled by a card.
   * @param row row of the cell
   * @param col column of the cell
   * @return the Status at the given position
   */
  Status statusAt(int row, int col);

  /**
   * Determines which player owns the card in a cell at a given coordinate.
   * @param row row of the cell
   * @param col column of the cell
   * @return the player who owns the card at the given cell position
   */
  PlayerColor ownerOf(int row, int col);

  /**
   * Determines whether it's legal for the current player to play at the given coordinate.
   * @param row row at which the card would be placed
   * @param col column at which the card would be placed
   * @return a boolean representing whether the move is valid
   */
  boolean validMove(int row, int col);

  /**
   * Determines the number of cards a player can flip by playing a given card
   * at a given coordinate.
   * @param row row at which the card is placed
   * @param col column at which the card is placed
   * @param cardIndex the index of the card being placed
   * @return an integer representing the number of cards flipped
   */
  int numFlipped(int row, int col, int cardIndex);

  /**
   * A score for a player is defined by the number of cards they own on the grid
   * in addition to the number of cards within their hand.
   * @param playerColor player whose score is being calculated
   * @return an integer representing the score
   */
  int calculateScore(PlayerColor playerColor);

  /**
   * Adds this model as a listener to the modelFeatures object passed in.
   * @param modelFeatures inputted features object.
   */
  void addFeatures(ModelFeatures modelFeatures);
}
