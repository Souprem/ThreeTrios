package cs3500.model;

public interface TriosAI extends Player{
  /*
  returns card, row, col, score
   */


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
