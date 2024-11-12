package cs3500.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of a model for the three trios card game.
 *
 * EXPLAIN
 * (a) where the origin is, (b) what the axes are, (c) how
 * the hex-coordinates are mapped to cartesian coordinates (if
 * they're using a 2d list as their data storage, they need to tell us
 * which index is the row and which index is the column)
 *
 * @param <C> The type of card used in the game.
 */
public interface TriosModel<C extends Card> extends ReadOnlyTriosModel{


  /**
   * Plays a card to the game board, updating the board and inciting
   * battle moves, which recursively occur until there are no longer
   * opposing cards to battle or every battle is lost.
   * @param cardIndex the index in the hand of the card being played.
   * @param row the row being played to.
   * @param col the column being played to.
   */
  void playCard(int cardIndex, int row, int col);



  void battleStep(List<ArrayList<Integer>> currentCards);

  /**
   * Initializes the boards, deck, and hands to be used in playing of the game.
   * @param numCardCells number of card cells on the board.
   * @param cardFile a string representing the file path of a card file.
   * @param boardFile a string representing the file path of a board file.
   */
  void startGame(int numCardCells, String cardFile, String boardFile);


}
