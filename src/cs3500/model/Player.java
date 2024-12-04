package cs3500.model;

/**
 * I'm currently unsure as to what implements this interface and why.
 */
public interface Player {

  /**
   * Method to find the best move available.
   * @param model the inputted model
   * @param playerColor the inputted player color
   * @return the best move as an array of integers: cardIndex, row, column.
   */
  int[] findMove(TriosModel model, PlayerColor playerColor);

}
