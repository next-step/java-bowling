package bowling.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PinsTest {

  private static final int MAX_PINS = 10;

  @ParameterizedTest
  @ValueSource(ints = {0, 10})
  @DisplayName("0이상 10이하의 쓰러진 볼링핀의 갯수를 생성한다.")
  void hit(int count) {
    Pins pins = new Pins(count);
    assertEquals(pins.getLeftCount(), MAX_PINS - count);
  }

  @ParameterizedTest
  @ValueSource(ints = {11, -1})
  @DisplayName("0미난 10초과 쓰러진 볼링핀을 생성할 때, exception")
  void hit_fail(int count) {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> new Pins(count))
        .withMessage("쓰러진핀의 갯수는 0이상 남은 핀이하 여야한다.");
  }

  @Test
  @DisplayName("남은 핀 수보다 쓰러뜨린 핀의 수가 크면, exception")
  void hit_fail_secondBall() {
    assertThatIllegalArgumentException()
        .isThrownBy(() -> new Pins(10, 11))
        .withMessage("쓰러진핀의 갯수는 0이상 남은 핀이하 여야한다.");
  }

  @Test
  @DisplayName("쓰러뜨린 핀의 수가 0이면 거터이다.")
  void isGutter() {
    Pins pins = new Pins(0);
    assertTrue(pins.isGutter());
  }
}