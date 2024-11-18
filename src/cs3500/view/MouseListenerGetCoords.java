package cs3500.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Should this class go in the view or controller?
 * This class handles mouse clicks for a given MouseEventListener.
 * When the mouse is clicked, this class delegates to whichPanel,
 * which calls particular methods on the MouseEventListener based on the input.
 */
public class MouseListenerGetCoords implements MouseListener {
  int x, y;
  int boardWidth, boardAndHandWidth;
  int currentPanel;
  int leftHandCardHeight, rightHandCardHeight;
  int cardHeight, cardWidth;
  private MouseEventListener listener;

  /**
   * A constructor for this mouse listener class.
   * @param boardWidth width of the board
   * @param boardAndHandWidth width of a single hand
   * @param leftHandCardHeight height of a card in the left hand
   * @param rightHandCardHeight height of a card in the right hand
   * @param cardHeight height of a grid card
   * @param cardWidth width of a grid card
   */
  public MouseListenerGetCoords(int boardWidth, int boardAndHandWidth,
                                int leftHandCardHeight, int rightHandCardHeight,
                                int cardHeight, int cardWidth) {
    this.boardWidth = boardWidth;
    this.boardAndHandWidth = boardAndHandWidth;
    this.leftHandCardHeight = leftHandCardHeight;
    this.rightHandCardHeight = rightHandCardHeight;
    this.cardHeight = cardHeight;
    this.cardWidth = cardWidth;
  }

  /**
   * Sets the MouseEventListener for this mouse listener.
   * @param listener
   */
  public void setMouseEventListener(MouseEventListener listener) {
    this.listener = listener;
  }

  /**
   * Resets all constructor-given parameters, to be called upon resizing of the window.
   * @param boardWidth width of the board
   * @param boardAndHandWidth width of a single hand
   * @param leftHandCardHeight height of a card in the left hand
   * @param rightHandCardHeight height of a card in the right hand
   * @param cardHeight height of a grid card
   * @param cardWidth width of a grid card
   */
  public void setAll(int boardWidth, int boardAndHandWidth,
                     int leftHandCardHeight, int rightHandCardHeight,
                     int cardHeight, int cardWidth) {
    this.boardWidth = boardWidth;
    this.boardAndHandWidth = boardAndHandWidth;
    this.leftHandCardHeight = leftHandCardHeight;
    this.rightHandCardHeight = rightHandCardHeight;
    this.cardHeight = cardHeight;
    this.cardWidth = cardWidth;
  }

  /**
   * Resets the hand card heights.
   * @param leftHandCardHeight left hand card height
   * @param rightHandCardHeight right hand card height
   */
  public void setHandSizes(int leftHandCardHeight, int rightHandCardHeight) {
    this.leftHandCardHeight = leftHandCardHeight;
    this.rightHandCardHeight = rightHandCardHeight;
  }


  @Override
  public void mouseClicked(MouseEvent e) {
    x = e.getLocationOnScreen().x;
    y = e.getLocationOnScreen().y;
    this.whichPanel();
  }

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  private void whichPanel() {
    int row = 0, col = 0, index = 0;
    if (x < boardAndHandWidth) {
      currentPanel = -1;
      index = y / leftHandCardHeight;
      if (listener != null) {
        listener.onMouseEvent(currentPanel, index, currentPanel);
        listener.onSelectedHandCard(index, -1);
      }
    } else if (x > boardWidth) {
      currentPanel = 1;
      index = y / rightHandCardHeight;
      if (listener != null) {
        listener.onMouseEvent(currentPanel, index, currentPanel);
        listener.onSelectedHandCard(index, 1);
      }
    } else {
      row = y / cardHeight;
      col = (x - boardAndHandWidth) / cardWidth;
      currentPanel = 0;
      if (listener != null) {
        listener.onMouseEvent(currentPanel, row, col);
      }
    }
  }

  /**
   * Updates the board width.
   * @param newWidth new board width
   */
  public void updateBoardWidth(int newWidth) {
    this.boardWidth = newWidth;
  }
}
