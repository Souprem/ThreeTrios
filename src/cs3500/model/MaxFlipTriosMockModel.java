package cs3500.model;

import java.util.List;
import java.util.ArrayList;

public class MaxFlipTriosMockModel implements TriosModel {
  private Status[][] statusBoard;
  private List<ThreeTriosCard> handRed;
  private List<ThreeTriosCard> handBlue;
  private Player currentTurn;
  private ThreeTriosCard[][] cardBoard;


  public MaxFlipTriosMockModel(Status[][] statusBoard, List<ThreeTriosCard> handRed, List<ThreeTriosCard> handBlue, Player currentTurn) {
    this.statusBoard = new Status[][] {
            {Status.EMPTY, Status.FULL},
            {Status.FULL, Status.HOLE},
            {Status.EMPTY, Status.FULL}
    };
//    this.handRed = new List<ThreeTriosCard> {};
    this.handBlue = handBlue;
    this.currentTurn = currentTurn;
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
  public int numRows() { return statusBoard.length; }

  @Override
  public int numCols() { return statusBoard[0].length; }

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
    return 0;
  }

  @Override
  public int calculateScore(Player player) {
    return 0;
  }


  // Add other methods as necessary for your tests.
}