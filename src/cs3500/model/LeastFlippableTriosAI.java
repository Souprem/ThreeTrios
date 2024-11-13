package cs3500.model;

public class LeastFlippableTriosAI implements TriosAI {

  @Override
  public int[] findMove(TriosModel model, Player player) {
    int[] bestMove = new int[3];
    int minFlips = Integer.MAX_VALUE;

    for (int i = 0; i < model.getStatusBoard().length; i++) {
      for (int j = 0; j < model.getStatusBoard()[0].length; j++) {
        if (model.getStatusBoard()[i][j] == Status.EMPTY) {
          for (int c = 0; c < model.getHand(player).size(); c++) {
            // Simulate placing card c at position (i, j)
            TriosModel newModel = new ThreeTriosModel((ThreeTriosModel) model);
            newModel.playCard(c, i, j);

            // Calculate how many of opponent's cards can flip this card
            int flips = calculateOpponentFlips(newModel, i, j);

            if (flips < minFlips) {
              minFlips = flips;
              bestMove = new int[]{c, i, j};
            }
          }
        }
      }
    }

    return bestMove;
  }

  private int calculateOpponentFlips(TriosModel model, int row, int col) {
//    int numFlips = 0;
//    if (model.getStatusBoard()[row+1][col].equals(Status.EMPTY)){
//      for (int i = 1; i < model.getHand(model.getCurrentPlayer().getOther()).size(); i++){
//        Card handCard = model.getHand(model.getCurrentPlayer().getOther()).get(i);
//        if (model.getHand(model.getCurrentPlayer().getOther()).get(i) > model.getCardBoard()[row][col].getSouth())
//
//      }
//    }
    return 1;
  }
}