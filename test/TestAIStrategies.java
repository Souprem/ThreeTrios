import cs3500.model.*;
import cs3500.view.ThreeTriosTextView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestAIStrategies {
  TriosModel<ThreeTriosCard> model;

  @Before
  public void init() {
    model = new ThreeTriosModel();
  }

  @Test
  public void testMaxFlipAI() throws IOException {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator
                    + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator
                    + "separatedBoardConfigTest");
    Status[][] statusBoardBefore = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };
    model.playCard(1, 1, 0); //2 south
    model.playCard(1, 4, 3); //doesn't matter
    model.playCard(1, 2, 1); //7 west
    model.playCard(1, 4, 2); //doesn't matter
    model.playCard(1, 0, 3); //4 west
    // need a card with 3+ north and 8+ east

    Status[][] statusBoardAfter = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.FULL},
            {Status.FULL, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.FULL, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.FULL, Status.FULL}
    };

    Status[][] statusBoardEnd = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.FULL},
            {Status.FULL, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.FULL, Status.FULL, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.FULL, Status.FULL}
    };

    Assert.assertEquals(model.getCardBoard()[2][0], null);
    TriosAI maxFlip = new MaxFlipTriosAI();
    int[] tempMove = new int[3];
    tempMove = maxFlip.findMove(model, Player.BLUE);
    for (int i : tempMove){
      System.out.println(i);
    }
    model.playCard(tempMove[0],tempMove[1],tempMove[2]);
    Assert.assertEquals(model.getCardBoard()[2][0].getOwner(), Player.BLUE);
    Assert.assertEquals(model.getStatusBoard(), statusBoardEnd);
  }



}
