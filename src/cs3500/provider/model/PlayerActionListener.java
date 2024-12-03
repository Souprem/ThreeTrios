package cs3500.provider.model;

import cs3500.provider.controller.ViewFeatures;

/**
 * Listener interface for player actions.
 */
public interface PlayerActionListener extends ViewFeatures {

  /**
   * Called when a player performs an action.
   *
   */
  void onPlayerAction();
}
