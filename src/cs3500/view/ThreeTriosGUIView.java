package cs3500.view;

import cs3500.controller.ViewFeatures;
import cs3500.model.PlayerColor;
import cs3500.model.ReadOnlyTriosModel;
import cs3500.model.Status;
import cs3500.model.ThreeTriosCard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A class to represent the graphic user interface view for a
 * ThreeTrios card game. Renders the game using three panels:
 * one for each hand, and another for the grid itself.
 */
public class ThreeTriosGUIView extends JFrame implements TriosGUIView {
  private final ReadOnlyTriosModel model;
  private int rows;
  private int cols;
  private int spaceCounter;
  private int boardWidth;
  private int cardHeight;
  private int cardWidth;
  private MouseAdapter mouseListener;
  private int boardAndHandWidth;
  private int leftHandCardHeight;
  private int rightHandCardHeight;
  private int handPanelWidthInit;
  private int handPanelHeightInit;
  private JPanel leftHand;
  private JPanel rightHand;

  /**
   * A constructor for the ThreeTriosGUIView class which takes in a
   * real only version of the model.
   * @param model read only version of the model.
   */
  public ThreeTriosGUIView(ReadOnlyTriosModel model) {
    this.model = model;
  }

  @Override
  public void startGame() {
    rows = model.getCardBoard().length;
    cols = model.getCardBoard()[0].length;
    handPanelWidthInit = 200;
    handPanelHeightInit = 500;
    int initWindowHeight = 500;
    int initWindowWidth = 800;

    this.setPreferredSize(new Dimension(initWindowWidth, initWindowHeight));
    // width - 6? height - 30?

    this.setTitle("Current Player: " + model.getCurrentPlayer().toString());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout(0, 0));

    leftHand = new JPanel();
    this.createHand(model, leftHand, PlayerColor.BLUE, BorderLayout.WEST,
            handPanelWidthInit, handPanelHeightInit, -1);

    rightHand = new JPanel();
    this.createHand(model, rightHand, PlayerColor.RED, BorderLayout.EAST,
            handPanelWidthInit, handPanelHeightInit, -1);

    this.createGrid(model, 5, 4);

    //every four pixels, it updates and the grid fills
    spaceCounter = 0;
    boardWidth = 500;
    boardAndHandWidth = handPanelWidthInit + spaceCounter + 6;
    leftHandCardHeight = initWindowHeight / model.getHand(PlayerColor.BLUE).size();
    rightHandCardHeight = initWindowHeight / model.getHand(PlayerColor.RED).size();
    cardHeight = initWindowHeight / rows;
    cardWidth = (initWindowWidth - (2 * boardAndHandWidth)) / cols;

    //add this action listener separately, this should be connected to the Features interface

    //    mouseListener = new MouseListenerGetCoords(boardWidth,
    //            boardAndHandWidth, leftHandCardHeight,
    //            rightHandCardHeight,
    //            cardHeight, cardWidth);
    //    mouseListener.setMouseEventListener(this);

    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        spaceCounter = (e.getComponent().getSize().width % 3);
        boardWidth = e.getComponent().getSize().width;
        //       mouseListener.updateBoardWidth(boardWidth - handPanelWidthInit - 7 - spaceCounter);
        boardAndHandWidth = handPanelWidthInit + spaceCounter + 6;
        leftHandCardHeight = e.getComponent().getSize().height
                / model.getHand(PlayerColor.BLUE).size();
        rightHandCardHeight = e.getComponent().getSize().height
                / model.getHand(PlayerColor.RED).size();
        //        mouseListener.setHandSizes(leftHandCardHeight, rightHandCardHeight);
        cardHeight = e.getComponent().getHeight() / rows;
        cardWidth = (e.getComponent().getWidth() - (2 * boardAndHandWidth)) / cols;
        removeMouseListener(mouseListener);
        addMouseListener(mouseListener);
      }
    });

    //    this.addMouseListener(mouseListener);

    this.pack();  // Pack the components to their preferred sizes
    this.setVisible(true);
  }

  @Override
  public void endGame(PlayerColor winner) {
    String w = "none";
    if (winner != null) {
      w = winner.toString();
    }
    this.showErrorMessage("This Game Has Ended. Winner: " + w);
  }

  /**
   * Creates a new panel to act as the panel for the grid of cards.
   * @param model the model
   * @param rows the number of rows
   * @param cols the number of columns
   */
  private void createGrid(ReadOnlyTriosModel model, int rows, int cols) {
    JPanel gridPanel = new JPanel();
    gridPanel.setLayout(new GridLayout(rows, cols));
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

  /*
  Updates a given hand panel.
   */
  private void createHand(ReadOnlyTriosModel model, JPanel hand, PlayerColor player,
                          String position, int width, int height, int selectedIndex) {
    hand.setLayout(new GridLayout(0, 1));
    ArrayList<CardCell> cardCells = new ArrayList<>();
    for (int i = 0; i < model.getHand(player).size(); i++) {
      // Initialize each CardCell based on the card data and selection status
      CardCell cell;
      if (i == selectedIndex) {
        cell = new CardCell(true, 0, 0,
                (ThreeTriosCard) model.getHand(player).get(i));
      } else {
        cell = new CardCell(false, 0, 0,
                (ThreeTriosCard) model.getHand(player).get(i));
      }
      cardCells.add(cell);
    }
    for (CardCell cell : cardCells) {
      cell.setPreferredSize(new Dimension(width, height)); //this is the size of the hand
      hand.add(cell);
    }

    this.add(hand, position);
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

  @Override
  public void onSelectedHandCard(int index, PlayerColor player) {
    if (player == PlayerColor.BLUE) {
      this.remove(leftHand);
      leftHand = new JPanel();
      this.createHand(model, leftHand, PlayerColor.BLUE, BorderLayout.WEST,
              handPanelWidthInit, handPanelHeightInit, index);
    } else {
      this.remove(rightHand);
      rightHand = new JPanel();
      this.createHand(model, rightHand, PlayerColor.RED, BorderLayout.EAST,
              handPanelWidthInit, handPanelHeightInit, index);
    }
    this.revalidate();
    this.repaint();
  }

  @Override
  public void addFeatures(ViewFeatures features) {
    mouseListener = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int index;
        int row;
        int col;
        if (x < boardAndHandWidth) { //blue hand
          index = y / leftHandCardHeight;
          features.selectHandCard(index, PlayerColor.BLUE);
        } else if (x > boardWidth) { //red hand
          index = y / rightHandCardHeight;
          features.selectHandCard(index, PlayerColor.RED);
        } else { //grid
          row = y / cardHeight;
          col = (x - boardAndHandWidth) / cardWidth;
          features.selectGridCard(row, col);
        }
      }
    };
    this.addMouseListener(mouseListener);
  }
}
