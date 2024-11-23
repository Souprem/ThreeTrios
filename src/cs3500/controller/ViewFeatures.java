package cs3500.controller;

/**
 * Features interface meant to listen for the features of the view.
 * These features
 * - (selectHandCard) listen for selection of a card from either hand
 * - (selectGridCard) listen for selection of a card from the grid
 * - (addListener) add the given TriosController as a listener for this features.
 */
public interface ViewFeatures {

  /**
   * Called from the view which holds this ViewFeatures
   * whenever the user attempts to select a card from either of
   * the hands on the board. This method notifies this interface's listener
   * when it's called.
   * @param handIndex the index within the hand from which the card was selected.
   */
  void selectHandCard(int handIndex);

  /**
   * Called from the view which holds this ViewFeatures
   * whenever the user attempts to select a card from
   * the grid. This method notifies this interface's listener
   * when it's called.
   * @param row the row on the grid at which the selection occurred.
   * @param col the column on the grid at which the selection occurred.
   */
  void selectGridCard(int row, int col);

  /**
   * Sets the view as visible.
   */
  void setVisible();

  /**
   * Adds the given TriosController to this interface as a listener.
   * @param listener the given controller.
   */
  void addListener(TriosController listener);

}

//Controller holds both features objects
//Features objects hold their respective references (view for viewFeatures, model for modelFeature)
//Controller acts as a listener for the two features objects it holds
//this class should do more of the work than PlayerActions