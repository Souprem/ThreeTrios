package View;

import Model.Card;
import Model.Player;
import Model.Status;
import Model.ThreeTriosCard;
import Model.TriosModel;
import java.io.IOException;

public class ThreeTriosTextView {
  private TriosModel<ThreeTriosCard> model;
  Appendable ap;


  public ThreeTriosTextView(TriosModel<ThreeTriosCard> model, Appendable ap) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    this.ap = ap;
  }


  public String textView() {
    StringBuilder sb = new StringBuilder();

    sb.append("Model.Player: ").append(model.getCurrentPlayer().toUpperCase()).append("\n");

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
    for (Card card : model.getHand(Player.RED)) {
      sb.append(card.toString()).append("\n");
    }
    return sb.toString();
  }

  public void render() throws IOException {
    ap.append(this.textView());
  }
}
