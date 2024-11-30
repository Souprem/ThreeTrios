package model.strategies;

/**
 * Represents a strategy for making moves in the game.
 */
public interface MoveStrategy {

  /**
   * Executes the strategy to make a move in the game.
   */
  void execute();

  /**
   * Checks if a card was successfully placed as part of the strategy execution.
   *
   * @return {@code true} if a card was placed; {@code false} otherwise.
   */
  boolean wasCardPlaced();
}


