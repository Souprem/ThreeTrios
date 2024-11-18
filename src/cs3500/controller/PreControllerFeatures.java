package cs3500.controller;

import cs3500.view.Features;

/**
 * Represents the current pre-controller features including the printing
 * of card and cell information to the console when cards are clicked.
 */
public class PreControllerFeatures implements Features {

  @Override
  public void cardInHandInfo(int indexInHand, int owner) {
    if (owner == -1) {
      System.out.println(" Owner: Blue, " + "Index in the hand: " + indexInHand);
    } else {
      System.out.println(" Owner: Red, " + "Index in the hand: " + indexInHand);
    }
  }

  @Override
  public void cellInfo(int row, int col) {
    System.out.println("[" + row + ", " + col + "]");
  }

}
