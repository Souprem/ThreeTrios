package cs3500.controller;

import java.util.List;

import cs3500.model.PlayerColor;
import cs3500.model.TriosModel;

public class ModelFeatureImpl implements ModelFeatures {
  private List<TriosController> listener;
  TriosModel model;

  public ModelFeatureImpl(TriosModel model) {
    this.model = model;
  }

  @Override
  public void playerTurnChanged() {
    for (TriosController controller : listener) {
      controller.playerChanged(model.getCurrentPlayer());
    }
  }

  public void setThisAsListener() {
    this.model.addFeatures(this);
  }

  @Override
  public void addListener(TriosController listener) {
    this.listener.add(listener);
  }

}
