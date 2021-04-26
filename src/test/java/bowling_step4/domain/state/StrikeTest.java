package bowling_step4.domain.state;

import bowling_step4.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StrikeTest {

  @Test
  @DisplayName("Strike 상태에선 더이상 투구할 수 없다.")
  void play() {
    Strike strike = new Strike();
    assertThatThrownBy(() -> strike.play(new Pin(4)))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
