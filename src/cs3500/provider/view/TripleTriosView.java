package view;

import java.io.IOException;

import controller.ViewFeatures;

/**
 * The interface for a view in the Three Trios game. It defines the basic contract for
 * rendering the game model in some format.
 */
public interface TripleTriosView {

  /**
   * Renders the game model's state to an output destination.
   *
   * @throws IOException if rendering fails due to an I/O error
   */
  void render() throws IOException;

  /**
   * Sets the callback object for the view to the given features object.
   * @param features the callback object for all components in the view
   */
  void addFeatureListener(ViewFeatures features);

  /**
   * Makes the view visible if show is true and invisible otherwise.
   * @param show whether view should be visible
   */
  void display(boolean show);

}
