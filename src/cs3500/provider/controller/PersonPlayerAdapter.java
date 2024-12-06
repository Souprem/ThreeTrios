package cs3500.provider.controller;

import cs3500.controller.PersonPlayer;
import cs3500.controller.PlayerActions;
import cs3500.model.PlayerColor;
import cs3500.model.TriosModel;
import cs3500.provider.model.PlayerTurnEnum;
import cs3500.provider.model.ThreeTriosModel;

public class PersonPlayerAdapter implements PlayerAction {
  PlayerActions playerActions;
  PlayerActionListener listener;
  TriosModel triosModel;

  public PersonPlayerAdapter(TriosModel triosModel, PlayerColor color) {
    this.playerActions = new PersonPlayer(triosModel, color);
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
    return true;
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
