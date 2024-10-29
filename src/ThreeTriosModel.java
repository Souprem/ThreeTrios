import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ThreeTriosModel implements TriosModel<ThreeTriosCard>{

  private ThreeTriosCard [][] cardBoard;
  private Status [][] statusBoard;
  private List<ThreeTriosCard> handRed;
  private List<ThreeTriosCard> handBlue;
  private int handSize;
  private int numCardCells;
  private Player currentTurn = Player.RED;



  @Override
  public void playCard(int cardIndex, int row, int col) {
    List<ThreeTriosCard> tempHand = getcurrentHand();
    if (statusBoard[row][col] == Status.FULL || statusBoard[row][col] == Status.HOLE){
      throw new IllegalArgumentException("cannot play card to taken spot or hole");
    }
    if (cardIndex >= tempHand.size()){
      throw new IllegalArgumentException("must pick a valid card in hand");
    }
    ThreeTriosCard tempCard = tempHand.remove(cardIndex);
    this.cardBoard[row][col] = tempCard;
    this.statusBoard[row][col] = Status.FULL;
    if (currentTurn == Player.RED){
      this.handRed = tempHand;
      currentTurn = Player.BLUE;
    }
    else if (currentTurn == Player.BLUE){
      this.handBlue = tempHand;
      currentTurn = Player.RED;
    }
  }

  @Override
  public void startGame(List<ThreeTriosCard> deck, int numCardCells, Status [][] statusBoard) {
    this.numCardCells = numCardCells;
    this.statusBoard = statusBoard;
    this.handSize = (this.numCardCells + 1) / 2;

    //shuffle the deck before startgame so the hands get random cards from the deck
    for (int i = 0; i < this.handSize; i++){
      handRed.add(deck.remove(0));
      handBlue.add(deck.remove(0));
    }


  }

  @Override
  public List<ThreeTriosCard> getcurrentHand() {
    if (currentTurn == Player.RED){
      return this.handRed;
    } else if (currentTurn == Player.BLUE) {
      return this.handBlue;
    }
    else {
      throw new IllegalStateException("current turn must either be red or blue");
    }
  }

  @Override
  public List<ThreeTriosCard> getHand(int player) {
    if (player == 1){
      return this.handRed;
    } else if (player == 2){
      return this.handBlue;
    }
    else {
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
  public Status [][] convertBoardConfig(String filepath) throws IOException {
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
  }

  @Override
  public List<ThreeTriosCard> convertCardConfig(String filepath) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(filepath));
    String line;
    List<ThreeTriosCard> outputList = new ArrayList<ThreeTriosCard>();
    while ((line = reader.readLine()) != null){
      String[] tempList = line.split("\\s+");
      for (String string : tempList) {
        System.out.println(string);
      }
      outputList.add(new ThreeTriosCard(tempList[0], tempList[1], tempList[2], tempList[3], tempList[4]));
    }
    reader.close();
    return outputList;

  }
}
