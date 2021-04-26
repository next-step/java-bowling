package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.player.Player;
import bowling.domain.player.Players;
import java.util.ArrayList;
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

  @Test
  @DisplayName("[Players] Players 생성시 List 검증 테스트")
  void validate_players_test() {
    List<Player> playerList = new ArrayList<>();

    assertThatThrownBy(() -> new Players(playerList))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
