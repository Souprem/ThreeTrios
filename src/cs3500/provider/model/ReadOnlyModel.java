package cs3500.provider.model;

import java.util.List;

import cs3500.provider.CardInCell;

/**
 * Interface for a ReadOnly version of the model. Interface has only observable methods.
 */
public interface ReadOnlyModel {

  /**
   * Retrieves all cards involved in the game.
   *
   * @return A list of TripleTriosCard instances.
   */
  List<CardInCell> allNumCards();

  /**
   * Gets the current player's turn.
   *
   * @return The current player's turn.
   */
  PlayerTurnEnum getPlayerTurn();

  /**
   * Gets current players Hand.
   *
   * @return the current players hand
   */
  List<CardInCell> getPlayersTurnHand();


  /**
   * Gets a players hand regardless of who's turn it is.
   *
   * @param playerTurn either red or blue players turn
   * @return chosen players hand
   */
  List<CardInCell> getPlayerHand(PlayerTurnEnum playerTurn);

  /**
   * Checks if the game is over.
   *
   * @return True if the game has ended, false otherwise.
   */
  boolean isGameOver();

  /**
   * Determines which player won the game based on cards placed.
   *
   * @return The winning player.
   */
  PlayerTurnEnum whoWon();

  /**
   * Gets the current game board state.
   *
   * @return A 2D array of Cells representing the game board.
   */
  Cells[][] getBoard();
}
