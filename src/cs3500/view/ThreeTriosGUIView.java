package cs3500.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.util.function.Consumer;

import javax.swing.*;

import cs3500.model.Player;
import cs3500.model.ReadOnlyTriosModel;
import cs3500.model.Status;
import cs3500.model.TriosModel;

public class ThreeTriosGUIView extends JFrame implements TriosGUIView{
  private final ReadOnlyTriosModel model;
  private TriosBoardPanel centerGrid;
  private TriosHandPanel leftPanel;
  private TriosHandPanel rightPanel;

  public ThreeTriosGUIView(ReadOnlyTriosModel model){
    this.model = model;
    int rows = model.getCardBoard().length;
    int cols = model.getCardBoard()[0].length;

    this.setTitle("Three Trios!");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel boardPanel = new TriosBoardPanel();
    boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    boardPanel.setLayout(new GridLayout(rows, cols));
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols) {
        //three card cell constructors that take in nothing (empty cell), card (full cell),
        // boolean (representing if there's a hole?)
        if (this.model.getStatusBoard()[rows][cols] == Status.EMPTY) {
          boardPanel.add(new CardCell());
        } else if (this.model.getStatusBoard()[rows][cols] == Status.FULL) {
          boardPanel.add(new CardCell(this.model.getCardBoard()[rows][cols]));
        } if (this.model.getStatusBoard()[rows][cols] == Status.HOLE) {
          boardPanel.add(new CardCell(true));
        }
      }
    }

    this.pack();
  }

  private void createLeftPanel(){
    leftPanel = new TriosHandPanel();
    leftPanel.setLayout(new GridLayout((model.getHand(Player.RED).size()), 1));
    leftPanel.setBackground(Color.PINK);

    for (int i = 1; i < model.getHand(Player.RED).size(); i++){

    }

    String[] labels = model.getHand(Player.RED).toArray(new String[0]);
    for (String label : labels) {
      JLabel lbl = new JLabel(label, SwingConstants.CENTER);
      lbl.setFont(new Font("Arial", Font.BOLD, 24));
      leftPanel.add(lbl);
    }
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
