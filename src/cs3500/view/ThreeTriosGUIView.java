package cs3500.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.function.Consumer;

import javax.swing.*;

import cs3500.model.Player;
import cs3500.model.ReadOnlyTriosModel;
import cs3500.model.Status;
import cs3500.model.TriosModel;

public class ThreeTriosGUIView extends JFrame implements TriosGUIView {
  private final ReadOnlyTriosModel model;
  private TriosBoardPanel centerGrid;
  private TriosHandPanel leftPanel;
  private TriosHandPanel rightPanel;
  private int width;
  private int height;

  public ThreeTriosGUIView(ReadOnlyTriosModel model) {
    this.model = model;
    int rows = model.getCardBoard().length;
    int cols = model.getCardBoard()[0].length;

    // Initial fixed width and height, but they will be updated dynamically based on window size
    this.width = 500;
    this.height = 500;

    // Set window title and close operation
    this.setTitle("Three Trios!");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create panels with initial dimensions
    this.centerGrid = new TriosBoardPanel(width, height, rows, cols, this.model);
    this.centerGrid.setBorder(BorderFactory.createLineBorder(Color.black));

    this.leftPanel = new TriosHandPanel(width, height, rows, cols, this.model, Player.RED);
    this.rightPanel = new TriosHandPanel(width, height, rows, cols, this.model, Player.BLUE);

    // Layout setup
    this.setLayout(new BorderLayout());
    this.add(leftPanel, BorderLayout.WEST);
    this.add(centerGrid, BorderLayout.CENTER);
    this.add(rightPanel, BorderLayout.EAST);


    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int mouseX = e.getLocationOnScreen().x;
        int mouseY = e.getLocationOnScreen().y;
        System.out.println(mouseX + ", " + mouseY);
      }
    });

    // Final setup
    this.pack();  // Pack the components to their preferred sizes
  }


  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void showErrorMessage(String error) {

  }

  @Override
  public void refresh() {

  }
}
