package cs3500.view;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;

/**
 * This interface represents the visual representation of a singular card cell.
 * This representation allows a card cell to be initialized with varying parameters.
 * As implementations of this interface are meant to extend a JPanel,
 * the paintComponent method is overridden, aided by helper methods below.
 */
public interface GeneralCardCell {

  /**
   * Sets font for this cards numbers, scaled according to the card size
   * Calculates positions for the numbers relative to length and width
   * Draws numbers in a diamond formation.
   * @param g2 the graphics object on which it draws
   */
  void drawNumbers(Graphics2D g2);

  /**
   * Fills the color of the card based on card owner
   * before redrawing the rectangle.
   * @param g2 the graphics object on which it draws
   * @param path the path representing the card
   */
  void fillCardColor(Graphics2D g2, Path2D path);

  /**
   * Creates a rectangle to be drawn using a Path2D object.
   * @return the rectangle as a Path2D object
   */
  Path2D createRectangle();
}
