package bowling.domain.player;

import bowling.domain.player.Player;
import java.util.Collections;
import java.util.List;

public class Players {

  private final List<Player> players;

  public Players(List<Player> players) {
    this.players = players;
  }

  public List<Player> players() {
    return Collections.unmodifiableList(players);
  }
}
