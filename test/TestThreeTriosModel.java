import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestThreeTriosModel {
  TriosModel<ThreeTriosCard> model;

  @Before
  public void init() {
    model = new ThreeTriosModel();
  }

  @Test
  public void testCardConverter() throws IOException {
    List<ThreeTriosCard> cardList = new ArrayList<ThreeTriosCard>();
    cardList.add(new ThreeTriosCard("TEST1", "1", "2", "3", "4"));
    cardList.add(new ThreeTriosCard("TEST2", "1", "3", "5", "7"));
    cardList.add(new ThreeTriosCard("TEST3", "A", "9", "8", "7"));

    Assert.assertEquals(model.convertCardConfig("src" + File.separator + "CardConfigTest"), cardList);
  }


  @Test
  public void testBoardConverterOne() throws IOException {
    Status [][] testStatusBoard = new Status[5][7];
    Status[][] statusBoard = {
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.EMPTY, Status.HOLE, Status.HOLE, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY, Status.HOLE, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY}
    };

    Assert.assertEquals(model.convertBoardConfig("src" + File.separator + "boardConfigTest"), statusBoard);
  }

  @Test
  public void testBoardConverterTwo() throws IOException {
    Status [][] testStatusBoard = new Status[2][3];
    Status[][] statusBoard = {
            {Status.EMPTY, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    Assert.assertEquals(model.convertBoardConfig("src" + File.separator + "emptyBoardConfigTest"), statusBoard);
  }

  @Test
  public void testBoardConverterThree() throws IOException {
    Status [][] testStatusBoard = new Status[3][4];
    Status[][] statusBoard = {
            {Status.EMPTY, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.EMPTY},
    };

    Assert.assertEquals(model.convertBoardConfig("src" + File.separator + "connectingBoardConfigTest"), statusBoard);
  }

  @Test
  public void testBoardConverterFour() throws IOException {
    Status [][] testStatusBoard = new Status[3][4];
    Status[][] statusBoard = {
            {Status.HOLE, Status.HOLE, Status.EMPTY, Status.EMPTY},
            {Status.EMPTY, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.HOLE, Status.HOLE},
            {Status.HOLE, Status.HOLE, Status.HOLE, Status.HOLE},
            {Status.EMPTY, Status.EMPTY, Status.EMPTY, Status.EMPTY}
    };

    Assert.assertEquals(model.convertBoardConfig("src" + File.separator + "connectingBoardConfigTest"), statusBoard);
  }
}
