package cs3500.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a model for the ThreeTrios game.
 */
public class ThreeTriosModel extends ReadOnlyThreeTriosModel implements TriosModel<ThreeTriosCard>{


  public ThreeTriosModel(ThreeTriosModel model){
    super(model);
  }

  public ThreeTriosModel(){
    super();
  }

  @Override
  public void playCard(int cardIndex, int row, int col) {
    List<ThreeTriosCard> tempHand = getCurrentHand();
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
    if (currentTurn == Player.RED) {
      this.handRed = tempHand;
      currentTurn = Player.BLUE;
    } else {
      this.handBlue = tempHand;
      currentTurn = Player.RED;
    }

    List<ArrayList<Integer>> playedCardIndex = new ArrayList<ArrayList<Integer>>();
    playedCardIndex.add(new ArrayList<Integer>(Arrays.asList(row, col)));
    battleStep(playedCardIndex);
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
      Player cardOwner = tempCard.owner;
      Player otherOwner = cardOwner.getOther();
      int boardLengthRows = statusBoard.length;
      int boardLengthCols = statusBoard[0].length;

      //looking east
      if (col < boardLengthCols - 1 && this.statusBoard[row][col + 1] == Status.FULL && this.cardBoard[row][col + 1].owner == otherOwner) {
        if (tempCard.getNorth().numericValue
                > this.cardBoard[row][col + 1].getSouth().numericValue) {
          this.cardBoard[row][col + 1].setOwner(cardOwner);
          //adding flipped cards to recursive list so they can also do battle
          recursiveList.add(new ArrayList<Integer>(Arrays.asList(row, col + 1)));
        }
      }
      //looking west
      if (col > 0 && this.statusBoard[row][col - 1] == Status.FULL && this.cardBoard[row][col - 1].owner == otherOwner) {
        if (tempCard.getWest().numericValue
                > this.cardBoard[row][col - 1].getEast().numericValue) {
          this.cardBoard[row][col - 1].setOwner(cardOwner);
          recursiveList.add(new ArrayList<Integer>(Arrays.asList(row, col - 1)));
        }
      }
      //looking north
      if (row > 0 && this.statusBoard[row - 1][col] == Status.FULL && this.cardBoard[row - 1][col].owner == otherOwner) {
        if (tempCard.getNorth().numericValue
                > this.cardBoard[row - 1][col].getSouth().numericValue) {
          this.cardBoard[row - 1][col].setOwner(cardOwner);
          recursiveList.add(new ArrayList<Integer>(Arrays.asList(row - 1, col)));
        }
      }
      //looking south
    if (row < boardLengthRows - 1 && this.statusBoard[row + 1][col] == Status.FULL && this.cardBoard[row + 1][col].owner == otherOwner) {
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
  public void startGame(int numCardCells, String cardFile, String boardFile) {
    if (numCardCells % 2 == 0) {
      throw new IllegalArgumentException("the number of card cells must be odd");
    }
    if (numCardCells < 0) {
      throw new IllegalArgumentException("there cannot be a negative number of card cells");
    }
    if (cardFile == null || cardFile.isEmpty()) {
      throw new IllegalArgumentException("the card file cannot be empty");
    }
    if (boardFile == null || boardFile.isEmpty()) {
      throw new IllegalArgumentException("the card file cannot be empty");
    }
    CardConfigReader cardReader = new CardConfigReader(cardFile);
    List<ThreeTriosCard> deck = cardReader.convertFile();
    BoardConfigReader boardReader = new BoardConfigReader(boardFile);
    this.statusBoard = boardReader.convertFile();
    this.cardBoard = new ThreeTriosCard[statusBoard.length][statusBoard[0].length];
    this.handRed = new ArrayList<ThreeTriosCard>();
    this.handBlue = new ArrayList<ThreeTriosCard>();

    int handSize = (numCardCells + 1) / 2;

    //shuffle the deck before startgame so the hands get random cards from the deck
    for (int i = 0; i < handSize; i++) {
      handRed.add(deck.remove(0));
      handBlue.add(deck.remove(0));
    }
  }


}
