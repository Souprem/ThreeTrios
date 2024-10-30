package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface TriosModel<C extends Card> {
  String getCurrentPlayer();

  void playCard(int cardIndex, int row, int col);

  void startGame(int numCardCells, String cardFile, String boardFile);

  List<C> getHand(Player player);

  List<ThreeTriosCard> getcurrentHand();

  C [][] getCardBoard();

  Status[][] getStatusBoard();

  String getWinner();

  void isGameOver();

  Status[][] convertBoardConfig(String filepath) throws IOException;

  List<C> convertCardConfig(String filepath) throws IOException;






}
