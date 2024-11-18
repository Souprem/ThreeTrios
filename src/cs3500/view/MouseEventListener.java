package cs3500.view;

import cs3500.model.PlayerColor;

/**
 * An interface to represent classes that need to react to mouse events.
 */
public interface MouseEventListener {

  /**
   * Called by the MouseListenerGetCoords whenever a mouse event occurs.
   * Depending on the first input, the second two can have varying meanings.
   * @param currentPanel the panel that's been clicked, -1, 0 , 1
   * @param rowOrIndex if the panel is a hand panel, this is the index of the card in the hand,
   *                   if the panel is a board panel, this is the row clicked.
   * @param colOrPlayer if the panel is a hand panel, this is the player of that hand,
   *                    if the panel is a board panel, this is the column clicked.
   */
  void onMouseEvent(int currentPanel, int rowOrIndex, int colOrPlayer);

  /**
   * Takes in the player of the hand selected and the index in the hand of the selected card.
   * @param index index in hand
   * @param player player whose hand it is
   */
  void onSelectedHandCard(int index, int player);
}
