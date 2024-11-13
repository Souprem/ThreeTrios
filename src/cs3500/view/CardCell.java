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
  private int length;
  private int width;
  private int topNumber;
  private int rightNumber;
  private int bottomNumber;
  private int leftNumber;

  /**
   * Constructor for a card cell that is meant to represent an empty cell that can
   * at some point hold a card.
   * @param length
   * @param width
   */
  public CardCell(int length, int width) {
    this.length = length;
    this.width = width;
    this.hole = false; //this is an empty cell, not a hole
    this.filled = false;

    moveTo(0, 0);
    lineTo(length, 0);
    lineTo(length, width);
    lineTo(0, width);
    closePath();

  }

  /**
   * Constructor for a card cell that is meant to represent a hole.
   * @param length
   * @param width
   * @param hole
   */
  public CardCell(int length, int width, boolean hole) {
    this.hole = hole;
    this.length = length;
    this.width = width;
    this.filled = false;

    moveTo(0, 0);
    lineTo(length, 0);
    lineTo(length, width);
    lineTo(0, width);
    closePath();

  }

  /**
   * Constructor for a card cell that is meant to represent a cell which holds a card.
   * @param length
   * @param width
   * @param card
   */
  public CardCell(int length, int width, ThreeTriosCard card) {
    this.length = length;
    this.width = width;
    this.hole = false;
    this.filled = true;

    // Extract numbers from the ThreeTriosCard object
    this.topNumber = card.getNorth().numericValue;
    this.rightNumber = card.getEast().numericValue;
    this.bottomNumber = card.getSouth().numericValue;
    this.leftNumber = card.getWest().numericValue;
    this.card = new ThreeTriosCard(card);

    // Create the rectangle path
    moveTo(0, 0);
    lineTo(length, 0);
    lineTo(length, width);
    lineTo(0, width);
    closePath();

    // Draw the rectangle and numbers
  }

  void draw(Graphics2D g2) {
    if (this.hole) {
      g2.setColor(Color.GRAY);
      g2.fill(this);
      g2.draw(this);
    } else if (!this.filled) {
      g2.setColor(Color.YELLOW);
      g2.fill(this);
      g2.draw(this);
    } else if (this.filled) {
      if (this.card.getOwner() == Player.BLUE) {
        g2.setColor(Color.BLUE);
      } else {
        g2.setColor(Color.RED);
      }
      // Draw the rectangle path
      g2.draw(this);

      // Set font for the numbers
      g2.setFont(new Font("Arial", Font.PLAIN, (int) Math.min(length, width) / 10));

      // Calculate positions for the numbers relative to length and width for scalability
      double centerX = length / 2;
      double centerY = width / 2;
      double edgeOffsetX = length / 20; // Proportional offset from edges for better placement
      double edgeOffsetY = width / 20;

      // Draw each number around the center in a diamond-like formation
      g2.drawString(Integer.toString(topNumber), (float) centerX, (float) edgeOffsetY); // Top
      g2.drawString(Integer.toString(rightNumber), (float) (length - edgeOffsetX), (float) centerY); // Right
      g2.drawString(Integer.toString(bottomNumber), (float) centerX, (float) (width - edgeOffsetY)); // Bottom
      g2.drawString(Integer.toString(leftNumber), (float) edgeOffsetX, (float) centerY); // Left
    }
  }
}

