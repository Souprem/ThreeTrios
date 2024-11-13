package cs3500.view;
import cs3500.model.Card;
import cs3500.model.Player;
import cs3500.model.ThreeTriosCard;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Path2D;


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

  /**
   * Constructor for an empty cell that can hold a card.
   * @param height
   * @param width
   */
  public CardCell(int height, int width) {
    this(height, width, false, null); // Empty cell without card
  }

  /**
   * Constructor for a hole cell.
   * @param width
   * @param hole
   */
  public CardCell(int height, int width, boolean hole) {
    this(height, width, hole, null); // Hole cell without card
  }

  /**
   * Constructor for a filled cell holding a card.
   * @param width
   * @param card
   */
  public CardCell(int height, int width, ThreeTriosCard card) {
    this(height, width, false, card); // Filled cell with a card
  }

  /**
   * Private constructor for shared logic between all card states (empty, hole, filled).
   */
  private CardCell(int height, int width, boolean hole, ThreeTriosCard card) {
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
      g2.setColor(this.card.getOwner() == Player.BLUE ? Color.CYAN : Color.RED);
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
