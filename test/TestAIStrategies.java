import cs3500.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * A class to test ai strategies.
 */
public class TestAIStrategies {
  TriosModel<ThreeTriosCard> model;

  @Before
  public void init() {
    model = new ThreeTriosModel();
  }

  @Test
  public void testMaxFlipMock() throws IOException {
    TriosModel<ThreeTriosCard> model = new MaxFlipTriosMockModel();
    TriosAI maxFlip = new MaxFlipTriosAI();
    int[] tempMove = new int[4];
    tempMove = maxFlip.findMove(model, PlayerColor.RED);
    int[] tempMove1 = new int[4];

    tempMove1[0] = 1;
    tempMove1[1] = 0;
    tempMove1[2] = 1;
    tempMove1[3] = 40;

    Assert.assertEquals(tempMove1[0], tempMove[0]);
    Assert.assertEquals(tempMove1[1], tempMove[1]);
    Assert.assertEquals(tempMove1[2], tempMove[2]);
    Assert.assertEquals(tempMove1[3], tempMove[3]);
  }

  @Test
  public void testMaxFlipMock2() throws IOException {
    TriosModel<ThreeTriosCard> model = new MaxFlipTriosMockModel();
    TriosAI maxFlip = new MaxFlipTriosAI();
    int[] tempMove = new int[4];
    tempMove = maxFlip.findMove(model, PlayerColor.RED);
    int[] tempMove1 = new int[4];

    tempMove1[0] = 0;
    tempMove1[1] = 0;
    tempMove1[2] = 0;
    tempMove1[3] = 0;

    Assert.assertEquals(tempMove1[0], tempMove[0]);
    Assert.assertEquals(tempMove1[1], tempMove[1]);
    Assert.assertEquals(tempMove1[2], tempMove[2]);
    Assert.assertEquals(tempMove1[3], tempMove[3]);
  }

  @Test
  public void testornerMock() throws IOException {
    TriosModel<ThreeTriosCard> model = new CornerTriosAIMockModel();
    TriosAI corners = new CornersTriosAI();
    int[] tempMove = new int[4];
    tempMove = corners.findMove(model, PlayerColor.RED);
    int[] tempMove1 = new int[4];

    tempMove1[0] = 4;
    tempMove1[1] = 2;
    tempMove1[2] = 0;
    tempMove1[3] = 13;

    Assert.assertEquals(tempMove1[0], tempMove[0]);
    Assert.assertEquals(tempMove1[1], tempMove[1]);
    Assert.assertEquals(tempMove1[2], tempMove[2]);
    Assert.assertEquals(tempMove1[3], tempMove[3]);
  }

  @Test
  public void testMaxFlipEasyNoChoice() throws IOException {
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
    model.playCard(3, 0, 1);
    model.playCard(3, 1, 0);
    model.playCard(2, 2, 1);

    Status[][] statusBoardAfter = {
            {Status.EMPTY, Status.FULL},//x 3r
            {Status.FULL, Status.HOLE}, //3b x
            {Status.EMPTY, Status.FULL} //x 2r
    };

    TriosAI maxFlip = new MaxFlipTriosAI();
    int[] tempMove = new int[3];
    tempMove = maxFlip.findMove(model, PlayerColor.BLUE);
    int[] tempMove1 = new int[3];
    //Expecting the AI to go to the upper leftmost corner
    //as there is no way for it to flip the opponents cards
    tempMove1[0] = 1;
    tempMove1[1] = 0;
    tempMove1[2] = 0;

    Assert.assertEquals(tempMove[0], tempMove1[0]);
    Assert.assertEquals(tempMove[1], tempMove1[1]);
    Assert.assertEquals(tempMove[2], tempMove1[2]);
    Assert.assertEquals(model.getStatusBoard(), statusBoardAfter);
}

  @Test
  public void testMaxFlipEasyOneChoice() throws IOException {
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
    model.playCard(3, 2, 0);
    model.playCard(3, 2, 1);
    model.playCard(1, 0, 1);

    Status[][] statusBoardAfter = {
            {Status.EMPTY, Status.FULL},//c 1r
            {Status.EMPTY, Status.HOLE},//c x
            {Status.FULL, Status.FULL}  //3r 3b
    };

    TriosAI maxFlip = new MaxFlipTriosAI();
    int[] tempMove = new int[3];
    tempMove = maxFlip.findMove(model, PlayerColor.BLUE);
    int[] tempMove1 = new int[3];
    //Expect the AI to play at 0,0 to flip the only card it can
    tempMove1[0] = 2;
    tempMove1[1] = 0;
    tempMove1[2] = 0;

    Assert.assertEquals(tempMove[0], tempMove1[0]);
    Assert.assertEquals(tempMove[1], tempMove1[1]);
    Assert.assertEquals(tempMove[2], tempMove1[2]);
    Assert.assertEquals(model.getStatusBoard(), statusBoardAfter);
  }

