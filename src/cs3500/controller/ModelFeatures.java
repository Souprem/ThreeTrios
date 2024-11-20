package cs3500.controller;

import cs3500.model.PlayerColor;

public interface ModelFeatures {

  void playerTurnChanged();

  void addListener(TriosController listener);
}
