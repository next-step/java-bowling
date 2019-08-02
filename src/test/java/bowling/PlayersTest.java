package bowling;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayersTest {

  Players players;

  @BeforeEach
  void 생성() {
    players = new Players();
  }

  @Test
  void Player추가() {
    players.addPlayer(new Player("LCJ"));
    assertThat(players.count()).isEqualTo(1);
  }

  @Test
  void 플레이어_이름리스트를_반환한다() {
    players.addPlayer(new Player("LCJ")).addPlayer(new Player("PYH"));

    assertThat(players.names()).containsExactly("LCJ","PYH");
  }
}
