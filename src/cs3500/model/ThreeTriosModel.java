package cs3500.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.controller.ModelFeatures;
import cs3500.controller.TriosController;

/**
 * This class represents a model for the ThreeTrios game.
 * What is implementation specific about this model?
 *
 */
public class ThreeTriosModel implements TriosModel<ThreeTriosCard> {

  /*
   for boards: ROW, COLUMN
   board.length = number of rows
   board[0].length = number of columns
    */
  protected ThreeTriosCard[][] cardBoard;
  protected Status[][] statusBoard;
  protected List<ThreeTriosCard> handRed;
  protected List<ThreeTriosCard> handBlue;
  protected PlayerColor currentTurn = PlayerColor.RED;
  protected List<TriosController> triosControllers = new ArrayList<>();
  protected ModelFeatures modelFeat;

  // No argument constructor
  public ThreeTriosModel() {
    //no arguments
  }

  /**
   * A deep copy constructor for ThreeTriosModel that takes in a ThreeTriosModel
   * and uses the fields from the given model to initialize its own fields without
   * creating pointers to avoid mutation.
   * @param model the given model
   */
  public ThreeTriosModel(ThreeTriosModel model) {
    // Deep copy of cardBoard (2D array)
    this.cardBoard = new ThreeTriosCard[model.getCardBoard().length][];
    for (int i = 0; i < model.getCardBoard().length; i++) {
      this.cardBoard[i] = new ThreeTriosCard[model.getCardBoard()[i].length];
      for (int j = 0; j < model.getCardBoard()[i].length; j++) {
        // ThreeTriosCard has its own deep copy constructor or clone method
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
    for (ThreeTriosCard card : model.getHand(PlayerColor.RED)) {
      this.handRed.add(new ThreeTriosCard(card));
    }

    // Deep copy of handBlue (List)
    this.handBlue = new ArrayList<>();
    for (ThreeTriosCard card : model.getHand(PlayerColor.BLUE)) {
      this.handBlue.add(new ThreeTriosCard(card));
    }

    // Copying primitive or immutable fields directly
    this.currentTurn = model.currentTurn;
  }

  @Override
  public PlayerColor getCurrentPlayer() {
    if (currentTurn == PlayerColor.RED) {
      return PlayerColor.RED;
    } else {
      return PlayerColor.BLUE;
    }
  }

  @Override
  public List<ThreeTriosCard> getHand(PlayerColor playerColor) {
    if (playerColor == PlayerColor.RED) {
      return this.handRed;
    } else if (playerColor == PlayerColor.BLUE) {
      return this.handBlue;
    } else {
      throw new IllegalArgumentException("input player must be player 1 or 2");
    }
  }

  @Override
  public List<ThreeTriosCard> getCurrentHand() {
    if (currentTurn == PlayerColor.RED) {
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

  public void addFeatures(ModelFeatures modelFeat) {
    this.modelFeat = modelFeat;
  }

  @Override
  public PlayerColor getWinner() {
    int redCount = 0;
    int blueCount = 0;
    for (int i = 0; i < this.cardBoard.length; i++) {
      for (int j = 0; j < this.cardBoard[0].length; j++) {
        if (cardBoard[i][j] != null && cardBoard[i][j].getOwner().equals(PlayerColor.RED)) {
          redCount++;
        } else if (cardBoard[i][j] != null && cardBoard[i][j].getOwner().equals(PlayerColor.BLUE)) {
          blueCount++;
        } else {
          continue;
        }
      }
    }
    if (redCount > blueCount) {
      return PlayerColor.RED;
    } else if (blueCount > redCount) {
      return PlayerColor.BLUE;
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

  @Override
  public int numRows() {
    return statusBoard.length;
  }

  @Override
  public int numCols() {
    return statusBoard[0].length;
  }

  @Override
  public Card cardAt(int row, int col) {
    if (!(statusBoard[row][col].equals(Status.FULL))) {
      throw new IllegalArgumentException("cannot ask where non existent card is");
    }
    return cardBoard[row][col];
  }

  @Override
  public Status statusAt(int row, int col) {
    return statusBoard[row][col];
  }

  @Override
  public PlayerColor ownerOf(int row, int col) {
    if (!(statusBoard[row][col].equals(Status.FULL))) {
      throw new IllegalArgumentException("cannot ask where non existent card is");
    }
    return cardBoard[row][col].getOwner();
  }

  @Override
  public boolean validMove(int row, int col) {
    return (statusBoard[row][col].equals(Status.EMPTY));
  }

  @Override
  public int numFlipped(int row, int col, int cardIndex) {
    int numCardsFlipped = 0;
    TriosModel newModel = new ThreeTriosModel(this);
    newModel.playCard(cardIndex, row, col);
    //checks new model to provided model to see how many cards were flipped
    for (int a = 0; a < this.numRows(); a++) {
      for (int b = 0; b < this.numCols(); b++) {
        if (newModel.getStatusBoard()[a][b] == Status.FULL && (row != a || col != b)) {
          if (this.cardBoard[a][b].getOwner() != newModel.getCardBoard()[a][b].getOwner()) {
            numCardsFlipped++;
          }
        }
      }
    }
    return numCardsFlipped;
  }

  @Override
  public int calculateScore(PlayerColor playerColor) {
    int score = 0;
    for (int i = 0; i < this.numRows(); i++) {
      for (int j = 0; j < this.numCols(); j++) {
        if (statusBoard[i][j].equals(Status.FULL)) {
          if (cardBoard[i][j].getOwner().equals(playerColor)) {
            score++;
          }
        }
      }
    }
    score = score + getHand(playerColor).size();
    return score;
  }

  @Override
  public void playCard(int cardIndex, int row, int col) {
    List<ThreeTriosCard> tempHand = getCurrentHand();
    if (row > this.numRows() - 1) {
      throw new IllegalArgumentException("cannot input row out of bounds");
    }
    if (col > this.numCols() - 1) {
      throw new IllegalArgumentException("cannot input col out of bounds");
    }
    if (statusBoard[row][col] != Status.EMPTY) {
      throw new IllegalArgumentException("cannot play card to taken spot or hole");
    }
    if (cardIndex < 1) {
      throw new IllegalArgumentException("card index must be positive");
    }
    if (cardIndex > tempHand.size()) {
      throw new IllegalArgumentException("must pick a valid card in hand");
    }
    if (this.isGameOver()) {
      throw new IllegalStateException("game is over");
    }
    //cardIndex coming in is 1-based, the row and col are 0 based
    ThreeTriosCard tempCard = tempHand.remove(cardIndex - 1);
    tempCard.setOwner(currentTurn);
    this.cardBoard[row][col] = tempCard;
    this.statusBoard[row][col] = Status.FULL;
    if (currentTurn == PlayerColor.RED) {
      this.handRed = tempHand;
      currentTurn = PlayerColor.BLUE;
    } else {
      this.handBlue = tempHand;
      currentTurn = PlayerColor.RED;
    }

    List<ArrayList<Integer>> playedCardIndex = new ArrayList<ArrayList<Integer>>();
    playedCardIndex.add(new ArrayList<Integer>(Arrays.asList(row, col)));
    battleStep(playedCardIndex);
    modelFeat.playerTurnChanged();
  }


  //Flips cards adjacent to the inputted list of current cards where cards adjacent
  // to the current card have attack values lower than the current card

  @Override
  public void battleStep(List<ArrayList<Integer>> currentCards) {
    List<ArrayList<Integer>> recursiveList = new ArrayList<ArrayList<Integer>>();
    for (List<Integer> card : currentCards) {
      int row = card.get(0);
      int col = card.get(1);
      ThreeTriosCard tempCard = this.cardBoard[row][col];
      PlayerColor cardOwner = tempCard.owner;
      PlayerColor otherOwner = cardOwner.getOther();
      int boardLengthRows = statusBoard.length;
      int boardLengthCols = statusBoard[0].length;

      //looking east
      if (col < boardLengthCols - 1 && this.statusBoard[row][col + 1]
              == Status.FULL && this.cardBoard[row][col + 1].owner != otherOwner) {
        if (tempCard.getNorth().numericValue
                > this.cardBoard[row][col + 1].getSouth().numericValue) {
          this.cardBoard[row][col + 1].setOwner(cardOwner);
          //adding flipped cards to recursive list so they can also do battle
          recursiveList.add(new ArrayList<Integer>(Arrays.asList(row, col + 1)));
        }
      }
      //looking west
      if (col > 0 && this.statusBoard[row][col - 1]
              == Status.FULL && this.cardBoard[row][col - 1].owner
              != otherOwner) {
        if (tempCard.getWest().numericValue
                > this.cardBoard[row][col - 1].getEast().numericValue) {
          this.cardBoard[row][col - 1].setOwner(cardOwner);
          recursiveList.add(new ArrayList<Integer>(Arrays.asList(row, col - 1)));
        }
      }
      //looking north
      if (row > 0 && this.statusBoard[row - 1][col]
              == Status.FULL && this.cardBoard[row - 1][col].owner != otherOwner) {
        if (tempCard.getNorth().numericValue
                > this.cardBoard[row - 1][col].getSouth().numericValue) {
          this.cardBoard[row - 1][col].setOwner(cardOwner);
          recursiveList.add(new ArrayList<Integer>(Arrays.asList(row - 1, col)));
        }
      }

    if (row < boardLengthRows - 1 && this.statusBoard[row + 1][col]
            == Status.FULL && this.cardBoard[row + 1][col].owner != otherOwner) {
        if (tempCard.getSouth().numericValue
                > this.cardBoard[row + 1][col].getNorth().numericValue) {
          this.cardBoard[row + 1][col].setOwner(cardOwner);
          recursiveList.add(new ArrayList<Integer>(Arrays.asList(row + 1, col)));
        }
      }
    }

    if (!recursiveList.isEmpty()) {
      battleStep(recursiveList);
    }
  }

  @Override
  public void startGame(int numCardCells, CardConfigReader cardReader, BoardConfigReader boardReader) {
    if (numCardCells % 2 == 0) {
      throw new IllegalArgumentException("the number of card cells must be odd");
    }
    if (numCardCells < 0) {
      throw new IllegalArgumentException("there cannot be a negative number of card cells");
    }
    List<ThreeTriosCard> deck = cardReader.convertFile();
    this.statusBoard = boardReader.convertFile();
    this.cardBoard = new ThreeTriosCard[statusBoard.length][statusBoard[0].length];
    this.handRed = new ArrayList<ThreeTriosCard>();
    this.handBlue = new ArrayList<ThreeTriosCard>();

    int handSize = (numCardCells + 1) / 2;

    //shuffle the deck before startgame so the hands get random cards from the deck
    for (int i = 0; i < handSize; i++) {
      handRed.add(deck.remove(0));
      handRed.get(handRed.size() - 1).setOwner(PlayerColor.RED);
      handBlue.add(deck.remove(0));
      handBlue.get(handRed.size() - 1).setOwner(PlayerColor.BLUE);
    }
  }

  @Override
  public void addObserver(TriosController triosController) {
    triosControllers.add(triosController);

  }

  @Override
  public void removeObserver(TriosController triosController) {
    triosControllers.remove(triosController);
  }

  @Override
  public void notifyObservers() {
    for (TriosController triosController : triosControllers) {
      triosController.update();
    }
  }


}
