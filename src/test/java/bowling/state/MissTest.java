package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.Pins;
import bowling.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MissTest {

  Miss miss;

  @BeforeEach
  void 생성() {
    miss = new Miss(new Pins(5), new Pins(3));
  }

  @Test
  void 미스_desc확인() {
    assertThat(miss.desc()).isEqualTo("5|3");
  }

  @Test
  void Miss는_프레임의_끝이다() {
    assertThat(miss.isFinish()).isTrue();
  }

  @Test
  void 한프레임에서_Miss후에는_투구할수없다() {
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
      miss.bowl(new Pins(5));
    });
  }

  @Test
  @DisplayName("Miss의_addAdditionalScore은_remainCount가_1일때_첫번째_투구의_값만더해준다")
  void miss_addAdditionalScore_case_remain1() {
    Score prevScore = new Score(10, 1);
    State missState = new Miss(new Pins(7), new Pins(2));
    assertThat(missState.addAdditionalScore(prevScore)).isEqualTo(new Score(17, 0));
  }

  @Test
  @DisplayName("Miss의_addAdditionalScore은_remainCount가_2일때_두번의_Score값_모두_더해준다")
  void miss_addAdditionalScore_case_remain2() {
    Score prevScore = new Score(10, 2);
    State missState = new Miss(new Pins(7), new Pins(2));
    assertThat(missState.addAdditionalScore(prevScore)).isEqualTo(new Score(19, 0));
  }
}