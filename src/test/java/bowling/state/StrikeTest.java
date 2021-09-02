package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.pin.Pin;
import bowling.pin.PinTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StrikeTest {

  @DisplayName("스트라이크 판별이 되엇을때 스트라이크 마크를 출력하는지 확인한다.")
  @Test
  void strikeMark() {
    Strike strike = new Strike(PinTest.from(10));
    assertThat(strike.score()).isEqualTo("X");
  }

  @DisplayName("스트라이크 판별이 되엇을때 스트라이크 쓰러트린 핀 개수를 출력하는지 확인한다.")
  @Test
  void strikeTotalPin() {
    Strike strike = new Strike(PinTest.from(10));
    assertThat(strike.totalPin()).isEqualTo(Pin.from(10));
  }
}