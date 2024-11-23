package cs3500.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import cs3500.controller.ModelFeatures;

/**
 * A mock to test the corners AI.
 */
public class CornerTriosAIMockModel implements TriosModel {
  private Status[][] statusBoard;
  private List<ThreeTriosCard> handRed;
  private List<ThreeTriosCard> handBlue;
  private PlayerColor currentTurn;


  /**
   * Constructor for the CornerTriosAIMockModel class that takes in no input.
   */
  public CornerTriosAIMockModel() {
    this.handRed = new ArrayList<ThreeTriosCard>();
    this.handRed.add(new ThreeTriosCard("TEST", "2", "1", "9", "5"));
    this.handRed.add(new ThreeTriosCard("TEST1", "1", "3", "1", "4"));
    this.handRed.add(new ThreeTriosCard("TEST2", "1", "1", "1", "1"));
    this.handRed.add(new ThreeTriosCard("TEST3", "6", "9", "7", "2"));
    this.statusBoard = new Status[][] {
            {Status.FULL, Status.FULL},
            {Status.FULL, Status.FULL},
            {Status.EMPTY, Status.EMPTY}
    };
    ThreeTriosCard[][] cardBoard = new ThreeTriosCard[][]{
            {new ThreeTriosCard("TL", "1", "1", "1", "1"),
                new ThreeTriosCard("TR", "1", "1", "1", "1")},
            {new ThreeTriosCard("ML", "1", "1", "1", "1"),
                new ThreeTriosCard("MR", "1", "1", "1", "1")},
            {null, null}
    };
  }

  @Override
  public Status[][] getStatusBoard() {
    return this.statusBoard;
  }

  @Override
  public List<ThreeTriosCard> getHand(PlayerColor playerColor) {
    if (playerColor == PlayerColor.RED) {
      return this.handRed;
    } else {
      return this.handBlue;
    }
  }

  @Override
  public List getCurrentHand() {
    return List.of();
  }

  @Override
  public Card[][] getCardBoard() {
    return new Card[0][];
  }

  @Override
  public PlayerColor getCurrentPlayer() {
    return this.currentTurn;
  }

  @Override
  public void playCard(int cardIndex, int row, int col) {
    // Simulate playing a card by updating the status board
    if (statusBoard[row][col] != Status.EMPTY) {
      throw new IllegalArgumentException("Cannot play card in non-empty spot.");
    }
    statusBoard[row][col] = Status.FULL; // Simulate placing a card
  }

  @Override
  public void battleStep(List currentCards) {
    //no implementation for this mock
  }

  // Other methods can be no-ops or throw UnsupportedOperationException if not needed for tests.

  @Override
  public int numRows() {
    return statusBoard.length;
  }

  @Override
  public int numCols() {
    return statusBoard[0].length;
  }

  @Override
  public boolean isGameOver() {
    throw new UnsupportedOperationException("Not implemented in mock.");
  }

  @Override
  public PlayerColor getWinner() {
    throw new UnsupportedOperationException("Not implemented in mock.");
  }

  @Override
  public Card cardAt(int row, int col) {
    throw new UnsupportedOperationException("Not implemented in mock.");
  }

  @Override
  public void startGame(int numCardCells,
                        CardConfigReader cardReader, BoardConfigReader boardReader) {
    //no implementation for this mock
  }

  @Override
  public void addFeatures(ModelFeatures modelFeatures) {
    //no implementation for this mock
  }

  @Override
  public Status statusAt(int row, int col) {
    return statusBoard[row][col];
  }

  @Override
  public PlayerColor ownerOf(int row, int col) {
    throw new UnsupportedOperationException("Not implemented in mock.");
  }

  @Override
  public boolean validMove(int row, int col) {
    return false;
  }

  @Override
  public int numFlipped(int row, int col, int cardIndex) {
    try {
      FileWriter writer = new FileWriter("strategy-transcript.txt");
      writer.write("row: " + row + "col: " + col + "card: " + cardIndex + "\n");
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    if (row == 0 && col == 1 && cardIndex == 1) {
      return 10;
    } else {
      return -1;
    }
  }

  @Override
  public int calculateScore(PlayerColor playerColor) {
    return 0;
  }


  // Add other methods as necessary for your tests.
}