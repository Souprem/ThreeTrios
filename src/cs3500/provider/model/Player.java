package cs3500.provider.model;

/**
 * Represents a player in the Three Trios game, capable of interacting with the game model
 * and making moves on behalf of both human and AI players.
 */
public interface Player {

  /**
   * Retrieves the display name of the player.
   *
   * @return The display name of the player.
   */
  String getDisplayName();
}
