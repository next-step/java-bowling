package bowling.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PlayerTest {

  @Test
  void create() {
    Player cyr = new Player("CYR");
    assertEquals(cyr.getName(), "CYR");
  }

  @Test
  void create_fail_max_length() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> new Player("CYRR"))
        .withMessage("이름은 3자 여야한다.");
  }

  @Test
  void create_fail_korean() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> new Player("최용락"))
        .withMessage("이름은 영어로 이뤄져야한다.");
  }
}