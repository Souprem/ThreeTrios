package model;

import java.util.List;

import model.cards.CardInCell;
import model.grids.Cells;

/**
 * Interface for the Three Trios game model, outlining game state and rules.
 */
public interface ThreeTriosModel {

  /**
   * Starts the game by setting up the initial state, including dealing cards to players.
   */
  void startGame();

  /**
   * Places a card on the game grid at the specified position.
   *
   * @param row     The row index for the card placement.
   * @param col     The column index for the card placement.
   * @param cardIdx The index of the card in the player's hand.
   * @throws IllegalArgumentException If placement is invalid.
   */
  void placingCard(int row, int col, int cardIdx);


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
