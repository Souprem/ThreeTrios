
import cs3500.controller.ModelFeatureImpl;
import cs3500.controller.ModelFeatures;
import cs3500.controller.PlayerActions;
import cs3500.controller.TriosController;
import cs3500.controller.ViewFeatures;
import cs3500.controller.ViewFeaturesImpl;
import cs3500.model.Card;
import cs3500.model.PlayerColor;
import cs3500.model.TriosModel;
import cs3500.view.TriosGUIView;

/**
 * A mock of the controller used to test connectivity between MVC + player classes + features
 * classes.
 */
public class ControllerMock implements TriosController {
  int testChecker = 0;

  /**
   * A public constructor for the ControllerMock which takes in the model, view, player, and
   * the number of card cells the game should be initialized with.
   * @param model the model.
   * @param view the view
   * @param player player
   * @param numCardCells number of card cells
   */
  public ControllerMock(TriosModel<Card> model, TriosGUIView view,
                              PlayerActions player, int numCardCells) {
    ModelFeatures modelFeatures = new ModelFeatureImpl(model);
    this.addModelFeatures();
    ViewFeatures viewFeatures = new ViewFeaturesImpl(view);
    this.addViewFeatures();
  }

  @Override
  public void playGame(String cardPath, String boardPath, TriosModel model) {
    //method stub, nothing needs to happen here
  }

  @Override
  public void playerChanged() {
    testChecker = 1;
  }

  /**
   * Getter for the test checker field.
   * @return testChecker
   */
  public int getTestChecker() {
    return testChecker;
  }

  @Override
  public void gameOver(PlayerColor winner) {
    //method stub, nothing needs to happen here
  }

  @Override
  public void addModelFeatures() {
    //method stub, nothing needs to happen here
  }

  @Override
  public void addViewFeatures() {
    //method stub, nothing needs to happen here
  }

  @Override
  public void selectHandCard(int handIndex, PlayerColor hand) {
    //method stub, nothing needs to happen here
  }

  @Override
  public void selectGridCard(int row, int col) {
    //method stub, nothing needs to happen here
  }
}
