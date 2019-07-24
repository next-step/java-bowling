package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LastFrameTest {

  @Test
  void 스트라이크_그리고_보너스샷을하면_끝이난다() {
    assertThat(new LastFrame().roll(10).roll(10).isGameEnd()).isEqualTo(true);
  }

  @Test
  void 스페어_그리고_보너스샷을하면_끝이난다() {
    assertThat(new LastFrame().roll(2).roll(8).roll(10).isGameEnd()).isEqualTo(true);
  }

  @Test
  void 스페어나_스크라이크_못하면_보너스_없다() {
    assertThat(new LastFrame().roll(2).roll(7).isGameEnd()).isEqualTo(true);
  }
}
