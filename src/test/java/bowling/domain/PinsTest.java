package bowling.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PinsTest {

  @Test
  @DisplayName("최초에 Pins를 생성하면 count가 10인 Pins가 생성된다.")
  void newPins() {
    Pins pins = new Pins();
    assertEquals(pins.count(), 10);
  }

  @Test
  @DisplayName("hit 하면 남은 핀정보를 리턴한다.")
  void hit() {
    Pins pins = new Pins();
    Pins leftPins = pins.hit(4);
    assertEquals(leftPins.count(), 6);
  }

  @Test
  @DisplayName("총 핀의 갯수보다 작아질 수 없다.")
  void hit_fail() {
    Pins pins = new Pins();
    assertThatIllegalArgumentException()
        .isThrownBy(() -> pins.hit(11))
        .withMessage("핀의 갯수는 0보다 커야한다.");
  }
}