package cs3500.model;

/**
 * An interface to represent all the possible AI actions,
 * including all available player actions in addition to AI-specific
 * methods that AI implementations will use to play the game.
 */
public interface TriosAI {

  /**
   * Returns an array of integers representing the card index, row, column, and score
   * associated with a particular move.
   * @param model inputted model
   * @param playerColor inputted player color
   * @return an array of integers
   */
  int[] findMove(ReadOnlyTriosModel model, PlayerColor playerColor);

  /**
   * The default method called by all subclasses when there are no valid moves.
   * @param model the inputted model.
   * @return an array of integers representing the ______
   */
  default int[] noValidMoves(ReadOnlyTriosModel model) {
    for (int i = 0; i < model.getStatusBoard().length; i++) {
      for (int j = 0; j < model.getStatusBoard()[0].length; j++) {
        if (model.getStatusBoard()[i][j].equals(Status.EMPTY)) {
          return new int[]{1, i, j, 0};
        }
      }
    }
    throw new IllegalStateException("No empty spots on the board");
  }
}
