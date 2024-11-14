package cs3500.controller;

import cs3500.model.Player;
import cs3500.view.Features;

/**
 * Represents the current pre-controller features including the printing
 * of card and cell information to the console when cards are clicked.
 */
public class PreControllerFeatures implements Features {

  @Override
  public void cardInHandInfo(int indexInHand, Player owner) {
    System.out.println(" Owner: " + owner + "Index in the hand: " + indexInHand);
  }

  @Override
  public void cellInfo(int row, int col) {
    System.out.println("[" + row + ", " + col + "]");
  }

}
