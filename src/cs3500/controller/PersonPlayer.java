package cs3500.controller;

import cs3500.model.PlayerColor;
import cs3500.model.TriosModel;

public class PersonPlayer implements PlayerActions{
  private TriosModel model;
  private PlayerColor color;
  private int indexInHand;

  public PersonPlayer(TriosModel model, PlayerColor color) {
    this.model = model;
    this.color = color;
    this.indexInHand = -1;
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
