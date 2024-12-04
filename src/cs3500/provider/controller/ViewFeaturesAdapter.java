package cs3500.provider.controller;

import cs3500.controller.ViewFeaturesImpl;
import cs3500.model.PlayerColor;
import cs3500.provider.view.TripleTriosView;
import cs3500.provider.view.ViewAdapter;
import cs3500.view.TriosGUIView;

public class ViewFeaturesAdapter implements ViewFeatures{
  TriosGUIView view;
  cs3500.controller.ViewFeatures delegate;

  public ViewFeaturesAdapter(TriosGUIView view) {
    this.view = view;
    this.delegate = new ViewFeaturesImpl(view);
  }

  public ViewFeaturesAdapter(cs3500.controller.ViewFeatures delegate) {
    this.delegate = delegate;
  }

  @Override
  public void handleGridClick(int x, int y) {
    this.delegate.selectGridCard(x, y);
  }

  @Override
  public void handleRedHandClick(int cardIndex) {
    this.delegate.selectHandCard(cardIndex, PlayerColor.RED);
  }

  @Override
  public void handleBlueHandClick(int cardIndex) {
    this.delegate.selectHandCard(cardIndex, PlayerColor.BLUE);
  }
}
