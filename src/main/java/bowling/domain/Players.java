package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Players {

  private List<Player> players;

  public Players(List<String> playerNames) {
    players = playerNames.stream()
        .map(Player::new)
        .collect(Collectors.toList());
  }

  public List<Player> getPlayers() {
    return players;
  }
}
