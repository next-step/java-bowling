package bowling.domain.player;

import java.util.Collections;
import java.util.List;

public class Players {

  private final List<Player> players;

  public Players(List<Player> players) {
    validatePlayers(players);
    this.players = players;
  }

  public List<Player> players() {
    return Collections.unmodifiableList(players);
  }

  private void validatePlayers(List<Player> players) {
    if (players.isEmpty()) {
      throw new IllegalArgumentException("최소 한명의 참가자가 있어야 실행할 수 있습니다.");
    }
  }
}
