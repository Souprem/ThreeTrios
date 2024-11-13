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

    JPanel boardPanel = new TriosBoardPanel(rows, cols, this.model);
    boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));

    JPanel leftHandPanel = new TriosHandPanel(rows, cols, this.model, Player.RED);
    JScrollPane scrollPaneLeft = new JScrollPane(leftHandPanel);
    scrollPaneLeft.setPreferredSize(new Dimension(500, 500)); // Set the size of the scrollable area

    JPanel rightHandPanel = new TriosHandPanel(rows, cols, this.model, Player.RED);
    JScrollPane scrollPaneRight = new JScrollPane(rightHandPanel);
    scrollPaneRight.setPreferredSize(new Dimension(500, 500)); // Set the size of the scrollable area

    this.add(leftHandPanel, BorderLayout.WEST);
    this.add(boardPanel, BorderLayout.CENTER);
    this.add(rightHandPanel, BorderLayout.EAST);

    this.pack();
  }

//  private void createLeftPanel(){
//    leftPanel = new TriosHandPanel();
//    leftPanel.setLayout(new GridLayout((model.getHand(Player.RED).size()), 1));
//    leftPanel.setBackground(Color.PINK);
//
//    for (int i = 1; i < model.getHand(Player.RED).size(); i++){
//
//    }
//
//    String[] labels = model.getHand(Player.RED).toArray(new String[0]);
//    for (String label : labels) {
//      JLabel lbl = new JLabel(label, SwingConstants.CENTER);
//      lbl.setFont(new Font("Arial", Font.BOLD, 24));
//      leftPanel.add(lbl);
//    }
//  }

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
