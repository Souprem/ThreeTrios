package cs3500.view;

import cs3500.controller.PreControllerFeatures;
import cs3500.model.Player;
import cs3500.model.ReadOnlyTriosModel;
import cs3500.model.Status;
import cs3500.model.ThreeTriosCard;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

/**
 * A class to represent the graphic user interface view for a
 * ThreeTrios card game.
 */
public class ThreeTriosGUIView extends JFrame implements TriosGUIView {
  private final ReadOnlyTriosModel model;
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
    int handPanelWidthInit = 200;
    int handPanelHeightInit = 500;

    this.setPreferredSize(new Dimension(800, 500));

    this.setTitle("Current Player: " + model.getCurrentPlayer().toString());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout(0, 0));

    this.createHand(model, Player.BLUE, BorderLayout.WEST, handPanelWidthInit, handPanelHeightInit);

    this.createHand(model, Player.RED, BorderLayout.EAST, handPanelWidthInit, handPanelHeightInit);

    this.createGrid(model);

    PreControllerFeatures features = new PreControllerFeatures();

    //add this action listener separately, this should be connected to the Features interface
    int frameWidth = this.getWidth();
    int frameHeight = this.getHeight();
    int boardWidth = this.getWidth() - handPanelWidthInit;
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int mouseX = e.getLocationOnScreen().x;
        int mouseY = e.getLocationOnScreen().y;
        //Right hand panel
        if (mouseX > 600) {
          System.out.println("right hand panel");
        } else if (mouseX < handPanelWidthInit) {
          System.out.println("left hand panel");
        } else {
          System.out.println("board");
        }
        //Left hand panel
        //Board
        System.out.println(mouseX + ", " + mouseY);
      }
    });

    this.pack();  // Pack the components to their preferred sizes
  }

  private void createGrid(ReadOnlyTriosModel model) {
    JPanel gridPanel = new JPanel();
    gridPanel.setLayout(new GridLayout(4, 3));
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        if (model.getStatusBoard()[row][col] == Status.EMPTY) {
          CardCell cell = new CardCell(false, 0, 0);
          gridPanel.add(cell);
        } else if (model.getStatusBoard()[row][col] == Status.FULL) {
          CardCell cell = new CardCell(false, 0, 0,
                  (ThreeTriosCard) model.getCardBoard()[row][col]);
          gridPanel.add(cell);
        } else { // Status.HOLE
          CardCell cell = new CardCell(false, 0, 0, true);
          gridPanel.add(cell);
        }
      }
    }
    this.add(gridPanel, BorderLayout.CENTER);
  }

  private void createHand(ReadOnlyTriosModel model, Player blue, String west, int width, int height) {
    JPanel leftHand = new JPanel(); //blue hand
    leftHand.setLayout(new GridLayout(0, 1));
    ArrayList<CardCell> cardCells = new ArrayList<>();
    for (int i = 0; i < model.getHand(blue).size(); i++) {
      // Initialize each CardCell based on the card data and selection status
      cardCells.add(new CardCell(false, 0, 0,
              (ThreeTriosCard) model.getHand(blue).get(i)));
    }
    for (CardCell cell : cardCells) {
      cell.setPreferredSize(new Dimension(width, height)); //this is the size of the hand
      leftHand.add(cell);
    }
    this.add(leftHand, west);
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
