package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

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
}