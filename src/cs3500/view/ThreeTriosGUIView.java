package cs3500.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.*;

import cs3500.model.TriosModel;

public class ThreeTriosGUIView extends JFrame implements TriosGUIView{
  private JButton quitButton;
  private JPanel buttonPanel;


  public ThreeTriosGUIView(TriosModel model){
    super();
    this.setTitle("Three Trios!");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
