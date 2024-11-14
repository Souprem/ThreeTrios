package cs3500.model;

import cs3500.ThreeTrios;

public interface TriosAI{
  /*
  returns card, row, col, score
   */
  int[] findMove(TriosModel model, Player player);

  default int[] noValidMoves(TriosModel model){
    for (int i = 0; i < model.getStatusBoard().length; i++){
      for (int j = 0; j < model.getStatusBoard()[0].length; j++){
        if (model.getStatusBoard()[i][j].equals(Status.EMPTY)){
          return new int[]{1, i, j, 0};
        }
      }
    }
    throw new IllegalStateException("No empty spots on the board");
  }
}
