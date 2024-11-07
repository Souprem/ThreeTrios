package cs3500.model;

import java.util.ArrayList;

public class MaxFlipTriosAI implements TriosAI<ThreeTriosCard>{

  @Override
  public int[] findMove(ThreeTriosModel model, Player player) {
    int numCardsFlipped = 0;
    //default is 1 to account for card placed
    int maxCardsFlipped = 1;
    //bestMove is list of ints [column, row, cardIndex] DOUBLE CHECK THIS!!!!!!!!!
    int[] bestMove = new int[3];

    Status[][] baseStatusBoard = model.getStatusBoard();
    ThreeTriosCard[][] baseCardBoard = model.getCardBoard();
    //i is column index
    for (int i = 0; i < baseStatusBoard.length; i++){
      //j is row index
      for (int j = 0; j< baseStatusBoard[0].length; j++){
        if (baseStatusBoard[i][j] == Status.EMPTY){
          //c is card index
          for (int c = 0; c < model.getHand(player).size(); c++){
            //create new model to try out moves on
            ThreeTriosModel newmodel = model;
            newmodel.playCard(c, i, j);
            //checks new model to provided model to see how many cards were flipped
            for (int a = 0; i < baseStatusBoard.length; i++) {
              for (int b = 0; j < baseStatusBoard[0].length; j++) {
                if (!(newmodel.getStatusBoard().equals(Status.EMPTY)) || baseCardBoard[a][b].getOwner() != newmodel.getCardBoard()[a][b].getOwner()){
                  numCardsFlipped ++;
                }
              }
            }
            if (numCardsFlipped > maxCardsFlipped){
              maxCardsFlipped = numCardsFlipped;
              bestMove = new int[]{i, j, c};
              numCardsFlipped = 0;
              //tiebreaker logic:
            } else if (numCardsFlipped == maxCardsFlipped || maxCardsFlipped != 0){
              if (bestMove[0] > i){
                bestMove = new int[]{i, j, c};
              } else if (bestMove[1] > j) {
                bestMove = new int[]{i, j, c};
              } else if (bestMove[2] > c){
                bestMove = new int[]{i, j, c};
              }
            }
          }
        }
      }
    }
    return bestMove;
  }
}
