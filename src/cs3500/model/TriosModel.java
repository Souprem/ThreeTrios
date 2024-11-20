package cs3500.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.controller.ModelFeatures;
import cs3500.controller.TriosController;

/**
 * A representation of a model for the three trios card game.
 * The origin, 0,0, is at the top left of the screen. The 2D array used to represent data
 * storage is indexed by row, column.
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
   */
  void startGame(int numCardCells, CardConfigReader cardReader, BoardConfigReader boardReader);

  void addObserver(TriosController triosController);

  void removeObserver(TriosController triosController);

  void notifyObservers();

  void addFeatures(ModelFeatures modelFeatures);


}
