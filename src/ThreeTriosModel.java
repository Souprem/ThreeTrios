import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;

public class ThreeTriosModel implements TriosModel<ThreeTriosCard> {

  private ThreeTriosCard [][] cardBoard;
  private Status [][] statusBoard;
  private List<ThreeTriosCard> handRed;
  private List<ThreeTriosCard> handBlue;
  private int handSize;
  private int numCardCells;
  private Player currentTurn = Player.RED;


  @Override
  public String getCurrentPlayer() {
    if (currentTurn == Player.RED) {
      return "RED";
    } else if (currentTurn == Player.BLUE) {
      return "Blue";
    } else {
      throw new IllegalStateException("currentTurn must either be red or blue");
    }
  }

  @Override
  public void playCard(int cardIndex, int row, int col) {
    List<ThreeTriosCard> tempHand = getcurrentHand();
    if (!(statusBoard[row][col] == Status.EMPTY)) {
      throw new IllegalArgumentException("cannot play card to taken spot or hole");
    }
    if (cardIndex < 1) {
      throw new IllegalArgumentException("card index must be positive");
    }
    if (cardIndex >= tempHand.size()) {
      throw new IllegalArgumentException("must pick a valid card in hand");
    }
    //cardIndex coming in is 1-based, the row and col are 0 based
    ThreeTriosCard tempCard = tempHand.remove(cardIndex - 1);
    tempCard.setOwner(currentTurn);
    this.cardBoard[row][col] = tempCard;
    this.statusBoard[row][col] = Status.FULL;
    if (currentTurn == Player.RED) {
      this.handRed = tempHand;
      currentTurn = Player.BLUE;
    } else if (currentTurn == Player.BLUE) {
      this.handBlue = tempHand;
      currentTurn = Player.RED;
    }

    List<ArrayList<Integer>> playedCardIndex = new ArrayList<ArrayList<Integer>>();
    playedCardIndex.add(new ArrayList<Integer>(Arrays.asList(row, col)));
    battleStep(playedCardIndex);
  }

  private void battleStep(List<ArrayList<Integer>> currentCards) {
    List<ArrayList<Integer>> recursiveList = new ArrayList<ArrayList<Integer>>();
    for (List<Integer> card : currentCards) {
      int row = card.get(0);
      int col = card.get(1);
      ThreeTriosCard tempCard = this.cardBoard[row][col];
      Player cardOwner = tempCard.owner;
      int boardLengthRows = statusBoard.length;
      int boardLengthCols = statusBoard[0].length;


      //looking east
      if (col < boardLengthCols - 1 && this.statusBoard[row][col + 1] == Status.FULL) {
        if (tempCard.getNorth().numericValue
                > this.cardBoard[row][col + 1].getSouth().numericValue) {
          this.cardBoard[row][col + 1].setOwner(cardOwner);
          //adding flipped cards to recursive list so they can also do battle
          recursiveList.add(new ArrayList<Integer>(Arrays.asList(row, col + 1)));
        }
      }
      //looking west
      if (col > 0 && this.statusBoard[row][col - 1] == Status.FULL) {
        if (tempCard.getWest().numericValue
                > this.cardBoard[row][col - 1].getEast().numericValue) {
          this.cardBoard[row][col - 1].setOwner(cardOwner);
          recursiveList.add(new ArrayList<Integer>(Arrays.asList(row, col - 1)));
        }
      }
      //looking north
      if (row > 0 && this.statusBoard[row - 1][col] == Status.FULL) {
        if (tempCard.getNorth().numericValue
                > this.cardBoard[row - 1][col].getSouth().numericValue) {
          this.cardBoard[row - 1][col].setOwner(cardOwner);
          recursiveList.add(new ArrayList<Integer>(Arrays.asList(row - 1, col)));
        }
      }
      //looking south
      if (row < boardLengthRows - 1 && this.statusBoard[row + 1][col] == Status.FULL) {
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
    if (numCardCells % 2 != 0) {
      throw new IllegalArgumentException("the number of card cells must be odd");
    }
    if (numCardCells < 0) {
      throw new IllegalArgumentException("there cannot be a negative number of card cells");
    }
    List<ThreeTriosCard> deck = this.convertCardConfig(cardFile);
    this.statusBoard = this.convertBoardConfig(boardFile);
    this.cardBoard = new ThreeTriosCard[statusBoard.length][statusBoard[0].length];
    this.handRed = new ArrayList<ThreeTriosCard>();
    this.handBlue = new ArrayList<ThreeTriosCard>();

    this.numCardCells = numCardCells;
    this.handSize = (this.numCardCells + 1) / 2;

    //shuffle the deck before startgame so the hands get random cards from the deck
    for (int i = 0; i < this.handSize; i++) {
      handRed.add(deck.remove(0));
      handBlue.add(deck.remove(0));
    }
  }

  @Override
  public List<ThreeTriosCard> getcurrentHand() {
    if (currentTurn == Player.RED) {
      return this.handRed;
    } else if (currentTurn == Player.BLUE) {
      return this.handBlue;
    } else {
      throw new IllegalStateException("current turn must either be red or blue");
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
  public ThreeTriosCard [][] getCardBoard() {
    return this.cardBoard;
  }

  @Override
  public Status[][] getStatusBoard() {
    return this.statusBoard;
  }

  @Override
  public String getWinner() {
    return null;
  }

  @Override
  public void isGameOver() {

  }

  @Override
  public Status [][] convertBoardConfig(String filepath) {
    try {
      if (!doesFileExist(filepath)){
        throw new IllegalArgumentException("must input valid filepath");
      }
      BufferedReader reader = new BufferedReader(new FileReader(filepath));
      String line = reader.readLine();
      String[] rowscols = line.split("\\s+");
      int rows = Integer.parseInt(rowscols[0]);
      int cols = Integer.parseInt(rowscols[1]);
      Status[][] statusBoard = new Status[rows][cols];
      while ((line = reader.readLine()) != null) {
        for (int rowIdx = 0; rowIdx < rows; rowIdx++) {
          for (int colIndex = 0; colIndex < cols; colIndex++) {
            Character value = line.charAt(colIndex);
            if (value.equals('X')) {
              statusBoard[rowIdx][colIndex] = Status.HOLE;
            }
            if (value.equals('C')) {
              statusBoard[rowIdx][colIndex] = Status.EMPTY;
            }
          }
          line = reader.readLine();
        }
      }
      reader.close();
      return statusBoard;
    } catch (IOException e) {
      System.err.println("An IOException occurred: " + e.getMessage());
      return null;
    }
  }

  @Override
  public List<ThreeTriosCard> convertCardConfig(String filepath) {
    try {
      if (!doesFileExist(filepath)){
        throw new IllegalArgumentException("must input valid filepath");
      }
      BufferedReader reader = new BufferedReader(new FileReader(filepath));
      String line;
      List<ThreeTriosCard> outputList = new ArrayList<ThreeTriosCard>();
      while ((line = reader.readLine()) != null) {
        String[] tempList = line.split("\\s+");

        outputList.add(new ThreeTriosCard(
                tempList[0], tempList[1], tempList[2], tempList[3], tempList[4]));
      }
      reader.close();
      return outputList;
    } catch (IOException e) {
      System.err.println("An IOException occurred: " + e.getMessage());
      return null;
    }
  }

  public static boolean doesFileExist(String path) {
    File file = new File(path);
    return file.exists();
  }
}
