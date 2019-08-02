package bowling.state;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.Pins;
import bowling.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StrikeTest {

  State state;

  @BeforeEach
  void 생성() {
    state = new Strike();
  }

  @Test
  void 스트라이크는_프레임의_끝이다() {
    assertThat(state.isFinish()).isTrue();
  }

  @Test
  void 한프레임에서_스트라이크후에는_투구할수없다() {
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
      state.bowl(new Pins(5));
    });
  }

  @Test
  void 스트라이크_desc확인() {
    assertThat(new Strike().desc()).isEqualTo("X");
  }

  @Test
  @DisplayName("Stike의_addAdditionalScore은_score가_10이증가하고_remainCount가_1이감소한다")
  void strike_addAdditionalScore_test() {
    Score prevScore = new Score(10, 1);
    assertThat(state.addAdditionalScore(prevScore)).isEqualTo(new Score(20, 0));
  }

}