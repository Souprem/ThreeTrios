package controller;

/**
 * The ViewFeatures interface defines the methods that the view layer should implement
 * to interact with the game's features.
 * This interface provides actions or commands that the view can trigger based on
 * user interactions or other events, such as handling grid clicks or quitting the game.
 */
public interface ViewFeatures {

  /**
   * Handles a click on the game grid.
   *
   * @param x the x-coordinate of the grid cell clicked
   * @param y the y-coordinate of the grid cell clicked
   */
  void handleGridClick(int x, int y);

  /**
   * Handles a click on a card in the red player's hand.
   *
   * @param cardIndex the index of the card clicked in the red player's hand
   */
  void handleRedHandClick(int cardIndex);

  /**
   * Handles a click on a card in the blue player's hand.
   *
   * @param cardIndex the index of the card clicked in the blue player's hand
   */
  void handleBlueHandClick(int cardIndex);

}
