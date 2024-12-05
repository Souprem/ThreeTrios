package cs3500.provider.view;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Objects;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import cs3500.provider.model.PlayerTurnEnum;
import cs3500.provider.model.ReadOnlyModel;
import cs3500.provider.model.CardColor;
import cs3500.provider.CardInCell;
import cs3500.provider.model.Cells;

/**
 * JTripleTrioPanel is a custom JPanel that visually represents the game board, the players' hands,
 * allows user interactions like selecting cards from the players' hands and clicking on the grid.
 * It uses mouse events to detect interactions with the board and players' cards.
 */
public class JTripleTrioPanel extends JPanel {
  private final ReadOnlyModel model;
  private cs3500.provider.controller.ViewFeatures featureListeners;
  private boolean isMouseDown;
  private int selectedRedCardIndex;
  private int selectedBlueCardIndex;

  /**
   * Constructs a JTripleTrioPanel with the specified model.
   *
   * @param model the model that represents the game state
   */
  public JTripleTrioPanel(ReadOnlyModel model) {
    this.model = Objects.requireNonNull(model);
    MouseEventsListener listener = new MouseEventsListener();
    this.addMouseListener(listener);
    this.addMouseMotionListener(listener);
    this.selectedRedCardIndex = -1;
    this.selectedBlueCardIndex = -1;
  }

  private Dimension getPreferredLogicalSize() {
    int height = model.getBoard().length;
    int width = model.getBoard()[0].length;
    //Plus two for each players hand
    return new Dimension(height, width + 2);
  }

  /**
   * Adds a feature listener to handle specific events in the view.
   *
   * @param features the feature listener to add
   */
  public void addFeaturesListener(cs3500.provider.controller.ViewFeatures features) {
    this.featureListeners = features;
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(800, 700);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.transform(transformLogicalToPhysical());
    int redPlayerCardHeight = 0;
    int bluePlayerCardHeight = 0;
    int cardHeight = getHeight() / model.getBoard().length;
    if (model.getPlayerHand(PlayerTurnEnum.RED).size() != 0) {
      redPlayerCardHeight = getHeight() / model.getPlayerHand(PlayerTurnEnum.RED).size();
    }
    if (model.getPlayerHand(PlayerTurnEnum.BLUE).size() != 0) {
      bluePlayerCardHeight = getHeight() / model.getPlayerHand(PlayerTurnEnum.BLUE).size();
    }
    int cardWidth = getWidth() / (model.getBoard()[0].length + 2);
    drawGrid(cardWidth, g2d, cardHeight);
    drawRedPlayerHand(g2d, cardWidth, redPlayerCardHeight);
    drawBluePlayerHand(cardWidth, g2d, bluePlayerCardHeight);
  }

  private void drawBluePlayerHand(int cardWidth, Graphics2D g2d, int bluePlayerCardHeight) {
    int y = 0;
    int x = getWidth() - cardWidth;
    if (model.getPlayerHand(PlayerTurnEnum.BLUE).size() == 0) {
      g2d.setColor(Color.GRAY);
      g2d.fillRect(x, 0, cardWidth, getHeight());
    }
    for (int i = 0; i < model.getPlayerHand(PlayerTurnEnum.BLUE).size(); i++) {
      CardInCell card = model.getPlayerHand(PlayerTurnEnum.BLUE).get(i);
      g2d.setColor(Color.CYAN);
      if (i == selectedBlueCardIndex) {
        g2d.setColor(Color.BLUE);
      }
      g2d.fillRect(x, y, cardWidth, bluePlayerCardHeight);
      g2d.setColor(Color.GRAY);
      g2d.drawRect(x, y, cardWidth, bluePlayerCardHeight);
      g2d.setColor(Color.BLACK);
      g2d.setFont(new Font("Arial", Font.BOLD, 30));
      g2d.drawString(card.getNumber(0), x + (cardWidth / 2) - 7, y + 30);
      g2d.drawString(card.getNumber(1), x + cardWidth - 25,
              y + (bluePlayerCardHeight / 2) + 15);
      g2d.drawString(card.getNumber(2), x + (cardWidth / 2) - 7,
              y + bluePlayerCardHeight - 5);
      g2d.drawString(card.getNumber(3), x + 10, y + (bluePlayerCardHeight / 2) + 15);
      y += bluePlayerCardHeight;
    }
  }

