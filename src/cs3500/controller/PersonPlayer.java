package cs3500.controller;

import cs3500.model.PlayerColor;
import cs3500.model.TriosModel;

/**
 * A representation of the actions that can be taken
 * by a player that is an actual human playing the game.
 */
public class PersonPlayer implements PlayerActions {
  private TriosModel model;
  private PlayerColor color;
  private int indexInHand;

  /**
   * Constructor for the PersonPlayer class which takes in a model and the player's color.
   * @param model the inputted model
   * @param color the inputted color of this PersonPlayer
   */
  public PersonPlayer(TriosModel model, PlayerColor color) {
    this.model = model;
    this.color = color;
    this.indexInHand = -1;
  }

  @Override
  public PlayerColor getColor() {
    return this.color;
  }

  @Override
  public void placeCardOnGrid(int row, int col) {
    if (indexInHand == -1) {
      throw new IllegalStateException("Cannot play a card before selecting one!");
    }
    model.playCard(indexInHand, row, col);
  }

  @Override
  public void selectHandCard(int handIndex) {
    indexInHand = handIndex;
  }

}
