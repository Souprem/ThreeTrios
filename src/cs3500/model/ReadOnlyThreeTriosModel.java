package cs3500.model;

import java.util.ArrayList;
import java.util.List;

public class ReadOnlyThreeTriosModel implements ReadOnlyTriosModel{

  /*
  for boards: ROW, COLUMN
  board.length = number of rows
  board[0].length = number of columns
   */
  protected ThreeTriosCard[][] cardBoard;
  protected Status[][] statusBoard;
  protected List<ThreeTriosCard> handRed;
  protected List<ThreeTriosCard> handBlue;
  protected Player currentTurn = Player.RED;

  // Deep copy constructor
  public ReadOnlyThreeTriosModel(ThreeTriosModel model) {
    // Deep copy of cardBoard (2D array)
    this.cardBoard = new ThreeTriosCard[model.getCardBoard().length][];
    for (int i = 0; i < model.getCardBoard().length; i++) {
      this.cardBoard[i] = new ThreeTriosCard[model.getCardBoard()[i].length];
      for (int j = 0; j < model.getCardBoard()[i].length; j++) {
        // Assuming ThreeTriosCard has its own deep copy constructor or clone method
        this.cardBoard[i][j] = model.getCardBoard()[i][j] != null
                ? new ThreeTriosCard(model.getCardBoard()[i][j])
                : null;
      }
    }

    // Deep copy of statusBoard (2D array)
    this.statusBoard = new Status[model.getStatusBoard().length][];
    for (int i = 0; i < model.getStatusBoard().length; i++) {
      this.statusBoard[i] = model.getStatusBoard()[i].clone();  // Shallow clone is fine for enums
    }

    // Deep copy of handRed (List)
    this.handRed = new ArrayList<>();
    for (ThreeTriosCard card : model.getHand(Player.RED)) {
      this.handRed.add(new ThreeTriosCard(card));  // Assuming ThreeTriosCard has a deep copy constructor
    }

    // Deep copy of handBlue (List)
    this.handBlue = new ArrayList<>();
    for (ThreeTriosCard card : model.getHand(Player.BLUE)) {
      this.handBlue.add(new ThreeTriosCard(card));  // Assuming ThreeTriosCard has a deep copy constructor
    }

    // Copying primitive or immutable fields directly
    this.currentTurn = model.currentTurn;
  }


  public ReadOnlyThreeTriosModel(){

  }

  @Override
  public String getCurrentPlayer() {
    if (currentTurn == Player.RED) {
      return "RED";
    } else {
      return "Blue";
    }
  }

  @Override
  public List<ThreeTriosCard> getHand(Player player) {
    if (player == Player.RED) {
      return this.handRed;
    } else if (player == Player.BLUE) {
      return this.handBlue;
    } else {
      throw new IllegalArgumentException("input player must be player 1 or 2");
    }
  }

  @Override
  public List getCurrentHand() {
    if (currentTurn == Player.RED) {
      return this.handRed;
    } else {
      return this.handBlue;
    }
  }

  @Override
  public ThreeTriosCard[][] getCardBoard() {
    return this.cardBoard;
  }

  @Override
  public Status[][] getStatusBoard() {
    return this.statusBoard;
  }

  @Override
  public Player getWinner() {
    int redCount = 0;
    int blueCount = 0;
    for (int i = 0; i < this.cardBoard.length; i++) {
      for (int j = 0; j < this.cardBoard[0].length; j++) {
        if (cardBoard[i][j] != null && cardBoard[i][j].getOwner().equals(Player.RED)) {
          redCount++;
        } else if (cardBoard[i][j] != null && cardBoard[i][j].getOwner().equals(Player.BLUE)) {
          blueCount++;
        } else {
          continue;
        }
      }
    }
    if (redCount > blueCount) {
      return Player.RED;
    } else if (blueCount > redCount) {
      return Player.BLUE;
    } else {
      throw new IllegalStateException("currently a draw");
    }
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.statusBoard.length; i++) {
      for (int j = 0; j < this.statusBoard[0].length; j++) {
        if (statusBoard[i][j] == Status.EMPTY) {
          return false;
        }
      }
    }
    return true;
  }
}
