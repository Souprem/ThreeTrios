package cs3500.view;
import cs3500.model.Player;

public interface Features {
  void cardInHandInfo(int indexInHand, Player owner);
  void cellInfo(int row, int col);
}
