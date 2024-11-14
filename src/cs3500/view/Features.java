package cs3500.view;

import cs3500.model.Player;

public interface Features {

  /**
   * Prints information about the selected card to the console.
   * @param indexInHand the index of the card in the hand
   * @param owner the owner of the card
   */
  void cardInHandInfo(int indexInHand, Player owner);

  /**
   * Prints information about the selected cell to the console.
   * @param row the row of the cell
   * @param col the column of the cell
   */
  void cellInfo(int row, int col);
}
