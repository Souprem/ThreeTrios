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

//  @Test
//  public void testMaxFlipAI() throws IOException {
//    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
//    model.startGame(7, "test" + File.separator + "configs" + File.separator
//                    + "CardConfigSmall",
//            "test" + File.separator + "configs" + File.separator
//                    + "separatedBoardConfigTest");
//    Status[][] statusBoardBefore = {
//            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
//            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
//            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
//            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
//            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
//    };
//    model.playCard(1, 1, 0); //2 south
//    model.playCard(1, 4, 3); //doesn't matter
//    model.playCard(1, 2, 1); //7 west
//    model.playCard(1, 4, 2); //doesn't matter
//    model.playCard(1, 0, 3); //4 west
//    // need a card with 3+ north and 8+ east
//
//    Status[][] statusBoardAfter = {
//            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.FULL},
//            {Status.FULL, Status.HOLE, Status.HOLE, Status.HOLE},
//            {Status.EMPTY, Status.FULL, Status.HOLE, Status.HOLE},
//            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
//            {Status.EMPTY, Status.EMPTY, Status.FULL, Status.FULL}
//    };
//
//    Status[][] statusBoardEnd = {
//            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.FULL},
//            {Status.FULL, Status.HOLE, Status.HOLE, Status.HOLE},
//            {Status.FULL, Status.FULL, Status.HOLE, Status.HOLE},
//            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
//            {Status.EMPTY, Status.EMPTY, Status.FULL, Status.FULL}
//    };
//
//    Assert.assertEquals(model.getCardBoard()[2][0], null);
//    TriosAI maxFlip = new MaxFlipTriosAI();
//    int[] tempMove = new int[3];
//    tempMove = maxFlip.findMove(model, Player.BLUE);
//    for (int i : tempMove){
//      System.out.println(i);
//    }
//    model.playCard(tempMove[0],tempMove[1],tempMove[2]);
//    Assert.assertEquals(model.getCardBoard()[2][0].getOwner(), Player.BLUE);
//    Assert.assertEquals(model.getStatusBoard(), statusBoardEnd);
//  }
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
  tempMove = maxFlip.findMove(model, Player.BLUE);
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
    tempMove = maxFlip.findMove(model, Player.BLUE);
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
    tempMove = maxFlip.findMove(model, Player.BLUE);
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
    tempMove = maxFlip.findMove(model, Player.BLUE);
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
    tempMove = maxFlip.findMove(model, Player.BLUE);
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
    Assert.assertEquals(model.getCardBoard()[0][1].getOwner(), Player.BLUE);
  }






}
