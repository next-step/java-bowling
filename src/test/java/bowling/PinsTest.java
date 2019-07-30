package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class PinsTest {

  @Test
  void 생성테스트() {
    Pins pins = new Pins(5);
  }

  @Test
  void 넘어간_핀수는_10을_넘어갈수없다() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      Pins pins = new Pins(11);
    });
  }

  @Test
  void 넘어간_핀수는_0보다_커야한다() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      Pins pins = new Pins(-1);
    });
  }

  @Test
  void 스트라이크는_10개핀이_넘어간것이다() {
    assertThat(new Pins(10).isStrike()).isTrue();
  }

  @Test
  void 스페어는_두Pins의_합이_10이다() {
    assertThat(new Pins(5).isSpare(new Pins(5)));
  }
}


