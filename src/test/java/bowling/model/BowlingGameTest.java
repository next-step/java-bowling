package bowling.model;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BowlingGameTest {

  @Test
  void getPlayerName() {
    assertThat(BowlingGame.createWith("abc").getPlayerName()).isEqualTo("abc");
  }
}