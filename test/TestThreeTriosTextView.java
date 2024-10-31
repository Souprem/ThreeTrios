import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import cs3500.model.ThreeTriosCard;
import cs3500.model.ThreeTriosModel;
import cs3500.model.TriosModel;
import cs3500.view.ThreeTriosTextView;

/**
 * A class to test the ThreeTriosTextView.
 */
public class TestThreeTriosTextView {
  TriosModel<ThreeTriosCard> model;

  @Before
  public void init() {
    model = new ThreeTriosModel();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testViewExceptionsNullModel() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    ThreeTriosTextView view = new ThreeTriosTextView(null, System.out);
  }


  @Test
  public void testRender() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator
                    + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator
                    + "separatedBoardConfigTest");
    ThreeTriosTextView view = new ThreeTriosTextView(model, System.out);
    try {
      view.render();
    } catch (IOException ignored) {
    }
    Assert.assertEquals(view.toString(), "Player: RED\n"
            + "  __\n"
            + "_   \n"
            + "__  \n"
            + "    \n"
            + "____\n"
            + "Hand: \n"
            + "TEST1 1 2 3 4\n"
            + "TEST3 10 9 8 7\n"
            + "TEST5 4 10 5 4\n"
            + "TEST7 6 2 10 2\n");
  }

  @Test
  public void testRenderAfterPlaying() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator
                    + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator
                    + "separatedBoardConfigTest");
    ThreeTriosTextView view = new ThreeTriosTextView(model, System.out);
    try {
      view.render();
    } catch (IOException ignored) {
    }
    model.playCard(1, 0, 3);
    Assert.assertEquals(view.toString(), "Player: BLUE\n"
            + "  _R\n"
            + "_   \n"
            + "__  \n"
            + "    \n"
            + "____\n"
            + "Hand: \n"
            + "TEST3 10 9 8 7\n"
            + "TEST5 4 10 5 4\n"
            + "TEST7 6 2 10 2\n");
  }

  @Test
  public void testRenderAfterGameEnd() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator
                    + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator
                    + "connectingBoardConfigTest");
    ThreeTriosTextView view = new ThreeTriosTextView(model, System.out);
    model.playCard(1, 0, 0);
    model.playCard(1, 0, 2);
    model.playCard(1, 1, 0);
    model.playCard(1, 1, 1);
    model.playCard(1, 1, 2);
    model.playCard(1, 1, 3);
    model.playCard(1, 2, 3);
    try {
      view.render();
    } catch (IOException ignored) {
    }
    Assert.assertEquals(view.toString(), "Player: BLUE\n"
            + "R B \n"
            + "RBBB\n"
            + "   R\n"
            + "Hand: \n");
  }
}
