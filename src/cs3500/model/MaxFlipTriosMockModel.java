package cs3500.model;

import java.util.ArrayList;
import java.util.List;

public class MaxFlipTriosMockModel implements TriosModel<ThreeTriosCard> {


  @Override
  public Player getCurrentPlayer() {
    return null;
  }

  @Override
  public List getHand(Player player) {
    return null;
  }

  @Override
  public List getCurrentHand() {
    return null;
  }

  @Override
  public Card[][] getCardBoard() {
    return new Card[0][];
  }

  @Override
  public Status[][] getStatusBoard() {
    return new Status[0][];
  }

  @Override
  public Player getWinner() {
    return null;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int numRows() {
    return 0;
  }

  @Override
  public int numCols() {
    return 0;
  }

  @Override
  public Card cardAt(int row, int col) {
    return null;
  }

  @Override
  public Status statusAt(int row, int col) {
    return null;
  }

  @Override
  public Player ownerOf(int row, int col) {
    return null;
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

  @Override
  public void playCard(int cardIndex, int row, int col) {

  }

  @Override
  public void battleStep(List<ArrayList<Integer>> currentCards) {

  }

  @Override
  public void startGame(int numCardCells, String cardFile, String boardFile) {

  }
}
