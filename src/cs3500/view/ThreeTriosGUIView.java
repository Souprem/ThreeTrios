package cs3500.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.util.function.Consumer;

import javax.swing.*;

import cs3500.model.TriosModel;

public class ThreeTriosGUIView extends JFrame implements TriosGUIView{
  private JButton quitButton;
  private JPanel buttonPanel;


  public ThreeTriosGUIView(TriosModel model){
    super();
    int rows = model.getCardBoard().length;
    int cols = model.getCardBoard()[0].length;

    this.setTitle("Three Trios!");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel boardPanel = new TriosBoardPanel();
    boardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    boardPanel.setLayout(new GridLayout(rows, cols));
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){

      }
    }

    //quit button
    quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });
    buttonPanel.add(quitButton);

    this.pack();
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
