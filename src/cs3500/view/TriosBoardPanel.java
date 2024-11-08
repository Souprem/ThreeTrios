package cs3500.view;

import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

public class TriosBoardPanel extends JPanel {

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    AffineTransform oldTransform = g2d.getTransform();
    Color oldColor = g2d.getColor();
    g2d.setColor(Color.RED);

    // Invert coordinates so origin is at bottom-left and +y is up
    g2d.translate(0, this.getHeight());
    g2d.scale(1, -1);




  }

}