  @Test
  public void testMaxFlipEasyTwoChoicesOneWinner() throws IOException {
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
    model.playCard(3, 0, 1);
    model.playCard(3, 1, 0);
    model.playCard(1, 2, 1);

    Status[][] statusBoardAfter = {
            {Status.EMPTY, Status.FULL},//c 3r
            {Status.FULL, Status.HOLE}, //3b x
            {Status.EMPTY, Status.FULL} //c 1r
    };

    TriosAI maxFlip = new MaxFlipTriosAI();
    int[] tempMove = new int[3];
    tempMove = maxFlip.findMove(model, PlayerColor.BLUE);
    int[] tempMove1 = new int[3];
    tempMove1[0] = 2;
    tempMove1[1] = 2;
    tempMove1[2] = 0;

    Assert.assertEquals(tempMove[0], tempMove1[0]);
    Assert.assertEquals(tempMove[1], tempMove1[1]);
    Assert.assertEquals(tempMove[2], tempMove1[2]);
    Assert.assertEquals(model.getStatusBoard(), statusBoardAfter);
  }

  @Test
  public void testMaxFlipEasyTwoChoicesOneBetter() throws IOException {
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
    model.playCard(2, 1, 0);
    model.playCard(1, 0, 1);
    model.playCard(1, 2, 1);

    Status[][] statusBoardAfter = {
            {Status.EMPTY, Status.FULL},//c 1b
            {Status.FULL, Status.HOLE}, //2r x
            {Status.EMPTY, Status.FULL} //c 1r
    };

    TriosAI maxFlip = new MaxFlipTriosAI();
    int[] tempMove = new int[3];
    tempMove = maxFlip.findMove(model, PlayerColor.BLUE);
    int[] tempMove1 = new int[3];
    //expect it to be in the position to flip two cards
    tempMove1[0] = 2;
    tempMove1[1] = 2;
    tempMove1[2] = 0;

    Assert.assertEquals(tempMove[0], tempMove1[0]);
    Assert.assertEquals(tempMove[1], tempMove1[1]);
    Assert.assertEquals(tempMove[2], tempMove1[2]);
    Assert.assertEquals(model.getStatusBoard(), statusBoardAfter);
  }

  @Test
  public void testMaxFlipEasyTwoChoicesTie() throws IOException {
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
    model.playCard(2, 0, 1);
    model.playCard(2, 2, 1);
    model.playCard(1, 2, 0);

    Status[][] statusBoardAfter = {
            {Status.EMPTY, Status.FULL},//c 2r
            {Status.EMPTY, Status.HOLE}, //c x
            {Status.FULL, Status.FULL} //1r 2b
    };

    TriosAI maxFlip = new MaxFlipTriosAI();
    int[] tempMove = new int[3];
    tempMove = maxFlip.findMove(model, PlayerColor.BLUE);
    int[] tempMove1 = new int[3];
    //Expect the AI to play 0,0 as there is a tie between two possible positions
    //which flip a single card
    tempMove1[0] = 2;
    tempMove1[1] = 0;
    tempMove1[2] = 0;

    Assert.assertEquals(tempMove[0], tempMove1[0]);
    Assert.assertEquals(tempMove[1], tempMove1[1]);
    Assert.assertEquals(tempMove[2], tempMove1[2]);
    Assert.assertEquals(model.getStatusBoard(), statusBoardAfter);
  }

  @Test
  public void testMaxFlipCheckConfigurationEvenWorks() throws IOException {
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
    model.playCard(1, 0, 1);
    model.playCard(1, 1, 0);
    model.playCard(1, 2, 1);

    Status[][] statusBoardAfter = {
            {Status.EMPTY, Status.FULL},//x 1r
            {Status.FULL, Status.HOLE}, //1b x
            {Status.EMPTY, Status.FULL} //x 2r
    };
    model.playCard(1, 0, 0);

    Status[][] statusBoardEnd = {
            {Status.FULL, Status.FULL},//2b 1r(flipped)
            {Status.FULL, Status.HOLE}, //2b x
            {Status.EMPTY, Status.FULL} //x 2r
    };

    Assert.assertEquals(model.getStatusBoard(), statusBoardEnd);
    Assert.assertEquals(model.getCardBoard()[0][1].getOwner(), PlayerColor.BLUE);
  }

  @Test
  public void testCornersTopLeftOneCard() throws IOException {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(5, "test" + File.separator + "configs" + File.separator
                    + "CardConfigTiny",
            "test" + File.separator + "configs" + File.separator
                    + "boardConfigTiny");
    TriosAI corners = new CornersTriosAI();
    int[] tempMove = new int[3];
    tempMove = corners.findMove(model, PlayerColor.RED);
    int[] tempMove1 = new int[3];
    //Expecting the AI to go to the upper leftmost corner
    // with the card TEST5 as it has the highest combined attack values
    // for south and east
    tempMove1[0] = 3;
    tempMove1[1] = 0;
    tempMove1[2] = 0;

    Assert.assertEquals(tempMove1[0], tempMove[0]);
    Assert.assertEquals(tempMove1[1], tempMove[1]);
    Assert.assertEquals(tempMove1[2], tempMove[2]);
  }

