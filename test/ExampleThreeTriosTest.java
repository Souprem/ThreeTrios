import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import cs3500.model.BoardConfigReader;
import cs3500.model.CardConfigReader;
import cs3500.model.Player;
import cs3500.model.ThreeTriosCard;
import cs3500.model.ThreeTriosModel;
import cs3500.model.TriosModel;
import cs3500.view.ThreeTriosTextView;

/**
 * An example class to showcase the functionality of the ThreeTrios game.
 */
public class ExampleThreeTriosTest {
  TriosModel<ThreeTriosCard> model;
  CardConfigReader cardReader;
  BoardConfigReader boardReader;

  @Before
  public void init() {
    model = new ThreeTriosModel();
  }

  @Test
  public void testEasyGameplayExample() throws IOException {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    ThreeTriosTextView view = new ThreeTriosTextView(model, System.out);
    model.startGame(7, "test" + File.separator + "configs" + File.separator + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator + "separatedBoardConfigTest");
    view.render();
    model.playCard(1, 4, 0);
    view.render();
    model.playCard(1, 4, 1);
    view.render();
    Assert.assertEquals(model.getCurrentPlayer(), Player.RED.toString());
  }

}
