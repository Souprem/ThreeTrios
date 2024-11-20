package cs3500.controller;

import cs3500.model.PlayerColor;
import cs3500.model.TriosModel;

public interface PlayerActions {

  public void placeCardOnGrid(int row, int col);

  public void selectHandCard(int handIndex);

  //the controller is supposed to be a listener within the view
}
