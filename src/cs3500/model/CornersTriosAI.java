package cs3500.model;

/**
 * Class which represents the strategy in which the AI goes for the corners.
 */
public class CornersTriosAI implements TriosAI {

  @Override
  public int[] findMove(TriosModel model, PlayerColor playerColor) {
    int[] bestMove = new int[3];
    int currentSum = 0;
    int maxSum = 0;
    int bestCard = -1;

    if (model.getStatusBoard()[0][0].equals(Status.EMPTY)) {
      for (int i = 0; i < model.getHand(playerColor).size(); i++) {
        ThreeTriosCard currentCard = (ThreeTriosCard) model.getHand(playerColor).get(i);
        currentSum = currentCard.getSouth().numericValue + currentCard.getEast().numericValue;

        if (currentSum > maxSum) {
          maxSum = currentSum;
          bestCard = i + 1;
        }
      }
      return new int[]{bestCard, 0, 0, currentSum};

    } else if (model.getStatusBoard()[0][model.numCols() - 1].equals(Status.EMPTY)) {
      for (int i = 0; i < model.getHand(playerColor).size(); i++) {
        ThreeTriosCard currentCard = (ThreeTriosCard) model.getHand(playerColor).get(i);
        currentSum = currentCard.getSouth().numericValue + currentCard.getWest().numericValue;

        if (currentSum > maxSum) {
          maxSum = currentSum;
          bestCard = i + 1;
        }
      }
      return new int[]{bestCard, 0, model.numCols() - 1, currentSum};

    } else if (model.getStatusBoard()[model.numRows() - 1][0].equals(Status.EMPTY)) {
      for (int i = 0; i < model.getHand(playerColor).size(); i++) {
        ThreeTriosCard currentCard = (ThreeTriosCard) model.getHand(playerColor).get(i);
        currentSum = currentCard.getNorth().numericValue + currentCard.getEast().numericValue;

        if (currentSum > maxSum) {
          maxSum = currentSum;
          bestCard = i + 1;
        }
      }
      return new int[]{bestCard, model.numRows() - 1, 0, currentSum};

    } else if (model.getStatusBoard()[model.numRows() - 1][model.numCols() - 1].equals(
            Status.EMPTY)) {
      for (int i = 0; i < model.getHand(playerColor).size(); i++) {
        ThreeTriosCard currentCard = (ThreeTriosCard) model.getHand(playerColor).get(i);
        currentSum = currentCard.getNorth().numericValue + currentCard.getWest().numericValue;

        if (currentSum > maxSum) {
          maxSum = currentSum;
          bestCard = i + 1;
        }
      }
      return new int[]{bestCard, model.numRows() - 1, model.numCols() - 1, currentSum};
    }

    return noValidMoves(model);
  }

  @Override
  public void placeCardOnGrid(int row, int col) {

  }

  @Override
  public void selectHandCard(int handIndex) {

  }
}