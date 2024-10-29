import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface TriosModel<C extends Card> {
  void playCard(int cardIndex, int row, int col);

  void startGame(List<C> deck, int numCardCells, Status [][] statusBoard);

  List<C> getHand(int player);

  List<ThreeTriosCard> getcurrentHand();

  C [][] getCardBoard();

  Status [][] getStatusBoard();

  String getWinner();

  void isGameOver();

  Status [][] convertBoardConfig(String filepath) throws IOException;

  List<C> convertCardConfig(String filepath) throws IOException;






}
