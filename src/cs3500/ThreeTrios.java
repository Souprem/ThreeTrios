package cs3500;

import cs3500.controller.PersonPlayer;
import cs3500.controller.PlayerActions;
import cs3500.controller.ThreeTriosController;
import cs3500.controller.TriosController;
import cs3500.model.PlayerColor;
import cs3500.model.ThreeTriosModel;
import cs3500.model.TriosModel;
import cs3500.view.ThreeTriosGUIView;
import cs3500.view.TriosGUIView;

import java.io.File;

/**
 * A class to hold the main method for ThreeTrios.
 */
public class ThreeTrios {

  /**
   * The main method used to run the application.
   * @param args input
   */
  public static void main(String[] args) {
    TriosModel model = new ThreeTriosModel();
    TriosGUIView viewPlayer1 = new ThreeTriosGUIView(model);
    TriosGUIView viewPlayer2 = new ThreeTriosGUIView(model);
    PlayerActions player1 = new PersonPlayer(model, PlayerColor.RED);
    PlayerActions player2 = new PersonPlayer(model, PlayerColor.BLUE);
    TriosController controller1 = new ThreeTriosController(model, viewPlayer1, player1, 7);
    TriosController controller2 = new ThreeTriosController(model, viewPlayer2, player2, 7);
    controller1.playGame("test" + File.separator + "configs" + File.separator
            + "CardConfigSmall", "test" + File.separator + "configs" + File.separator
            + "separatedBoardConfigTest", model);
    controller2.playGame("test" + File.separator + "configs" + File.separator
            + "CardConfigSmall", "test" + File.separator + "configs" + File.separator
            + "separatedBoardConfigTest", model);

    model.playCard(1, 0, 2);
    model.playCard(1, 2, 0);

  }
}
