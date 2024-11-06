package cs3500.model;

import java.util.ArrayList;

public class MaxFlipTriosAI implements TriosAI<ThreeTriosCard>{

  @Override
  public int[] findMove(ThreeTriosModel model, Player player) {
    int numCardsFlipped = 0;
    int maxCardsFlipped = 0;
    //bestMove is list of ints [column, row, cardIndex] DOUBLE CHECK THIS!!!!!!!!!
    int[] bestMove = new int[3];

    Status[][] baseStatusBoard = model.getStatusBoard();
    ThreeTriosCard[][] baseCardBoard = model.getCardBoard();
    for (int i = 0; i < baseStatusBoard.length; i++){
      for (int j = 0; j< baseStatusBoard[0].length; j++){
        if (baseStatusBoard[i][j] == Status.EMPTY){
          for (int c = 0; c < model.getHand(player).size(); c++){
            ThreeTriosModel newmodel = model;
            newmodel.playCard(c, i, j);
            for (int a = 0; i < baseStatusBoard.length; i++) {
              for (int b = 0; j < baseStatusBoard[0].length; j++) {
                if (!(newmodel.getStatusBoard().equals(Status.EMPTY)) || baseCardBoard[a][b] != newmodel.getCardBoard()[a][b]){
                  numCardsFlipped ++;
                }
              }
            }
            if (numCardsFlipped > maxCardsFlipped){
              maxCardsFlipped = numCardsFlipped;
              bestMove = new int[]{i, j, c};
            }
          }
        }
      }
    }
    return bestMove;
  }
}
