package cs3500.Controller;
import cs3500.model.Player;
import cs3500.view.Features;

public class PreControllerFeatures implements Features {

  @Override
  public void cardInHandInfo(int indexInHand, Player owner) {
    System.out.println(" Owner: " + owner + "Index in the hand: " + indexInHand);
  }

  @Override
  public void cellInfo(int row, int col) {
    System.out.println("[" + row + ", " + col + "]");
  }

}
