package cs3500.model;

import java.util.ArrayList;
import java.util.List;


public class CompositeTriosAI implements TriosAI {

  private List<TriosAI> strategies;

  public CompositeTriosAI(List<TriosAI> strategies) {
    this.strategies = strategies;
  }

  @Override
  public int[] findMove(TriosModel model, Player player) {
    List<int[]> possibleMoves = new ArrayList<>();

    // Collect moves from all strategies
    for (TriosAI strategy : strategies) {
      possibleMoves.add(strategy.findMove(model, player));
    }

    // Apply tie-breaking logic here (upper-leftmost position)
    return breakTies(possibleMoves);
  }

  private int[] breakTies(List<int[]> moves) {
    // Implement tie-breaking logic: uppermost-leftmost coordinate first,
    // then card index closest to 0
    return null;
  }
}
