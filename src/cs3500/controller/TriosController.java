package cs3500.controller;

import cs3500.model.PlayerColor;
import cs3500.model.TriosModel;

/**
 * An interface to represent the controller
 * for a game of ThreeTrios. This controller is meant
 * to work with a GUI view and TriosModel.
 */
public interface TriosController {

  /**
   * Begins the game for the controller, model, and view.
   * Adds the respective features to the model and view before calling their startGame methods.
   * @param cardPath the String representing the path to the card configuration file.
   * @param boardPath the String representing the path to the board configuration file.
   * @param model the inputted model.
   */
  void playGame(String cardPath, String boardPath, TriosModel model);

  /**
   * Revalidates and repaints the view everytime a card is played (and the turn changes
   * from one played to the other).
   */
  void playerChanged();

  /**
   * Delegates to the view that the game is over, and that it should print the game over message.
   * @param winner the winner of the game, including in the message if not null
   */
  void gameOver(PlayerColor winner);

  /**
   * Adds this TriosController as a listener for a ModelFeatures implementation.
   */
  void addModelFeatures();

  /**
   * Adds this TriosController as a listener for a ViewFeatures implementation.
   */
  void addViewFeatures();

  /**
   * Called from the ViewFeatures which holds this controller
   * whenever the user attempts to select a card from either of
   * the hands on the board. This method holds the card at the
   * inputted index as the "selected" card until it's placed on the grid
   * or a different card is selected
   * @param handIndex the index within the hand from which the card was selected.
   * @param hand the hand being selected.
   */
  void selectHandCard(int handIndex, PlayerColor hand);

  /**
   * Called from the ViewFeatures which holds this controller
   * whenever the user attempts to select a card from
   * the grid. This method places the currently selected card
   * onto the grid if the position inputted is valid.
   * @param row the row on the grid at which the selection occurred.
   * @param col the column on the grid at which the selection occurred.
   */
  void selectGridCard(int row, int col);


}

//interactions between game logic and user input
//features are view features: things that the view should be able to do
//play interaction: 
