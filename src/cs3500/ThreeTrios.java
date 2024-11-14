package cs3500;

import cs3500.model.ThreeTriosModel;
import cs3500.view.ThreeTriosGUIView;

import java.io.File;

public class ThreeTrios {
  public static void main(String[] args) {
    ThreeTriosModel model = new ThreeTriosModel();

    model.startGame(7, "test" + File.separator + "configs" + File.separator
                    + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator
                    + "separatedBoardConfigTest");
    model.playCard(1, 0, 2);
    model.playCard(1, 2, 0);
    ThreeTriosGUIView view = new ThreeTriosGUIView(model);
    view.setVisible(true);

  }
}
