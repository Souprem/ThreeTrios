package cs3500.view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import cs3500.model.Player;
import cs3500.model.ReadOnlyTriosModel;
import cs3500.model.ThreeTriosCard;

public class TriosHandPanel extends JPanel {
  private final ReadOnlyTriosModel model;
  private final Player owner;

  public TriosHandPanel(int width, int height, int rows, int cols, ReadOnlyTriosModel model,
                        Player owner) {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Vertical arrangement
    this.model = model;
    this.owner = owner;
    setPreferredSize(new Dimension(width, height));
    setBorder(BorderFactory.createEmptyBorder());

    initializeCardCells();

    // Resize listener to handle vertical stretching on panel resize
    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        initializeCardCells();
      }
    });
  }

  private void initializeCardCells() {
    removeAll(); // Clear existing cells before adding new ones

    int cardHeight = getHeight() / model.getHand(owner).size();  // Calculate card height to fill
    // vertical space
    int cardWidth = getWidth();  // Use the panel width for each card's width

    ArrayList<CardCell> cardCells = new ArrayList<>();
    for (int i = 0; i < model.getHand(owner).size(); i++) {
      // Initialize each CardCell based on the card data and selection status
      cardCells.add(new CardCell(false, cardHeight, cardWidth,
              (ThreeTriosCard) model.getHand(owner).get(i)));
    }

    // Add each CardCell as a panel with a fixed width and adjustable height
    for (CardCell cell : cardCells) {
      JPanel cellPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          Graphics2D g2 = (Graphics2D) g;
          cell.draw(g2);  // Draw each cell using CardCell's draw method
        }
      };

      cellPanel.setPreferredSize(new Dimension(cardWidth, cardHeight));
      cellPanel.setMaximumSize(new Dimension(cardWidth, cardHeight));
      //Fix width, allow height to adjust
      add(cellPanel);
      add(Box.createVerticalStrut(0));  // No gap between cards
    }

    revalidate();
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
