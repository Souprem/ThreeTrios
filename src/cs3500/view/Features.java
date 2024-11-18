package cs3500.view;

/**
 * Interface for the possible features of a three trios card game.
 */
public interface Features {

  /**
   * Prints information about the selected card to the console.
   * @param indexInHand the index of the card in the hand
   * @param owner the owner of the card (-1 for blue, 1 for red)
   */
  void cardInHandInfo(int indexInHand, int owner);

  /**
   * Prints information about the selected cell to the console.
   * @param row the row of the cell
   * @param col the column of the cell
   */
  void cellInfo(int row, int col);
}
