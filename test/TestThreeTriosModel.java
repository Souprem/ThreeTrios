import cs3500.model.BoardConfigReader;
import cs3500.model.CardConfigReader;
import cs3500.model.PlayerColor;
import cs3500.model.Status;
import cs3500.model.ThreeTriosCard;
import cs3500.model.ThreeTriosModel;
import cs3500.model.TriosModel;
import cs3500.view.ThreeTriosTextView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * A test class for the ThreeTriosModel class.
 */
public class TestThreeTriosModel {
  TriosModel<ThreeTriosCard> model;
  CardConfigReader cardReader;
  BoardConfigReader boardReader;
  CardConfigReader cardConfigReaderSmall = new CardConfigReader("test" + File.separator
          + "configs" + File.separator + "CardConfigSmall");
  BoardConfigReader boardConfigReaderSeperated = new BoardConfigReader("test"
          + File.separator + "configs" + File.separator + "separatedBoardConfigTest");

  @Before
  public void init() {
    model = new ThreeTriosModel();
  }

  @Test
  public void testGetCurrentHandPlayCard() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);

    ArrayList<ThreeTriosCard> before = new ArrayList<>();
    before.add(new ThreeTriosCard("TEST1", "1", "2", "3", "4"));
    before.add(new ThreeTriosCard("TEST3", "A", "9", "8", "7"));
    before.add(new ThreeTriosCard("TEST5", "4", "A", "5", "4"));
    before.add(new ThreeTriosCard("TEST7", "6", "2", "A", "2"));

    ArrayList<ThreeTriosCard> after = new ArrayList<>();
    after.add(new ThreeTriosCard("TEST2", "1", "3", "5", "7"));
    after.add(new ThreeTriosCard("TEST4", "3", "2", "6", "3"));
    after.add(new ThreeTriosCard("TEST6", "5", "9", "6", "8"));
    after.add(new ThreeTriosCard("TEST8", "1", "4", "3", "8"));

    Assert.assertEquals(model.getCurrentHand(), before);
    model.playCard(1, 0 , 3);
    Assert.assertEquals(model.getCurrentHand(), after);
  }

  @Test
  public void testGetCurrentPlayerMut() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);

    Assert.assertEquals(model.getCurrentPlayer(), "RED");
    model.playCard(1, 0 , 3);
    Assert.assertEquals(model.getCurrentPlayer(), "Blue");
  }

  @Test
  public void testGetCurrentPlayer() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);

    Assert.assertEquals(model.getCurrentPlayer(), "RED");
    model.playCard(1, 0 , 3);
    Assert.assertEquals(model.getCurrentPlayer(), "Blue");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayCardRowOutOfBounds() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);

    model.playCard(1, 10000000 , 3);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testPlayCardColOutOfBounds() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);

    model.playCard(1, 0 , 100000);
  }



  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectFileNameCardConfig() throws IOException {
    List<ThreeTriosCard> cardList = new ArrayList<>();
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
    cardReader = new CardConfigReader("test" + File.separator + "configs" + File.separator
            + "CardConfigSmall");

    Assert.assertEquals(cardReader.convertFile(), cardList);
  }


  @Test
  public void testBoardConverterOne() throws IOException {
    Status[][] statusBoard = {
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE,
                Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE,
                Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.EMPTY, Status.HOLE, Status.HOLE,
                Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY, Status.HOLE,
                Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY,
                Status.EMPTY}
    };
    boardReader = new BoardConfigReader("test" + File.separator + "configs"
            + File.separator + "boardConfigTest");

    Assert.assertEquals(boardReader.convertFile(), statusBoard);
  }

  @Test
  public void testBoardConverterTwo() throws IOException {
    Status[][] statusBoard = {
            {Status.EMPTY, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };
    boardReader = new BoardConfigReader("test" + File.separator + "configs"
            + File.separator + "emptyBoardConfigTest");

    Assert.assertEquals(boardReader.convertFile(), statusBoard);
  }

  @Test
  public void testBoardConverterThree() throws IOException {
    Status[][] statusBoard = {
            {Status.EMPTY, Status.HOLE, Status.EMPTY, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY},
    };
    boardReader = new BoardConfigReader("test" + File.separator + "configs"
            + File.separator + "connectingBoardConfigTest");

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
    boardReader = new BoardConfigReader("test" + File.separator
            + "configs" + File.separator + "separatedBoardConfigTest");

    Assert.assertEquals(boardReader.convertFile(), statusBoard);
  }

  @Test
  public void testStartGameBoards() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);
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
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);
    Status[][] statusBoard = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    ThreeTriosCard c1 = new ThreeTriosCard("TEST1", "1", "2", "3",
            "4");
    ThreeTriosCard c2 = new ThreeTriosCard("TEST2", "1", "3", "5",
            "7");
    ThreeTriosCard c3 = new ThreeTriosCard("TEST3", "A", "9", "8",
            "7");
    ThreeTriosCard c4 = new ThreeTriosCard("TEST4", "3", "2", "6",
            "3");
    ThreeTriosCard c5 = new ThreeTriosCard("TEST5", "4", "A", "5",
            "4");
    ThreeTriosCard c6 = new ThreeTriosCard("TEST6", "5", "9", "6",
            "8");
    ThreeTriosCard c7 = new ThreeTriosCard("TEST7", "6", "2", "A",
            "2");
    ThreeTriosCard c8 = new ThreeTriosCard("TEST8", "1", "4", "3",
            "8");

    Assert.assertEquals(model.getHand(PlayerColor.RED),
            new ArrayList<ThreeTriosCard>(Arrays.asList(c1, c3, c5, c7)));
    Assert.assertEquals(model.getHand(PlayerColor.BLUE),
            new ArrayList<ThreeTriosCard>(Arrays.asList(c2, c4, c6, c8)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameEvenCardCells() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(8, cardConfigReaderSmall,
            boardConfigReaderSeperated);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNegativeCardCells() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(-1, cardConfigReaderSmall,
            boardConfigReaderSeperated);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameEmptyCardFile() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(-1, cardConfigReaderSmall,
            new BoardConfigReader("test" + File.separator + "configs"
                    + File.separator + "emptyFile"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNullCardFile() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(-1, cardConfigReaderSmall,
            null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayCardToTaken() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);

    model.playCard(1, 0, 3);
    model.playCard(1, 0, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayCardSmallIndex() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);

    model.playCard(0, 0, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayCardBigIndex() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);

    model.playCard(100, 0, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayCardGameOver() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);
    model.playCard(1, 0, 0);
    model.playCard(1, 0, 2);
    model.playCard(1, 1, 0);
    model.playCard(1, 1, 1);
    model.playCard(1, 1, 2);
    model.playCard(1, 1, 3);
    model.playCard(1, 2, 3);

    model.playCard(1, 0, 3);
  }

  @Test
  public void testBattleStep() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);
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
  public void testBattleStepTied() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, new CardConfigReader("test" + File.separator + "configs" + File.separator
                    + "CardConfigBig"),
            boardConfigReaderSeperated);

    Status[][] statusBoardAfter = {
            {Status.HOLE, Status.HOLE, Status.FULL, Status.FULL},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    ThreeTriosCard firstCard = new ThreeTriosCard("TEST1", "1", "2", "3",
            "4");
    ThreeTriosCard secondCard = new ThreeTriosCard("TEST2", "1", "3", "5",
            "3");
    model.playCard(1, 0, 2);
    Assert.assertEquals(model.getCardBoard()[0][2], firstCard);
    model.playCard(1, 0, 3);
    Assert.assertEquals(model.getCardBoard()[0][2], firstCard);
    Assert.assertEquals(model.getCardBoard()[0][3], secondCard);
  }


  @Test
  public void testBattleStepRecurseOnceEast() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);
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
    ThreeTriosCard flipped = new ThreeTriosCard("TEST1", "1", "2", "3",
            "4");
    flipped.setOwner(PlayerColor.BLUE);
    Assert.assertEquals(model.getCardBoard()[0][2], flipped);
  }

  @Test
  public void testBattleStepRecurseOnceWest() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);
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
    ThreeTriosCard flipped = new ThreeTriosCard("TEST1", "1", "2", "3",
            "4");
    flipped.setOwner(PlayerColor.BLUE);
    Assert.assertEquals(model.getCardBoard()[2][1], flipped);
  }

  @Test
  public void testBattleStepRecurseOnceSouth() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);
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
    ThreeTriosCard flipped = new ThreeTriosCard("TEST1", "1", "2", "3",
            "4");
    flipped.setOwner(PlayerColor.BLUE);
    Assert.assertEquals(model.getCardBoard()[1][0], flipped);
  }

  @Test
  public void testBattleStepRecurseOnceNorth() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);
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
    try {
      view.render();
    } catch (IOException ignored) {
    }
    model.playCard(1, 2, 0);
    try {
      view.render();
    } catch (IOException ignored) {
    }
    model.playCard(3, 1, 0);
    try {
      view.render();
    } catch (IOException ignored) {
    }
    Assert.assertEquals(model.getStatusBoard(), statusBoardAfterTwo);
    ThreeTriosCard flipped = new ThreeTriosCard("TEST1", "1", "2", "3",
            "4");
    flipped.setOwner(PlayerColor.BLUE);
    Assert.assertEquals(model.getCardBoard()[2][0], flipped);
  }

  @Test
  public void testGameNotOverEmptyBoard() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);
    Assert.assertFalse(model.isGameOver());
  }

  @Test
  public void testGameOverHoleBoard() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            new BoardConfigReader("test" + File.separator + "configs"
                    + File.separator + "holeBoardConfigTest"));
    Assert.assertTrue(model.isGameOver());
  }

  @Test
  public void testGameOverFilledBoard() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    Status[][] statusBoard = {
            {Status.EMPTY, Status.HOLE, Status.EMPTY, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY},
    };
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);
    model.playCard(1, 0, 0);
    model.playCard(1, 0, 2);
    model.playCard(1, 1, 0);
    model.playCard(1, 1, 1);
    model.playCard(1, 1, 2);
    model.playCard(1, 1, 3);
    model.playCard(1, 2, 3);

    Assert.assertTrue(model.isGameOver());
  }

  @Test(expected = IllegalStateException.class)
  public void testGameWonHoleBoard() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            new BoardConfigReader("test" + File.separator + "configs"
                    + File.separator + "holeBoardConfigTest"));
    model.getWinner();
  }

  @Test(expected = IllegalStateException.class)
  public void testGameWonTiedBoard() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);
    model.playCard(1, 0, 0);
    model.playCard(1, 0, 2);

    model.getWinner();
  }


  @Test
  public void testGameWon() {
    TriosModel<ThreeTriosCard> model = new ThreeTriosModel();
    Status[][] statusBoard = {
            {Status.EMPTY, Status.HOLE, Status.EMPTY, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY},
    };
    model.startGame(7, cardConfigReaderSmall,
            boardConfigReaderSeperated);
    model.playCard(1, 0, 0);
    model.playCard(1, 0, 2);
    model.playCard(1, 1, 0);
    model.playCard(1, 1, 1);
    model.playCard(1, 1, 2);
    model.playCard(1, 1, 3);
    model.playCard(1, 2, 3);

    Assert.assertEquals(model.getWinner(), PlayerColor.BLUE);
  }



}
