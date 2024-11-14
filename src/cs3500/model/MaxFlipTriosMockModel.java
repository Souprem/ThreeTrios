package cs3500.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Mock to test the Max flip AI.
 */
public class MaxFlipTriosMockModel implements TriosModel {
  private Status[][] statusBoard;
  private List<ThreeTriosCard> handRed;
  private List<ThreeTriosCard> handBlue;
  private Player currentTurn;
  private ThreeTriosCard[][] cardBoard;

  /**
   * A mock to test the max flip AI.
   */
  public MaxFlipTriosMockModel() {
    this.handRed = new ArrayList<ThreeTriosCard>();
    this.handRed.add(new ThreeTriosCard("TEST", "9", "1", "9", "9"));
    this.handRed.add(new ThreeTriosCard("TEST1", "9", "9",  "9", "9"));
    this.handRed.add(new ThreeTriosCard("TEST2", "1", "1", "1", "1"));
    this.statusBoard = new Status[][] {
            {Status.FULL, Status.EMPTY},
            {Status.FULL, Status.FULL},
            {Status.FULL, Status.EMPTY}
    };
    this.cardBoard = new ThreeTriosCard[][] {
            {new ThreeTriosCard("TL", "1", "1", "1", "1"), null},
            {new ThreeTriosCard("ML", "1", "1", "1 ", "1"),
                new ThreeTriosCard("MR", "1", "1", "1", "1")},
            {new ThreeTriosCard("BL", "9", "9",  "9  ", "9"), null}
    };
  }

  @Override
  public Status[][] getStatusBoard() {
    return this.statusBoard;
  }

  @Override
  public List<ThreeTriosCard> getHand(Player player) {
    if (player == Player.RED) {
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
  public Player getCurrentPlayer() {
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
  public Player getWinner() {
    throw new UnsupportedOperationException("Not implemented in mock.");
  }

  @Override
  public Card cardAt(int row, int col) {
    throw new UnsupportedOperationException("Not implemented in mock.");
  }


  @Override
  public void startGame(int numCardCells, String cardFile, String boardFile) {

  }

  @Override
  public Status statusAt(int row, int col) {
    return statusBoard[row][col];
  }

  @Override
  public Player ownerOf(int row, int col) {
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
  public int calculateScore(Player player) {
    return 0;
  }

}