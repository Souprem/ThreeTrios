package cs3500.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import cs3500.model.Player;
import cs3500.model.ReadOnlyTriosModel;

/**
 * A class to represent the graphic user interface view for a
 * ThreeTrios card game.
 */
public class ThreeTriosGUIView extends JFrame implements TriosGUIView {
  private final ReadOnlyTriosModel model;
  private TriosBoardPanel centerGrid;
  private TriosHandPanel leftPanel;
  private TriosHandPanel rightPanel;
  private int rows;
  private int cols;

  /**
   * A constructor for the ThreeTriosGUIView class which takes in a
   * real only version of the model.
   * @param model read only version of the model.
   */
  public ThreeTriosGUIView(ReadOnlyTriosModel model) {
    this.model = model;
    rows = model.getCardBoard().length;
    cols = model.getCardBoard()[0].length;
    this.setSize(800, 800);

    // Set window title and close operation
    this.setTitle("Current Player: " + model.getCurrentPlayer().toString());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create panels with initial dimensions
    this.centerGrid = new TriosBoardPanel(this.getWidth() / 2, this.getHeight(), rows, cols,
            this.model);
    this.centerGrid.setBorder(BorderFactory.createLineBorder(Color.black));

    this.leftPanel = new TriosHandPanel(this.getWidth() / 4, this.getHeight(), rows, cols,
            this.model, Player.RED);
    this.rightPanel = new TriosHandPanel(this.getWidth() / 4, this.getHeight(), rows, cols,
            this.model, Player.BLUE);

    // Layout setup
    this.setLayout(new BorderLayout());
    this.add(leftPanel, BorderLayout.WEST);
    this.add(centerGrid, BorderLayout.CENTER);
    this.add(rightPanel, BorderLayout.EAST);


    double handCardHeight = (double) this.getHeight()
            / model.getHand(model.getCurrentPlayer()).size();
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int mouseX = e.getLocationOnScreen().x;
        int mouseY = e.getLocationOnScreen().y;
        if (mouseX > handCardHeight - 200) {
          System.out.println(mouseY / handCardHeight + ", " + Player.BLUE);
        }
        System.out.println(mouseX + ", " + mouseY);
      }
    });

    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        int newWidth = getWidth();
        int newHeight = getHeight();
        centerGrid.setSize(newWidth / 2, newHeight);
        leftPanel.setSize(newWidth / 4, newHeight);
        rightPanel.setSize(newWidth / 4, newHeight);
        revalidate();
        repaint();
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
    System.out.println(error);
  }

  @Override
  public void refresh() {
    revalidate();
    repaint();
  }
}
