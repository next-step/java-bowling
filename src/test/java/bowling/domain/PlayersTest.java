package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.player.Player;
import bowling.domain.player.Players;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

  @Test
  @DisplayName("[Players] Players 생성 테스트")
  void create_players_test() {
    List<Player> playerList = Arrays.asList(new Player("nhj"), new Player("nn"));
    Players players = new Players(playerList);

    int size = players.players().size();

    assertThat(size).isEqualTo(2);
  }
}
