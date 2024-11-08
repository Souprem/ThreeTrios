package cs3500.model;

import java.util.ArrayList;

public class MaxFlipTriosAI implements TriosAI<ThreeTriosCard>{

  @Override
  public int[] findMove(TriosModel model, Player player) {
    int numCardsFlipped = 0;
    //default is 1 to account for card placed
    int maxCardsFlipped = 0;
    //bestMove is list of ints [cardIndex, row, column] DOUBLE CHECK THIS!!!!!!!!!
    int[] bestMove = new int[3];
    bestMove[0] = -1;

    Status[][] baseStatusBoard = model.getStatusBoard();
    Card[][] baseCardBoard = model.getCardBoard();
    //i is row index
    for (int i = 0; i < baseStatusBoard.length; i++){
      //j is column index
      for (int j = 0; j< baseStatusBoard[0].length; j++){
        if (baseStatusBoard[i][j] == Status.EMPTY){
          //c is card index
          for (int c = 1; c < model.getHand(player).size()+1; c++){
            //create new model to try out moves on
            TriosModel newModel = new ThreeTriosModel((ThreeTriosModel)model);
            newModel.playCard(c, i, j);
            //checks new model to provided model to see how many cards were flipped
            for (int a = 0; a < baseStatusBoard.length; a++) {
              for (int b = 0; b < baseStatusBoard[0].length; b++) {
                if (newModel.getStatusBoard()[a][b] == Status.FULL && (i != a || j != b)){
                  if (baseCardBoard[a][b].getOwner() != newModel.getCardBoard()[a][b].getOwner()){
                    numCardsFlipped ++;
                  }
                }
              }
            }
            if (numCardsFlipped > maxCardsFlipped){
              maxCardsFlipped = numCardsFlipped;
              bestMove = new int[]{c, i, j};
              numCardsFlipped = 0;
              //tiebreaker logic:
            } else if (numCardsFlipped == maxCardsFlipped && maxCardsFlipped != 0){
              if (bestMove[1] > i){
                bestMove = new int[]{c, i, j};
              } else if (bestMove[2] > j) {
                bestMove = new int[]{c, i, j};
              } else if (bestMove[0] > c){
                bestMove = new int[]{c, i, j};
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
