package cs3500.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerGetCoords implements MouseListener {
  int x, y;

  @Override
  public void mouseClicked(MouseEvent e) {
    x = e.getLocationOnScreen().x;
    y = e.getLocationOnScreen().y;
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
}
