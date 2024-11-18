package cs3500.model;

public class HumanPlayer implements Player{

  private TriosModel model;

  public HumanPlayer(TriosModel<ThreeTriosCard> model){
    this.model = model;
  }

  @Override
  public int[] findMove(TriosModel model, PlayerColor playerColor) {
    return new int[0];
  }
}
