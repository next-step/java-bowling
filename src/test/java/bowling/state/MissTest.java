package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.pin.PinTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MissTest {

  @DisplayName("미스 판별이 되엇을때 미스 마크를 출력하는지 확인한다.")
  @Test
  void miss() {
    Miss miss = new Miss(PinTest.from(3), PinTest.from(5));
    assertThat(miss.score()).isEqualTo("3|8");
  }
}