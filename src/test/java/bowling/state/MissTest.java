package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.pin.Pin;
import bowling.pin.PinTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MissTest {

  @DisplayName("미스 판별이 되엇을때 미스 마크를 출력하는지 확인한다.")
  @Test
  void missMark() {
    Miss miss = new Miss(PinTest.from(3), PinTest.from(5));
    assertThat(miss.scoreMessage()).isEqualTo("3|5");
  }

  @DisplayName("미스 판별이 되엇을때 쓰러트린 핀 개수를 출력하는지 확인한다.")
  @Test
  void missTotalPin() {
    Miss miss = new Miss(PinTest.from(3), PinTest.from(5));
    assertThat(miss.totalPin()).isEqualTo(Pin.from(8));
  }
}