  private void drawRedPlayerHand(Graphics2D g2d, int cardWidth, int playerCardHeight) {
    int y = 0;
    if (model.getPlayerHand(PlayerTurnEnum.RED).size() == 0) {
      g2d.setColor(Color.GRAY);
      g2d.fillRect(0, 0, cardWidth, getHeight());
    }
    for (int i = 0; i < model.getPlayerHand(PlayerTurnEnum.RED).size(); i++) {
      CardInCell card = model.getPlayerHand(PlayerTurnEnum.RED).get(i);
      g2d.setColor(Color.PINK);
      if (i == selectedRedCardIndex) {
        g2d.setColor(Color.RED);
      }
      g2d.fillRect(0, y, cardWidth, playerCardHeight);
      g2d.setColor(Color.GRAY);
      g2d.drawRect(0, y, cardWidth, playerCardHeight);
      g2d.setColor(Color.BLACK);
      g2d.setFont(new Font("Arial", Font.BOLD, 30));
      g2d.drawString(card.getNumber(0), (cardWidth / 2) - 7, y + 30);
      g2d.drawString(card.getNumber(1), cardWidth - 30, y + (playerCardHeight / 2) + 15);
      g2d.drawString(card.getNumber(2), (cardWidth / 2) - 7, y + playerCardHeight - 5);
      g2d.drawString(card.getNumber(3), 12, y + (playerCardHeight / 2) + 15);
      y += playerCardHeight;
    }
  }

  private void drawGrid(int cardWidth, Graphics2D g2d, int cardHeight) {
    int x = cardWidth;
    int y = 0;
    for (Cells[] cells : model.getBoard()) {
      for (Cells cell : cells) {
        if (cell.cardExists()) {
          if (cell.getCard().getColor() == CardColor.RED) {
            g2d.setColor(Color.PINK);
          } else {
            g2d.setColor(Color.CYAN);
          }
          g2d.fillRect(x, y, cardWidth, cardHeight);
          g2d.setColor(Color.GRAY);
          g2d.drawRect(x, y, cardWidth, cardHeight);
          g2d.setColor(Color.BLACK);
          g2d.setFont(new Font("Arial", Font.BOLD, 35));
          g2d.drawString(cell.getCard().getNumber(0), x + (cardWidth / 2) - 10,
                  y + cardHeight / 3 - 2);
          g2d.drawString(cell.getCard().getNumber(1), x + cardWidth - 30,
                  y + (cardHeight / 2) + 15);
          g2d.drawString(cell.getCard().getNumber(2), x + (cardWidth / 2) - 10,
                  y + cardHeight - 20);
          g2d.drawString(cell.getCard().getNumber(3), x + 10, y + (cardHeight / 2) + 15);
          x += cardWidth;
        } else if (cell.isPlayable()) {
          g2d.setColor(Color.YELLOW);
          createCell(cardWidth, g2d, cardHeight, x, y);
          x += cardWidth;
        } else {
          g2d.setColor(Color.LIGHT_GRAY);
          createCell(cardWidth, g2d, cardHeight, x, y);
          x += cardWidth;
        }
      }
      x = cardWidth;
      y += cardHeight;
    }
  }

  private static void createCell(int cardWidth, Graphics2D g2d, int cardHeight, int x, int y) {
    g2d.fillRect(x, y, cardWidth, cardHeight);
    g2d.setColor(Color.GRAY);
    g2d.drawRect(x, y, cardWidth, cardHeight);
  }

  private AffineTransform transformLogicalToPhysical() {
    AffineTransform ret = new AffineTransform();
    Dimension preferred = getPreferredLogicalSize();
    //ret.scale(getWidth() / preferred.getWidth(), getHeight() / preferred.getHeight());
    ret.scale(1, 1);
    return ret;
  }

