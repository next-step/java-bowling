package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  @Test
  @DisplayName("[Player] 플레이어 생성 테스트")
  void player_init_test() {
    Player player = new Player("nhj");

    int size = player.frames().frames().size();

    assertThat(size).isEqualTo(10);
  }
}
