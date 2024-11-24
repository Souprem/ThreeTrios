import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import cs3500.controller.ModelFeatureImpl;
import cs3500.controller.PersonPlayer;
import cs3500.controller.PlayerActions;
import cs3500.model.BoardConfigReader;
import cs3500.model.CardConfigReader;
import cs3500.model.PlayerColor;
import cs3500.model.ThreeTriosCard;
import cs3500.model.ThreeTriosModel;
import cs3500.model.TriosModel;
import cs3500.view.ThreeTriosGUIView;
import cs3500.view.TriosGUIView;

/**
 * Tests to ensure the connectivity between the view, model, and controller, in addition to
 * connectivity to the player and features classes.
 */
public class TestConnectivity {

  TriosModel<ThreeTriosCard> model;
  CardConfigReader cardReader;
  BoardConfigReader boardReader;

  @Before
  public void init() {
    model = new ThreeTriosModel();
  }

  @Test
  public void testConnectivity() {
    TriosModel model = new ThreeTriosModel();
    TriosGUIView view = new ThreeTriosGUIView(model);
    PlayerActions player1 = new PersonPlayer(model, PlayerColor.RED);
    ControllerMock mockController = new ControllerMock(model, view, player1, 7);
    model.startGame(7, new CardConfigReader("test" + File.separator + "configs" + File.separator
                    + "CardConfigSmall"),
            new BoardConfigReader("test" + File.separator + "configs" + File.separator
                    + "separatedBoardConfigTest"));
    model.addFeatures(new ModelFeatureImpl(model));
    model.playCard(1,2,1);
    Assert.assertEquals(mockController.getTestChecker(), 1);
  }
}
