package cs3500.view;

import cs3500.model.Card;
import cs3500.model.Player;
import cs3500.model.Status;
import cs3500.model.ThreeTriosCard;
import cs3500.model.TriosModel;
import java.io.IOException;

/**
 * A class to represent the view for a ThreeTrios game.
 */
public class ThreeTriosTextView implements TriosView {
  private final TriosModel<ThreeTriosCard> model;
  private final Appendable ap;

  /**
   * A constructor for ThreeTriosTextView that takes in the model and an appendable.
   * @param model the model
   * @param ap the appendable to represent output
   */
  public ThreeTriosTextView(TriosModel<ThreeTriosCard> model, Appendable ap) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    this.ap = ap;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Player: ").append(model.getCurrentPlayer().toString().toUpperCase()).append("\n");

    for (int i = 0; i < model.getStatusBoard().length; i++) {
      for (int j = 0; j < model.getStatusBoard()[i].length; j++) {
        if (model.getStatusBoard()[i][j] == Status.EMPTY) {
          sb.append("_");
        } else if (model.getStatusBoard()[i][j] == Status.HOLE) {
          sb.append(" ");
        } else {
          Player owner = model.getCardBoard()[i][j].getOwner();
          if (owner == Player.RED) {
            sb.append("R");
          } else if (owner == Player.BLUE) {
            sb.append("B");
          } else {
            throw new IllegalStateException("owner cannot be null");
          }
        }

      }
      sb.append("\n");
    }

    sb.append("Hand: \n");
    for (Object card : model.getHand(Player.RED)) {
      sb.append(card.toString()).append("\n");
    }
    return sb.toString();
  }

  @Override
  public void render() throws IOException {
    ap.append(this.toString());
  }
}
