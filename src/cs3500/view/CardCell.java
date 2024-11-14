package cs3500.view;

import cs3500.model.Card;
import cs3500.model.Player;
import cs3500.model.ThreeTriosCard;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Path2D;

/**
 * This class represents the visual representation of a singular card cell.
 * This representation allows a card cell to be initialized and drawn using
 * the private draw method.
 */
public class CardCell extends Path2D.Double {
  private Card card;
  boolean hole;
  boolean filled;
  private int height;
  private int width;
  private String topNumber;
  private String rightNumber;
  private String bottomNumber;
  private String leftNumber;
  private boolean selected;

  /**
   * Constructor for an empty cell that can hold a card.
   * @param selected boolean determining if this card is currently selected
   * @param height the cell height
   * @param width the cell width
   */
  public CardCell(boolean selected, int height, int width) {
    this(selected, height, width, false, null); // Empty cell without card
  }


  /**
   * Constructor for a hole cell.
   * @param selected boolean determining if this card is currently selected
   * @param height the cell's height
   * @param width the cell's width
   * @param hole a boolean representing whether this cell is a hole.
   */
  public CardCell(boolean selected, int height, int width, boolean hole) {
    this(selected, height, width, hole, null); // Hole cell without card
  }

  /**
   * Constructor for a filled cell holding a card.
   * @param selected boolean determining if this card is currently selected
   * @param height the cell's height
   * @param width the cell's width
   */
  public CardCell(boolean selected, int height, int width, ThreeTriosCard card) {
    this(selected, height, width, false, card); // Filled cell with a card
  }

  /**
   * Private constructor for shared logic between all card states (empty, hole, filled).
   */
  private CardCell(boolean selected, int height, int width, boolean hole, ThreeTriosCard card) {
    this.selected = selected;
    this.height = height;
    this.width = width;
    this.hole = hole;
    this.filled = card != null;

    if (filled) {
      // Set up the card's numeric values
      this.card = new ThreeTriosCard(card);
      if (card.getNorth().numericValue == 10) {
        this.topNumber = card.getNorth().stringValue;
      } else {
        this.topNumber = Integer.toString(card.getNorth().numericValue);
      }
      if (card.getEast().numericValue == 10) {
        this.rightNumber = card.getEast().stringValue;
      } else {
        this.rightNumber = Integer.toString(card.getEast().numericValue);
      }
      if (card.getSouth().numericValue == 10) {
        this.bottomNumber = card.getSouth().stringValue;
      } else {
        this.bottomNumber = Integer.toString(card.getSouth().numericValue);
      }
      if (card.getWest().numericValue == 10) {
        this.leftNumber = card.getWest().stringValue;
      } else {
        this.leftNumber = Integer.toString(card.getWest().numericValue);
      }
    }

    // Create the rectangle path
    moveTo(0, 0);
    lineTo(height, 0);
    lineTo(height, width);
    lineTo(0, width);
    closePath();
  }

  /**
   * This method draws the current card cell, given a graphics object.
   * This method is package private as it's used by the board and hand
   * panels (all within the view package) to draw their respective cards.
   * @param g2 the Graphics2D object.
   */
  void draw(Graphics2D g2) {
    if (this.hole) {
      g2.setColor(Color.GRAY);
      g2.fill(this);
      g2.setColor(Color.DARK_GRAY);  // Set text color to black
      g2.draw(this);
    } else if (!this.filled) {
      g2.setColor(Color.YELLOW);  // Empty cell color
      g2.fill(this);
      g2.setColor(Color.DARK_GRAY);  // Set text color to black
      g2.draw(this);
    } else {
      // Fill color based on card owner
      if (this.selected) {
        g2.setColor(Color.GREEN);
      } else {
        g2.setColor(this.card.getOwner() == Player.BLUE ? Color.CYAN : Color.RED);
      }
      g2.fill(this);  // Fill the rectangle
      g2.setColor(Color.DARK_GRAY);  // Set text color to black
      g2.draw(this);  // Draw the rectangle

      // Set font for the numbers, scaled according to the card size
      g2.setFont(new Font("Arial", Font.BOLD, (int) Math.min(height, width) / 10));

      // Calculate positions for the numbers relative to length and width for scalability
      double centerX = height / 2;
      double centerY = width / 2;
      double edgeOffsetX = height / 10;
      double edgeOffsetY = width / 10;

      // Draw numbers in a diamond formation
      g2.drawString(topNumber, (float) centerX, (float) edgeOffsetY); // Top
      g2.drawString(rightNumber, (float) (height - edgeOffsetX), (float) centerY); // Right
      g2.drawString(bottomNumber, (float) centerX, (float) (width - edgeOffsetY)); // Bottom
      g2.drawString(leftNumber, (float) edgeOffsetX, (float) centerY); // Left
    }
  }
}
