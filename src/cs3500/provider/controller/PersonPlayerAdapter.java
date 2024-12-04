package cs3500.provider.controller;

import cs3500.controller.ModelFeatureImpl;
import cs3500.controller.ModelFeatures;
import cs3500.controller.PersonPlayer;
import cs3500.controller.PlayerActions;
import cs3500.model.PlayerColor;
import cs3500.model.TriosModel;
import cs3500.provider.model.PlayerTurnEnum;
import cs3500.provider.model.ThreeTriosModel;

public class PersonPlayerAdapter implements PlayerAction {
  PlayerActions playerActions;
  ModelFeatures modelFeatures;

  //just have the model adapater input its ReadOnlyTriosModel delegate
  public PersonPlayerAdapter(TriosModel triosModel, PlayerColor color) {
    this.playerActions = new PersonPlayer(triosModel, color);
    this.modelFeatures = new ModelFeatureImpl(triosModel);
  }

  @Override
  public void addPlayerActionListener(PlayerActionListener listener) {
    modelFeatures
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
