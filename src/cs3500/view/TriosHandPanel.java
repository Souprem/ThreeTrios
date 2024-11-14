package cs3500.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import cs3500.model.Player;
import cs3500.model.ReadOnlyTriosModel;
import cs3500.model.Status;
import cs3500.model.ThreeTriosCard;

public class TriosHandPanel extends JPanel {
  Graphics2D g2d;
  ReadOnlyTriosModel model;

  public TriosHandPanel(int width, int height, int rows, int cols, ReadOnlyTriosModel model, Player owner) {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Set the layout to Y_AXIS for vertical arrangement
    this.model = model;
    this.setBorder(BorderFactory.createEmptyBorder());
    this.setSize(width, height);


    int cardHeight = this.getHeight() / model.getHand(owner).size();  // Fixed formula for height calculation
    int cardWidth = this.getWidth();  // Calculate width for each card
    initializeCardCells(model, owner, cardHeight, cardWidth, -1);
  }

  public void initializeCardCells(ReadOnlyTriosModel model, Player owner, int cardHeight, int cardWidth, int index) {
    removeAll();
    ArrayList<CardCell> cardCells = new ArrayList<CardCell>();
    for (int i = 0; i < model.getHand(owner).size(); i++) {
      if(i == 0 && owner == Player.BLUE) {
        cardCells.add(new CardCell(true, cardHeight, cardWidth, (ThreeTriosCard) this.model.getHand(owner).get(i)));
      } else {
        cardCells.add(new CardCell(false, cardHeight, cardWidth, (ThreeTriosCard) this.model.getHand(owner).get(i)));
      }
    }

    // Add each CardCell to the hand display
    for (CardCell cell : cardCells) {
      JPanel cellPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          Graphics2D g2 = (Graphics2D) g;
          cell.draw(g2);  // Use CardCell's draw method to render the cell
        }
      };

      cellPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      // Set preferred size of the panel based on the CardCell dimensions
      cellPanel.setPreferredSize(new Dimension((int) cell.getBounds().getWidth(), this.getHeight() / this.model.getHand(owner).size()));
      add(cellPanel);
      add(Box.createVerticalStrut(0));  // Ensure no space between cards
    }
    revalidate();
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    this.g2d = (Graphics2D) g;
  }
}
