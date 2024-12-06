package cs3500;

import cs3500.controller.AIPlayer;
import cs3500.controller.PersonPlayer;
import cs3500.controller.PlayerActions;
import cs3500.controller.ThreeTriosController;
import cs3500.controller.TriosController;
import cs3500.model.BoardConfigReader;
import cs3500.model.CardConfigReader;
import cs3500.model.CornersTriosAI;
import cs3500.model.IBoardConfigReader;
import cs3500.model.ICardConfigReader;
import cs3500.model.MaxFlipTriosAI;
import cs3500.model.PlayerColor;
import cs3500.model.ThreeTriosModel;
import cs3500.model.TriosModel;
import cs3500.provider.model.ModelAdapter;
import cs3500.provider.model.ReadOnlyModel;
import cs3500.provider.view.ViewAdapter;
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
    ICardConfigReader cardConfigReader = new CardConfigReader("test" + File.separator
            + "configs" + File.separator
            + "CardConfigSmall");
    IBoardConfigReader boardConfigReader = new BoardConfigReader("test" + File.separator
            + "configs" + File.separator
            + "separatedBoardConfigTest");
    ReadOnlyModel modelAdapter = new ModelAdapter(model, 7,
            cardConfigReader, boardConfigReader);
    TriosGUIView viewPlayer1 = new ThreeTriosGUIView(model);
    TriosGUIView viewPlayer2 = new ViewAdapter(modelAdapter);
    if (args.length != 2) {
      throw new IllegalArgumentException("Wrong number of arguments");
    }
    for (int i = 0; i < args.length - 1; i++) {
      args[i] = args[i].toLowerCase();
    }
    PlayerActions player1;
    PlayerActions player2;
    switch (args[0]) {
      case "human":
        player1 = new PersonPlayer(model,PlayerColor.RED);
        break;
      case "strategy1":
        player1 = new AIPlayer(model,PlayerColor.RED, new MaxFlipTriosAI());
        break;
      case "strategy2":
        player1 = new AIPlayer(model,PlayerColor.RED, new CornersTriosAI());
        break;
      default:
        throw new IllegalArgumentException("player type must be specified as " +
                "human, strategy1, strategy2");
    }
    switch (args[1]) {
      case "human":
        player2 = new PersonPlayer(model, PlayerColor.BLUE);
        break;
      case "strategy1":
        player2 = new AIPlayer(model, PlayerColor.BLUE, new MaxFlipTriosAI());
        break;
      case "strategy2":
        player2 = new AIPlayer(model, PlayerColor.BLUE, new CornersTriosAI());
        break;
      default:
        throw new IllegalArgumentException("player type must be specified as" +
                " human, strategy1, strategy2");
    }

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
