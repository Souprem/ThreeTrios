package cs3500.controller;


import cs3500.model.PlayerColor;
import cs3500.model.TriosAI;
import cs3500.model.TriosModel;

/**
 * A representation of the actions that can be taken
 * by a player that is an AI playing the game.
 */
public class AIPlayer implements PlayerActions {
  TriosModel model;
  PlayerColor color;
  TriosAI strategy;

  /**
   * Constuctor for the AI player which takes in the model, color, and strategy associated with this
   * AI player.
   * @param model the model.
   * @param color the color for this player.
   * @param strategy the strategy for this player.
   */
  public AIPlayer(TriosModel model, PlayerColor color, TriosAI strategy) {
    this.model = model;
    this.color = color;
    this.strategy = strategy;
  }

  @Override
  public void placeCardOnGrid(int row, int col) {
    int[] theMove = strategy.findMove(model, color);
    model.playCard(theMove[0], theMove[1], theMove[2]);
  }

  @Override
  public void selectHandCard(int handIndex) {
    //Do nothing as the selection of the card in the hand is done
    //within the strategy
  }

  @Override
  public PlayerColor getColor() {
    return this.color;
  }
}
