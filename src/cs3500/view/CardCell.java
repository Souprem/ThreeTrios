package cs3500.view;

import cs3500.model.Card;
import cs3500.model.PlayerColor;
import cs3500.model.ThreeTriosCard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

import javax.swing.JPanel;

/**
 * This class represents the visual representation of a singular card cell.
 * This representation allows a card cell to be initialized and drawn using
 * the private draw method.
 */
public class CardCell extends JPanel {
  private Card card;
  boolean hole;
  boolean filled;
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

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    Path2D path = new Path2D.Double();
    // Create the rectangle path
    path.moveTo(0, 0);
    path.lineTo(this.getWidth(), 0);
    path.lineTo(this.getWidth(), this.getHeight());
    path.lineTo(0, this.getHeight());
    path.closePath();

    if (this.hole) {
      g2.setColor(Color.GRAY);
      g2.fill(path);
      g2.setColor(Color.DARK_GRAY);  // Set text color to black
      g2.draw(path);
    } else if (!this.filled) {
      g2.setColor(Color.YELLOW);  // Empty cell color
      g2.fill(path);
      g2.setColor(Color.DARK_GRAY);  // Set text color to black
      g2.draw(path);
    } else {
      // Fill color based on card owner
      if (this.selected) {
        g2.setColor(Color.GREEN);
      } else {
        if (card.getOwner() == PlayerColor.BLUE) {
          g2.setColor(Color.BLUE);
        } else {
          g2.setColor(Color.RED);
        }
      }
      g2.fill(path);  // Fill the rectangle
      g2.setColor(Color.DARK_GRAY);  // Set text color to black
      g2.draw(path);  // Draw the rectangle

      // Set font for the numbers, scaled according to the card size
      g2.setFont(new Font("Arial", Font.BOLD, (int) Math.min(this.getHeight(),
              this.getWidth()) / 10));

      // Calculate positions for the numbers relative to length and width for scalability
      double centerY = this.getHeight() / 2;
      double centerX = this.getWidth() / 2;
      double edgeOffsetY = this.getHeight() / 6;
      double edgeOffsetX = this.getWidth() / 6;

      // Draw numbers in a diamond formation
      g2.drawString(topNumber, (float) centerX, (float) edgeOffsetY); // Top
      g2.drawString(rightNumber, (float) (this.getWidth() - edgeOffsetX), (float) centerY); // Right
      g2.drawString(bottomNumber, (float) centerX, (float) (this.getHeight() - edgeOffsetY));
      // Bottom ^
      g2.drawString(leftNumber, (float) edgeOffsetX, (float) centerY); // Left
    }
  }
}
