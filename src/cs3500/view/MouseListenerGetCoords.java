package cs3500.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Should this class go in the view or controller?
 */
public class MouseListenerGetCoords implements MouseListener {
  int x, y;
  int boardWidth, boardAndHandWidth;
  int currentPanel;

  public MouseListenerGetCoords(int boardWidth, int boardAndHandWidth) {
    this.boardWidth = boardWidth;
    this.boardAndHandWidth = boardAndHandWidth;
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

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int[] getCoords() {
    return new int[] {x, y};
  }

  public void whichPanel() {
    if (x < boardAndHandWidth) {
      System.out.println("left hand panel");
      currentPanel = -1;
    } else if (x > boardWidth) {
      System.out.println("right hand panel");
      currentPanel = 1;
    } else {
      System.out.println("board");
      currentPanel = 0;
    }
    //Left hand panel
    //Board
//    System.out.println(x + ", " + y);
  }

  public int getCurrentPanel() {
    return currentPanel;
  }

  public void updateBoardWidth(int newWidth) {
    this.boardWidth = newWidth;
  }
}
