package cs3500.provider.view;

import cs3500.controller.ViewFeatures;
import cs3500.model.PlayerColor;
import cs3500.provider.controller.ViewFeaturesAdapter;
import cs3500.provider.model.ReadOnlyModel;
import cs3500.view.TriosGUIView;

public class ViewAdapter implements TriosGUIView {
  TripleTriosView tripleTriosView;

  public ViewAdapter(ReadOnlyModel model) {
    this.tripleTriosView = new SimpleTripleTriosView(model);
  }

  @Override
  public void makeVisible() {
    this.tripleTriosView.display(true);
  }

  @Override
  public void showErrorMessage(String error) {

  }

  @Override
  public void refresh() {

  }

  @Override
  public void addFeatures(ViewFeatures viewFeatures) {
    cs3500.provider.controller.ViewFeatures newViewFeatures = new ViewFeaturesAdapter(viewFeatures);
    tripleTriosView.addFeatureListener(newViewFeatures);
  }

  @Override
  public void startGame() {

  }

  @Override
  public void endGame(PlayerColor winner) {

  }

  @Override
  public void onSelectedHandCard(int index, PlayerColor player) {

  }
}
