package cs3500.view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.model.ReadOnlyTriosModel;
import cs3500.model.Status;
import cs3500.model.ThreeTriosCard;

public class TriosBoardPanel extends JPanel {
  Graphics2D g2d;
  ReadOnlyTriosModel model;

  public TriosBoardPanel(int width, int height, int rows, int cols, ReadOnlyTriosModel model) {
    setLayout(new GridLayout(rows, cols, 0, 0)); // Set gaps to 0 to ensure cells are directly adjacent
    this.model = model;
    setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border around the entire panel

    ArrayList<CardCell> cardCells = new ArrayList<CardCell>();
    for (int i = rows - 1; i > -1; i--) {
      for (int j = 0; j < cols; j++) {
        //three card cell constructors that take in nothing (empty cell), card (full cell),
        // boolean (representing if there's a hole?)
        if (this.model.getStatusBoard()[i][j] == Status.EMPTY) {
          cardCells.add(new CardCell(height / rows, width / cols));
        } else if (this.model.getStatusBoard()[i][j] == Status.FULL) {
          cardCells.add(new CardCell(height / rows, width / cols, (ThreeTriosCard) this.model.getCardBoard()[i][j]));
        } if (this.model.getStatusBoard()[i][j] == Status.HOLE) {
          cardCells.add(new CardCell(height / rows, width / cols, true));
        }
      }
    }

    // Add each CardCell to the grid
    for (CardCell cell : cardCells) {
      JPanel cellPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          Graphics2D g2 = (Graphics2D) g;
          cell.draw(g2);  // Use CardCell's draw method to render the cell
        }
      };

      setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Border around the entire card panel
      // Set preferred size of the panel based on the CardCell dimensions
      cellPanel.setPreferredSize(new Dimension((int) cell.getBounds().getWidth(), (int) cell.getBounds().getHeight()));
      add(cellPanel);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    this.g2d = (Graphics2D) g;
  }


}
