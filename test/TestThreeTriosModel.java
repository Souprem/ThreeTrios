import Model.BoardConfigReader;
import Model.CardConfigReader;
import Model.Player;
import Model.Status;
import Model.ThreeTriosCard;
import Model.ThreeTriosModel;
import Model.TriosModel;
import View.ThreeTriosTextView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class TestThreeTriosModel {
  TriosModel<ThreeTriosCard> model;
  CardConfigReader cardReader;
  BoardConfigReader boardReader;

  @Before
  public void init() {
    model = new ThreeTriosModel();
    
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectFileNameCardConfig() throws IOException {
    List<ThreeTriosCard> cardList = new ArrayList<ThreeTriosCard>();
    cardReader = new CardConfigReader("badpath");
    cardList = cardReader.convertFile();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectFileNameBoardConfig() throws IOException {
    Status[][] testStatusBoard = new Status[5][7];
    boardReader = new BoardConfigReader("badpath");
    testStatusBoard =  boardReader.convertFile();
  }


  @Test
  public void testCardConverter() throws IOException {
    List<ThreeTriosCard> cardList = new ArrayList<ThreeTriosCard>();
    cardList.add(new ThreeTriosCard("TEST1", "1", "2", "3", "4"));
    cardList.add(new ThreeTriosCard("TEST2", "1", "3", "5", "7"));
    cardList.add(new ThreeTriosCard("TEST3", "A", "9", "8", "7"));
    cardReader = new CardConfigReader( 
            "test" + File.separator + "configs" + File.separator + "CardConfigTest");

    Assert.assertEquals(cardReader.convertFile(), cardList);
  }

  @Test
  public void testCardConverterSmall() throws IOException {
    List<ThreeTriosCard> cardList = new ArrayList<>();
    cardList.add(new ThreeTriosCard("TEST1", "1", "2", "3", "4"));
    cardList.add(new ThreeTriosCard("TEST2", "1", "3", "5", "7"));
    cardList.add(new ThreeTriosCard("TEST3", "A", "9", "8", "7"));
    cardList.add(new ThreeTriosCard("TEST4", "3", "2", "6", "3"));
    cardList.add(new ThreeTriosCard("TEST5", "4", "A", "5", "4"));
    cardList.add(new ThreeTriosCard("TEST6", "5", "9", "6", "8"));
    cardList.add(new ThreeTriosCard("TEST7", "6", "2", "A", "2"));
    cardList.add(new ThreeTriosCard("TEST8", "1", "4", "3", "8"));
    cardReader = new CardConfigReader("test" + File.separator + "configs" + File.separator + "CardConfigSmall");

    Assert.assertEquals(cardReader.convertFile(), cardList);
  }


  @Test
  public void testBoardConverterOne() throws IOException {
    Status[][] statusBoard = {
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.EMPTY, Status.HOLE, Status.HOLE, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY, Status.HOLE, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY}
    };
    boardReader = new BoardConfigReader("test" + File.separator + "configs" + File.separator +"boardConfigTest");

    Assert.assertEquals(boardReader.convertFile(), statusBoard);
  }

  @Test
  public void testBoardConverterTwo() throws IOException {
    Status[][] statusBoard = {
            {Status.EMPTY, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };
    boardReader = new BoardConfigReader("test" + File.separator + "configs" + File.separator + "emptyBoardConfigTest");

    Assert.assertEquals(boardReader.convertFile(), statusBoard);
  }

  @Test
  public void testBoardConverterThree() throws IOException {
    Status[][] statusBoard = {
            {Status.EMPTY, Status.HOLE, Status.EMPTY, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY},
    };
    boardReader = new BoardConfigReader("test" + File.separator + "configs" + File.separator + "connectingBoardConfigTest");

    Assert.assertEquals(boardReader.convertFile(), statusBoard);
  }

  @Test
  public void testBoardConverterFour() throws IOException {
    Status[][] statusBoard = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };
    boardReader = new BoardConfigReader("test" + File.separator + "configs" + File.separator + "separatedBoardConfigTest");

    Assert.assertEquals(boardReader.convertFile(), statusBoard);
  }

  @Test
  public void testStartGameBoards() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator + "separatedBoardConfigTest");
    Status[][] statusBoard = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    Assert.assertEquals(model.getStatusBoard(), statusBoard);
    Assert.assertEquals(model.getCardBoard(), new ThreeTriosCard[5][4]);
  }

  @Test
  public void testStartGameHands() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator + "separatedBoardConfigTest");
    Status[][] statusBoard = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    ThreeTriosCard c1 = new ThreeTriosCard("TEST1", "1", "2", "3", "4");
    ThreeTriosCard c2 = new ThreeTriosCard("TEST2", "1", "3", "5", "7");
    ThreeTriosCard c3 = new ThreeTriosCard("TEST3", "A", "9", "8", "7");
    ThreeTriosCard c4 = new ThreeTriosCard("TEST4", "3", "2", "6", "3");
    ThreeTriosCard c5 = new ThreeTriosCard("TEST5", "4", "A", "5", "4");
    ThreeTriosCard c6 = new ThreeTriosCard("TEST6", "5", "9", "6", "8");
    ThreeTriosCard c7 = new ThreeTriosCard("TEST7", "6", "2", "A", "2");
    ThreeTriosCard c8 = new ThreeTriosCard("TEST8", "1", "4", "3", "8");

    Assert.assertEquals(model.getHand(Player.RED), new ArrayList<ThreeTriosCard>(Arrays.asList(c1, c3, c5, c7)));
    Assert.assertEquals(model.getHand(Player.BLUE), new ArrayList<ThreeTriosCard>(Arrays.asList(c2, c4, c6, c8)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameEvenCardCells(){
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(8, "test" + File.separator + "configs" + File.separator + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator + "separatedBoardConfigTest");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNegativeCardCells(){
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(-1, "test" + File.separator + "configs" + File.separator + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator + "separatedBoardConfigTest");
  }

  @Test
  public void testBattleStep() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator + "separatedBoardConfigTest");
    Status[][] statusBoardBefore = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    Status[][] statusBoardAfter = {
            {Status.HOLE, Status.HOLE, Status.FULL, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    Assert.assertEquals(model.getStatusBoard(), statusBoardBefore);
    model.playCard(1, 0, 2);
    Assert.assertEquals(model.getStatusBoard(), statusBoardAfter);
  }

  @Test
  public void testBattleStepRecurseOnceEast() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator + "separatedBoardConfigTest");
    Status[][] statusBoardBefore = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    Status[][] statusBoardAfterOne = {
            {Status.HOLE, Status.HOLE, Status.FULL, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    Status[][] statusBoardAfterTwo = {
            {Status.HOLE, Status.HOLE, Status.FULL, Status.FULL},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    model.playCard(1, 0, 2);
    model.playCard(1, 0, 3);
    Assert.assertEquals(model.getStatusBoard(), statusBoardAfterTwo);
    ThreeTriosCard flipped = new ThreeTriosCard("TEST1", "1", "2", "3", "4");
    flipped.setOwner(Player.BLUE);
    Assert.assertEquals(model.getCardBoard()[0][2], flipped);
  }

  @Test
  public void testBattleStepRecurseOnceWest() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator + "separatedBoardConfigTest");
    Status[][] statusBoardBefore = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    Status[][] statusBoardAfterOne = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.FULL, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    Status[][] statusBoardAfterTwo = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.FULL, Status.FULL, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    model.playCard(1, 2, 1);
    model.playCard(1, 2, 0);
    Assert.assertEquals(model.getStatusBoard(), statusBoardAfterTwo);
    ThreeTriosCard flipped = new ThreeTriosCard("TEST1", "1", "2", "3", "4");
    flipped.setOwner(Player.BLUE);
    Assert.assertEquals(model.getCardBoard()[2][1], flipped);
  }

  @Test
  public void testBattleStepRecurseOnceSouth() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator + "separatedBoardConfigTest");
    Status[][] statusBoardBefore = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    Status[][] statusBoardAfterOne = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    Status[][] statusBoardAfterTwo = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.FULL, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.FULL, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    model.playCard(1, 1, 0);
    model.playCard(3, 2, 0);
    Assert.assertEquals(model.getStatusBoard(), statusBoardAfterTwo);
    ThreeTriosCard flipped = new ThreeTriosCard("TEST1", "1", "2", "3", "4");
    flipped.setOwner(Player.BLUE);
    Assert.assertEquals(model.getCardBoard()[1][0], flipped);
  }

  @Test
  public void testBattleStepRecurseOnceNorth() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator + "separatedBoardConfigTest");
    Status[][] statusBoardBefore = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    Status[][] statusBoardAfterOne = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.FULL, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    Status[][] statusBoardAfterTwo = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.FULL, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.FULL, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    ThreeTriosTextView view = new ThreeTriosTextView(model, System.out);
    try{
      view.render();
    } catch (IOException Ignored) {
    }
    model.playCard(1, 2, 0);
    try{
      view.render();
    } catch (IOException Ignored) {
    }
    model.playCard(3, 1, 0);
    try{
      view.render();
    } catch (IOException Ignored) {
    }
    Assert.assertEquals(model.getStatusBoard(), statusBoardAfterTwo);
    ThreeTriosCard flipped = new ThreeTriosCard("TEST1", "1", "2", "3", "4");
    flipped.setOwner(Player.BLUE);
    Assert.assertEquals(model.getCardBoard()[2][0], flipped);
  }

  @Test
  public void testRender() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator + "separatedBoardConfigTest");
    ThreeTriosTextView view = new ThreeTriosTextView(model, System.out);
    try{
      view.render();
    } catch (IOException Ignored) {
    }
  }

  @Test
  public void testGameNotOverEmptyBoard() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator + "emptyBoardConfigTest");
    Assert.assertFalse(model.isGameOver());
  }

  @Test
  public void testGameNotOver() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, "test" + File.separator + "configs" + File.separator + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator + "winningBoardConfigTest");
    Assert.assertTrue(model.isGameOver());
  }

  @Test
  public void testGameOver() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    Status[][] statusBoard = {
            {Status.EMPTY, Status.HOLE, Status.EMPTY, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY},
    };
    model.startGame(7, "test" + File.separator + "configs" + File.separator + "CardConfigSmall",
            "test" + File.separator + "configs" + File.separator + "connectingBoardConfigTest");
    model.playCard(1, 0, 0);
    model.playCard(1, 0, 2);
    model.playCard(1, 1, 0);
    model.playCard(1, 1, 1);
    model.playCard(1, 1, 2);
    model.playCard(1, 1, 3);
    model.playCard(1, 2, 3);

    Assert.assertTrue(model.isGameOver());
  }



}
