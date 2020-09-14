package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PinsTest {

  @Test
  void strike_pins() {
    Pins strike = Pins.roll(10);
    assertThat(strike.isStrike()).isTrue();
  }

  @Test
  void pitching_pins() {
    Pins pitching = Pins.roll(8);
    assertThat(pitching.isPitching()).isTrue();
  }

  @Test
  void gutter_pins() {
    Pins gutter = Pins.roll(0);
    assertThat(gutter.isGutter()).isTrue();
  }

  @Test
  void spare_pins() {
    Pins pitching = Pins.roll(8);
    assertThat(pitching.isSpare(2)).isTrue();
  }

  @Test
  void open_roll() {
    Pins pitching = Pins.roll(8);
    assertThat(pitching.isOpen(1)).isTrue();
  }

  @Test
  void invalid_under_0pins() {
    assertThatThrownBy(() -> Pins.roll(-1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("0개핀 이상 입력하세요. 현재 -1핀이 입력되었습니다.");
  }

  @Test
  void invalid_over_10pins() {
    assertThatThrownBy(() -> Pins.roll(11))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("10개핀을 초과할 수 없습니다. 현재 11핀이 입력되었습니다.");
  }

  @Test
  void invalid_sum_over_10pins() {
    Pins pitching = Pins.roll(8);
    assertThatThrownBy(() -> pitching.isSpare(3))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("한 프레임은 10개핀을 초과할 수 없습니다.(이전 8개핀) 현재 3핀이 입력되었습니다.");
  }
}

