package cs3500.model;

/**
 * Class to represent the AI strategy in which the AI attempts to play a card
 * to a space that allows it to flip as many cards on a turn as possible.
 */
public class MaxFlipTriosAI implements TriosAI {

  /**
   * Finds the best move to be made based on the model and player who the AI is playing for.
   *
   *
   * @param model inputted model
   * @param player inputted player
   * @return an array of integers representing the move to be made
   */
  public int[] findMove(TriosModel model, Player player) {
    int numCardsFlipped = 0;
    //default is 1 to account for card placed
    int maxCardsFlipped = 0;
    //bestMove is list of ints [cardIndex, row, column] DOUBLE CHECK THIS!!!!!!!!!
    int[] bestMove = new int[4];
    bestMove[0] = -1;

    for (int i = 0; i < model.getStatusBoard().length; i++) {
      //j is column index
      for (int j = 0; j < model.getStatusBoard()[0].length; j++) {
        if (model.getStatusBoard()[i][j] == Status.EMPTY) {
          //c is card index
          for (int c = 1; c < model.getHand(player).size() + 1; c++) {
            numCardsFlipped = model.numFlipped(i, j, c);
            if (numCardsFlipped > maxCardsFlipped) {
              maxCardsFlipped = numCardsFlipped;
              bestMove = new int[]{c, i, j, numCardsFlipped * 4};
              numCardsFlipped = 0;
              //tiebreaker logic:
            } else if (numCardsFlipped == maxCardsFlipped && maxCardsFlipped != 0) {
              if (bestMove[1] > i) {
                bestMove = new int[]{c, i, j, numCardsFlipped * 4};
              } else if (bestMove[2] > j) {
                bestMove = new int[]{c, i, j, numCardsFlipped * 4};
              } else if (bestMove[0] > c) {
                bestMove = new int[]{c, i, j, numCardsFlipped * 4};
              }
            }
          }
        }
      }
    }
    if (bestMove[0] == -1) {
      return noValidMoves(model);
    }
    return bestMove;
  }
}
