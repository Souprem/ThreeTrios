package cs3500.provider.controller;

import cs3500.provider.model.PlayerTurnEnum;
import cs3500.provider.model.ThreeTriosModel;

public class AIPlayerAdapter implements PlayerAction {
  @Override
  public void addPlayerActionListener(PlayerActionListener listener) {

  }

  @Override
  public void performAction(ThreeTriosModel model) {

  }

  @Override
  public boolean isHuman() {
    return false;
  }

  @Override
  public PlayerTurnEnum getTurn() {
    return null;
  }

  @Override
  public String getDisplayName() {
    return "";
  }
}
