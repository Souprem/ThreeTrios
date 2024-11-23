package cs3500.controller;

import cs3500.model.PlayerColor;

/**
 * An interface to represent all possible player actions within
 * a game of ThreeTrios. This player can be a human player or
 * possibly an AI player.
 */
public interface PlayerActions {

  /**
   * Called from the model which holds this ViewFeatures
   * whenever the user attempts to select a card from
   * the grid. This method notifies this interface's listener
   * when it's called.
   * @param row the row on the grid at which the selection occurred.
   * @param col the column on the grid at which the selection occurred.
   */
  public void placeCardOnGrid(int row, int col);

  /**
   * Called from the model which holds this ViewFeatures
   * whenever the user attempts to select a card from either of
   * the hands on the board. This method notifies this interface's listener
   * when it's called.
   * @param handIndex the index within the hand from which the card was selected.
   */
  public void selectHandCard(int handIndex);

  /**
   * Getter for this PersonPlayer's color.
   * @return this player's color.
   */
  PlayerColor getColor();
}
