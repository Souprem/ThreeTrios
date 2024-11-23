package cs3500.controller;

import cs3500.model.PlayerColor;

/**
 * Features interface meant to listen for the features of the model.
 * These features
 * - (playerTurnChanged) listen for the turn changing from one player to another
 * - (gameOver) listen for the model to say the game is over
 * - (addListener) add the given TriosController as a listener for this features.
 */
public interface ModelFeatures {

  /**
   * Called within the model when the turn has been
   * changed to a new player. This method notifies
   * this interface's listener when it's called.
   */
  void playerTurnChanged();

  /**
   * Adds the given TriosController to this interface
   * to act as a listener for this interface.
   * @param listener the given listener.
   */
  void addListener(TriosController listener);

  /**
   * Called within the model when game is over
   * for whatever reason. This method notifies
   * this interface's listener when it's called.
   * @param winner the winner of the game, null if there is no winner.
   */
  void gameOver(PlayerColor winner);
}
