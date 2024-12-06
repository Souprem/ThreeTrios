package cs3500.provider.controller;

import cs3500.controller.AIPlayer;
import cs3500.controller.PlayerActions;
import cs3500.model.PlayerColor;
import cs3500.model.TriosAI;
import cs3500.model.TriosModel;
import cs3500.provider.model.PlayerTurnEnum;
import cs3500.provider.model.ThreeTriosModel;

public class AIPlayerAdapter implements PlayerAction {
  PlayerActions playerActions;
  PlayerActionListener listener;
  TriosModel triosModel;

  public AIPlayerAdapter(TriosModel triosModel, PlayerColor playerColor, TriosAI strat) {
    playerActions = new AIPlayer(triosModel, playerColor, strat);
  }

  @Override
  public void addPlayerActionListener(PlayerActionListener listener) {
    this.listener = listener;
  }

  @Override
  public void performAction(ThreeTriosModel model) {
    listener.onPlayerAction();
  }

  @Override
  public boolean isHuman() {
    return false;
  }

  @Override
  public PlayerTurnEnum getTurn() {
    return PlayerTurnEnum.valueOf(triosModel.getCurrentPlayer().toString());
  }

  @Override
  public String getDisplayName() {
    return "";
  }
}
