package cs3500.view;

import java.io.IOException;

/**
 * Behaviors needed for a view of the ThreeTrios implementation
 * that transmits information to the user.
 */
public interface TriosView {

  /**
   * Renders a model in some manner (as text, graphics, etc.)
   * @throws IOException if the rendering fails for some reason
   */
  void render() throws IOException;

}
