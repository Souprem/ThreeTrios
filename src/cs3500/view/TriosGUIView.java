package cs3500.view;

import cs3500.controller.ViewFeatures;
import cs3500.model.PlayerColor;

/**
 * This interface represents a GUI view for a ThreeTrios game.
 */
public interface TriosGUIView {

  /**
   * Make the view visible. This is usually called
   * after the view is constructed.
   */
  void makeVisible();

  /**
   * Transmit an error message to the console, in case
   * the command could not be processed correctly.
   * @param error message
   */
  void showErrorMessage(String error);

  /**
   * Signal the view to draw itself.
   */
  void refresh();

  /**
   * Adds this view as a listener to the viewFeatures object passed in.
   * @param viewFeatures inputted features object.
   */
  void addFeatures(ViewFeatures viewFeatures);

  /**
   * Constructs the entire view based on the model.
   * Has to be separated into its own method to give the controller power
   * over individually starting the game for the model then the view.
   */
  void startGame();

  /**
   * Displays a text box informing the user that the game has ended.
   * @param winner the winner of the game is displayed if the winner is not null;
   */
  void endGame(PlayerColor winner);

  /**
   * Takes in the player of the hand selected and the index in the hand of the selected card.
   * @param index index in hand
   * @param player player whose hand it is
   */
  void onSelectedHandCard(int index, PlayerColor player);


}
