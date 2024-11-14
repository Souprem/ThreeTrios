package cs3500.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import cs3500.model.ReadOnlyTriosModel;
import cs3500.model.Status;
import cs3500.model.ThreeTriosCard;

public class TriosBoardPanel extends JPanel {
  private final ReadOnlyTriosModel model;
  private final int rows;
  private final int cols;

  public TriosBoardPanel(int width, int height, int rows, int cols, ReadOnlyTriosModel model) {
    this.model = model;
    this.rows = rows;
    this.cols = cols;
    setPreferredSize(new Dimension(width, height));
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    int panelWidth = getWidth();
    int panelHeight = getHeight();

    // Calculate base cell width and height
    int baseCellWidth = panelWidth / cols;
    int baseCellHeight = panelHeight / rows;

    // Calculate remaining pixels that need to be distributed
    int extraWidth = panelWidth % cols;
    int extraHeight = panelHeight % rows;

    // Initialize y position for drawing cells
    int yPosition = 0;

    for (int row = 0; row < rows; row++) {
      int cellHeight = baseCellHeight + (row < extraHeight ? 1 : 0); // Distribute extra height

      // Initialize x position for drawing cells in this row
      int xPosition = 0;
      for (int col = 0; col < cols; col++) {
        int cellWidth = baseCellWidth + (col < extraWidth ? 1 : 0); // Distribute extra width

        // Determine cell type and create cell
        CardCell cell;
        if (model.getStatusBoard()[row][col] == Status.EMPTY) {
          cell = new CardCell(false, cellHeight, cellWidth);
        } else if (model.getStatusBoard()[row][col] == Status.FULL) {
          cell = new CardCell(false, cellHeight, cellWidth,
                  (ThreeTriosCard) model.getCardBoard()[row][col]);
        } else { // Status.HOLE
          cell = new CardCell(false, cellHeight, cellWidth, true);
        }

        // Translate to (xPosition, yPosition) and draw cell
        g2d.translate(xPosition, yPosition);
        cell.draw(g2d);
        g2d.translate(-xPosition, -yPosition);

        // Move x position for next cell in this row
        xPosition += cellWidth;
      }
      // Move y position for next row
      yPosition += cellHeight;
    }
  }
}
