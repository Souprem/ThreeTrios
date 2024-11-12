import cs3500.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class TestModelObservables {
  TriosModel<ThreeTriosCard> model;
  CardConfigReader cardReader;
  BoardConfigReader boardReader;

  @Before
  public void init() {
    model = new ThreeTriosModel();
  }

  @Test
  public void testRowAndColAmount() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator
                    + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator
                    + "separatedBoardConfigTest");

    Assert.assertEquals(model.numRows(), 5);
    Assert.assertEquals(model.numCols(), 4);
  }

  @Test
  public void testCardAt() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator
                    + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator
                    + "separatedBoardConfigTest");

    model.playCard(1, 2, 1);
    Card c = new ThreeTriosCard("TEST1", "1", "2", "3", "4");
    Assert.assertEquals(model.cardAt(2, 1), c);
  }

  @Test
  public void testStatusAt() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator
                    + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator
                    + "separatedBoardConfigTest");

    Assert.assertEquals(model.statusAt(1, 1), Status.HOLE);
    Assert.assertEquals(model.statusAt(2, 1), Status.EMPTY);
    model.playCard(1, 2, 1);
    Assert.assertEquals(model.statusAt(2, 1), Status.FULL);
  }

  @Test
  public void testOwnerOf() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator
                    + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator
                    + "separatedBoardConfigTest");

    model.playCard(1, 2, 1);
    Assert.assertEquals(model.ownerOf(2, 1), Player.RED);
  }

  @Test
  public void testValidMove() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator
                    + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator
                    + "separatedBoardConfigTest");

    Assert.assertEquals(model.validMove(1, 1), false);
    Assert.assertEquals(model.validMove(2, 1), true);
    model.playCard(1, 2, 1);
    Assert.assertEquals(model.validMove(2, 1), false);
  }

  @Test
  public void testCalculateScoreBasic() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator
                    + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator
                    + "separatedBoardConfigTest");

    Assert.assertEquals(model.calculateScore(Player.RED), 4);
    model.playCard(1, 2, 1);
    Assert.assertEquals(model.calculateScore(Player.RED), 4);
  }

  @Test
  public void testCalculateScoreAfterPlaying() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(5, "test" + File.separator + "configs" + File.separator
                    + "CardConfigTiny",
            "test" + File.separator + "configs" + File.separator
                    + "boardConfigTiny");
    Status[][] statusBoardBefore = {
            {Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE},
            {Status.EMPTY, Status.EMPTY}
    };
    Assert.assertEquals(model.calculateScore(Player.BLUE), 3);
    model.playCard(3, 2, 0);
    model.playCard(3, 2, 1);
    model.playCard(1, 0, 1);
    Assert.assertEquals(model.calculateScore(Player.BLUE), 3);

    Status[][] statusBoardAfter = {
            {Status.EMPTY, Status.FULL},//c 1r
            {Status.EMPTY, Status.HOLE},//c x
            {Status.FULL, Status.FULL}  //3r 3b
    };

    //Expect the AI to play 2 at 0,0 to flip the only card it can
    model.playCard(2, 0, 0);
    Assert.assertEquals(model.calculateScore(Player.BLUE), 4);
  }








}
