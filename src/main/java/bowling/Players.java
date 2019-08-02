package bowling;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

public class Players {

  private List<Player> players = new ArrayList<>();

  public Players addPlayer(Player player) {
    players.add(player);
    return this;
  }

  public int count() {
    return players.size();
  }

  public List<String> names() {
    return players.stream()
        .map(Player::toString)
        .collect(toList());
  }
}
