package view;

import java.awt.Dimension;
import java.io.IOException;
import java.util.List;


import javax.swing.JFrame;

import controller.ViewFeatures;
import model.ReadOnlyModel;
import model.cards.CardInCell;
import model.grids.Cells;

/**
 * A textual view implementation for the Three Trios game. This class provides a representation
 * of the game model's current state, displaying the player's turn, the game board,
 * and the player's hand.
 */
public class SimpleTripleTriosView extends JFrame implements TripleTriosView {
  private final ReadOnlyModel model;
  private final JTripleTrioPanel panel;
  private Appendable out;

  /**
   * Constructs a SimpleTripleTriosView with the specified game model.
   *
   * @param model the game model for the Three Trios game
   * @throws IllegalArgumentException if the model is null
   */
  public SimpleTripleTriosView(ReadOnlyModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new JTripleTrioPanel(model);
    this.add(panel);
    this.setTitle("Current Player: " + model.getPlayerTurn());
    this.setMinimumSize(new Dimension(800, 700));
    this.pack();
  }

  @Override
  public void addFeatureListener(ViewFeatures features) {
    this.panel.addFeaturesListener(features);
  }

  @Override
  public void render() throws IOException {
    out.append(playerTurn() + boardToString() + playerHandToString());
  }

  @Override
  public void display(boolean show) {
    this.setVisible(show);
  }


  @Override
  public String toString() {
    return playerTurn() + boardToString() + playerHandToString();
  }

  /**
   * Returns the current player's turn as a string.
   *
   * @return a string indicating the current player's turn
   */
  private String playerTurn() {
    return "Player: " + model.getPlayerTurn().toString() + "\n";
  }

  /**
   * Converts the game board into a string format, with each cell represented as either an
   * underscore ('_') for playable cells, the first letter of the color for occupied cells,
   * or a space for non-playable cells.
   *
   * @return a string representation of the game board
   */
  private String boardToString() {
    String gridString = "";
    for (Cells[] cells : model.getBoard()) {
      for (Cells cell : cells) {
        if (cell.isPlayable() && !cell.cardExists()) {
          gridString += "_" + " ";
        } else if (cell.cardExists()) {
          gridString += cell.getCard().getColor().toString().substring(0, 1) + " ";
        } else if (!cell.isPlayable()) {
          gridString += "  ";
        }
      }
      gridString += "\n";
    }
    return gridString;
  }

  /**
   * Generates a string representation of the current player's hand, displaying each card
   * on a new line.
   *
   * @return a string containing all cards in the current player's hand
   */
  private String playerHandToString() {
    String handString = "";
    List<CardInCell> hand = model.getPlayersTurnHand();
    for (CardInCell handCard : hand) {
      handString += handCard.toString() + "\n";
    }
    return handString;
  }
}
