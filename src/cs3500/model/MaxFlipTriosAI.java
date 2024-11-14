package cs3500.model;

public class MaxFlipTriosAI implements TriosAI{

  public int[] findMove(TriosModel model, Player player){
    int numCardsFlipped = 0;
    //default is 1 to account for card placed
    int maxCardsFlipped = 0;
    //bestMove is list of ints [cardIndex, row, column] DOUBLE CHECK THIS!!!!!!!!!
    int[] bestMove = new int[4];
    bestMove[0] = -1;

    for (int i = 0; i < model.getStatusBoard().length; i++){
      //j is column index
      for (int j = 0; j< model.getStatusBoard()[0].length; j++) {
        if (model.getStatusBoard()[i][j] == Status.EMPTY) {
          //c is card index
          for (int c = 1; c < model.getHand(player).size() + 1; c++) {
            numCardsFlipped = model.numFlipped(i,j,c);
            if (numCardsFlipped > maxCardsFlipped){
              maxCardsFlipped = numCardsFlipped;
              bestMove = new int[]{c, i, j, numCardsFlipped*4};
              numCardsFlipped = 0;
              //tiebreaker logic:
            } else if (numCardsFlipped == maxCardsFlipped && maxCardsFlipped != 0){
              if (bestMove[1] > i){
                bestMove = new int[]{c, i, j, numCardsFlipped*4};
              } else if (bestMove[2] > j) {
                bestMove = new int[]{c, i, j, numCardsFlipped*4};
              } else if (bestMove[0] > c){
                bestMove = new int[]{c, i, j, numCardsFlipped*4};
              }
            }
          }
          }
        }
      }
    if (bestMove[0] == -1){
      return noValidMoves(model);
    }
    return bestMove;
  }
}