  @Test
  public void testCornersTopRightOneCard() throws IOException {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(5, "test" + File.separator + "configs" + File.separator
                    + "CardConfigTiny",
            "test" + File.separator + "configs" + File.separator
                    + "boardConfigTiny");
    model.playCard(1, 0, 0);
    TriosAI corners = new CornersTriosAI();
    int[] tempMove = new int[3];
    tempMove = corners.findMove(model, PlayerColor.BLUE);
    int[] tempMove1 = new int[3];
    //Expecting the AI to go to the upper leftmost corner
    // with the card TEST5 as it has the highest combined attack values
    // for south and east
    tempMove1[0] = 3;
    tempMove1[1] = 0;
    tempMove1[2] = 1;

    Assert.assertEquals(tempMove1[0], tempMove[0]);
    Assert.assertEquals(tempMove1[1], tempMove[1]);
    Assert.assertEquals(tempMove1[2], tempMove[2]);
  }

  @Test
  public void testCornersBottomLeftOneCard() throws IOException {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(5, "test" + File.separator + "configs" + File.separator
                    + "CardConfigTiny",
            "test" + File.separator + "configs" + File.separator
                    + "boardConfigTiny");
    model.playCard(1, 0, 0);
    model.playCard(1, 0, 1);
    TriosAI corners = new CornersTriosAI();
    int[] tempMove = new int[3];
    tempMove = corners.findMove(model, PlayerColor.BLUE);
    int[] tempMove1 = new int[3];
    //Expecting the AI to go to the upper leftmost corner
    // with the card TEST5 as it has the highest combined attack values
    // for south and east
    tempMove1[0] = 2;
    tempMove1[1] = 2;
    tempMove1[2] = 0;

    Assert.assertEquals(tempMove1[0], tempMove[0]);
    Assert.assertEquals(tempMove1[1], tempMove[1]);
    Assert.assertEquals(tempMove1[2], tempMove[2]);
  }

  @Test
  public void testCornersBottomRightOneCard() throws IOException {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(5, "test" + File.separator + "configs" + File.separator
                    + "CardConfigTiny",
            "test" + File.separator + "configs" + File.separator
                    + "boardConfigTiny");
    model.playCard(1, 0, 0);
    model.playCard(1, 0, 1);
    model.playCard(1, 2, 0);
    TriosAI corners = new CornersTriosAI();
    int[] tempMove = new int[3];
    tempMove = corners.findMove(model, PlayerColor.BLUE);
    int[] tempMove1 = new int[3];
    //Expecting the AI to go to the upper leftmost corner
    // with the card TEST5 as it has the highest combined attack values
    // for south and east
    tempMove1[0] = 2;
    tempMove1[1] = 2;
    tempMove1[2] = 1;

    Assert.assertEquals(tempMove1[0], tempMove[0]);
    Assert.assertEquals(tempMove1[1], tempMove[1]);
    Assert.assertEquals(tempMove1[2], tempMove[2]);
  }

  @Test
  public void testCornersNoCornersAvailOneCard() throws IOException {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(5, "test" + File.separator + "configs" + File.separator
                    + "CardConfigTiny",
            "test" + File.separator + "configs" + File.separator
                    + "boardConfigTiny");
    model.playCard(1, 0, 0);
    model.playCard(1, 0, 1);
    model.playCard(1, 2, 0);
    model.playCard(1, 2, 1);
    TriosAI corners = new CornersTriosAI();
    int[] tempMove = new int[3];
    tempMove = corners.findMove(model, PlayerColor.BLUE);
    int[] tempMove1 = new int[3];
    //Expecting the AI to go to the upper leftmost corner
    // with the card TEST5 as it has the highest combined attack values
    // for south and east
    tempMove1[0] = 1;
    tempMove1[1] = 1;
    tempMove1[2] = 0;

    Assert.assertEquals(tempMove1[0], tempMove[0]);
    Assert.assertEquals(tempMove1[1], tempMove[1]);
    Assert.assertEquals(tempMove1[2], tempMove[2]);
  }

  @Test
  public void testCornersNoCornersAvailMoreCards() throws IOException {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(5, "test" + File.separator + "configs" + File.separator
                    + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator
                    + "boardConfigTiny");
    model.playCard(1, 0, 0);
    model.playCard(1, 0, 1);
    model.playCard(1, 2, 0);
    model.playCard(1, 2, 1);
    TriosAI corners = new CornersTriosAI();
    int[] tempMove = new int[3];
    tempMove = corners.findMove(model, PlayerColor.BLUE);
    int[] tempMove1 = new int[3];
    //Expecting the AI to go to the upper leftmost corner
    // with the card TEST5 as it has the highest combined attack values
    // for south and east
    tempMove1[0] = 1;
    tempMove1[1] = 1;
    tempMove1[2] = 0;

    Assert.assertEquals(tempMove1[0], tempMove[0]);
    Assert.assertEquals(tempMove1[1], tempMove[1]);
    Assert.assertEquals(tempMove1[2], tempMove[2]);
  }
}