  private class MouseEventsListener extends MouseInputAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
      JTripleTrioPanel.this.isMouseDown = true;
      Point physicalP = e.getPoint();
      int redPlayerCardHeight = 0;
      int bluePlayerCardHeight = 0;
      int cardWidth = getWidth() / (model.getBoard()[0].length + 2);
      if (model.getPlayerHand(PlayerTurnEnum.RED).size() != 0) {
        redPlayerCardHeight = getHeight() / model.getPlayerHand(PlayerTurnEnum.RED).size();
      }
      if (model.getPlayerHand(PlayerTurnEnum.BLUE).size() != 0) {
        bluePlayerCardHeight = getHeight() / model.getPlayerHand(PlayerTurnEnum.BLUE).size();
      }
      checkRedHandClick(physicalP, cardWidth, redPlayerCardHeight);
      checkBlueHandClick(physicalP, cardWidth, bluePlayerCardHeight);
      checkGridClick(physicalP, cardWidth);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      JTripleTrioPanel.this.isMouseDown = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      Point physicalP = e.getPoint();
    }

    private void checkRedHandClick(Point physicalP, int cardWidth, int redPlayerCardHeight) {
      if (physicalP.x < cardWidth) {
        if (model.getPlayerTurn() != PlayerTurnEnum.RED) {
          wrongColorMessage();
        } else {
          if (redPlayerCardHeight != 0) {
            int index = physicalP.y / redPlayerCardHeight;
            if (index < model.getPlayerHand(PlayerTurnEnum.RED).size()) {
              if (index == selectedRedCardIndex) {
                selectedRedCardIndex = -1;
                System.out.println("Red player card deselected");
                featureListeners.handleRedHandClick(-1);
              } else {
                selectedRedCardIndex = index;
                System.out.println("Red player card selected: " + index);
                featureListeners.handleRedHandClick(index);
              }
              repaint();
            }
          }
        }
      }
    }

    private void wrongColorMessage() {
      JOptionPane.showMessageDialog(null, "It is " +
              model.getPlayerTurn() + "'s turn. Please select a " +
              model.getPlayerTurn() + " card.");
    }

    private void checkBlueHandClick(Point physicalP, int cardWidth, int bluePlayerCardHeight) {
      if (physicalP.x > getWidth() - cardWidth) {
        if (model.getPlayerTurn() != PlayerTurnEnum.BLUE) {
          wrongColorMessage();
        } else {
          if (bluePlayerCardHeight != 0) {
            int index = physicalP.y / bluePlayerCardHeight;
            if (index < model.getPlayerHand(PlayerTurnEnum.BLUE).size()) {
              if (index == selectedBlueCardIndex) {
                selectedBlueCardIndex = -1;
                System.out.println("Blue player card deselected");
                featureListeners.handleBlueHandClick(-1);
              } else {
                selectedBlueCardIndex = index;
                System.out.println("Blue player card selected: " + index);
                featureListeners.handleBlueHandClick(index);
              }
              repaint();
            }
          }
        }
      }
    }

    private void checkGridClick(Point physicalP, int cardWidth) {
      int gridX = physicalP.x / cardWidth - 1;
      int gridY = physicalP.y / (getHeight() / model.getBoard().length);
      if (gridX >= 0 && gridX < model.getBoard()[0].length && gridY >= 0 &&
              gridY < model.getBoard().length) {
        if ((selectedRedCardIndex == -1 && model.getPlayerTurn() == PlayerTurnEnum.RED) ||
                (selectedBlueCardIndex == -1) && model.getPlayerTurn() == PlayerTurnEnum.BLUE) {
          JOptionPane.showMessageDialog(null, "Please select a card before"
                  + "playing to Grid!");
        }
        System.out.println("Grid cell clicked at: (" + gridX + ", " + gridY + ")");
        if (model.getBoard()[gridY][gridX].isPlayable()) {
          selectedRedCardIndex = -1;
          selectedBlueCardIndex = -1;
        }
        featureListeners.handleGridClick(gridY, gridX);
        if (model.isGameOver()) {
          JOptionPane.showMessageDialog(null, "Game Over \n"
                  + model.whoWon()
                  + " Player Won!");
        }
      }
    }
  }
}
