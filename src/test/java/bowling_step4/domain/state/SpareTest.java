package bowling_step4.domain.state;

import bowling_step4.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SpareTest {

  @Test
  @DisplayName("Spare 유효성 확인")
  void valid() {
    assertThatThrownBy(() -> new Spare(7, 6))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Spare에서는 더이상 투구 할 수 없다.")
  void play() {
    Spare spare = new Spare(6, 4);
    assertThatThrownBy(() -> spare.play(new Pin(3)))
        .isInstanceOf(IllegalArgumentException.class);
  }

}