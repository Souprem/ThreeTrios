package cs3500.provider.controller;

import cs3500.provider.model.Player;
import cs3500.provider.model.PlayerTurnEnum;
import cs3500.provider.model.ThreeTriosModel;

/**
 * Interface representing the actions a player can perform during the game.
 */
public interface PlayerAction extends Player {
  /**
   * Registers a listener for player actions.
   *
   * @param listener the listener to be notified of player actions
   */
  void addPlayerActionListener(PlayerActionListener listener);

  /**
   * Triggers the player's move.
   *
   */
  void performAction(ThreeTriosModel model);

  boolean isHuman();

  PlayerTurnEnum getTurn();
}

