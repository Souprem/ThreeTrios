package cs3500.view;

import cs3500.controller.ViewFeatures;
import cs3500.controller.ViewFeaturesImpl;

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


  void addFeatures(ViewFeatures viewFeatures);
}
