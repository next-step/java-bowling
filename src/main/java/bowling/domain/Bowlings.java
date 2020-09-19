package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Bowlings {

  private int current = 1;
  private final List<Player> players;

  public Bowlings(List<String> playerNames) {
    players = new ArrayList<>();
    for (String playerName : playerNames) {
      players.add(Player.ofName(playerName));
    }
  }

  public static Bowlings ofNames(List<String> playerNames) {
    return new Bowlings(playerNames);
  }

  public String nextPlayer() {
    return players.stream()
        .filter(p -> p.isPlayingFrameOf(current))
        .map(Player::name)
        .findFirst()
        .orElseGet(this::nextFirstPlayer);
  }

  private String nextFirstPlayer() {
    current++;
    if (current < 11) {
      return players.get(0).name();
    }

    return null;
  }

  public void roll(int pins) {
    players.stream()
        .filter(p -> p.isNextPlayer(nextPlayer()))
        .findFirst()
        .ifPresent(p -> p.roll(pins));
  }

  public boolean isPlaying() {
    return players.stream()
        .anyMatch(Player::isPlaying);
  }

  public List<Player> players() {
    return players;
  }
}
