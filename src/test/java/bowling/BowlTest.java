package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class BowlTest {

  @Test
  void strike_roll() {
    Bowl strike = Bowl.roll(10);
    assertThat(strike.isStrike()).isTrue();
  }

  @Test
  void pitching_roll() {
    Bowl strike = Bowl.roll(8);
    assertThat(strike.isStrike()).isFalse();
    assertThat(strike.isPitching()).isTrue();
  }

  @Test
  void invalid_under_0pins() {
    assertThatThrownBy(() -> Bowl.roll(-1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("0개핀 이상 입력하세요. 현재 -1핀이 입력되었습니다.");
  }

  @Test
  void invalid_over_10pins() {
    assertThatThrownBy(() -> Bowl.roll(11))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("10개핀을 초과할 수 없습니다. 현재 11핀이 입력되었습니다.");
  }
}

