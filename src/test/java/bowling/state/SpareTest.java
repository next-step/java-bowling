package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.Pins;
import bowling.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpareTest {

  State state;

  @BeforeEach
  void 생성() {
    state = new Spare(new Pins(5), new Pins(5));
  }

  @Test
  void 스페어_desc확인() {
    assertThat(state.desc()).isEqualTo("5|/");
  }

  @Test
  void 스페어는_프레임의_끝이다() {
    assertThat(state.isFinish()).isTrue();
  }

  @Test
  void 한프레임에서_Miss후에는_투구할수없다() {
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
      state.bowl(new Pins(5));
    });
  }

  @Test
  void Spare의_addAdditionalScore은_remainCount가_1일때_첫번째_투구의_값만더해준다() {
    Score prevScore = new Score(10, 1);
    State spareState = new Spare(new Pins(7), new Pins(3));
    assertThat(spareState.addAdditionalScore(prevScore)).isEqualTo(new Score(17, 0));
  }

  @Test
  void Spare의_addAdditionalScore은_remainCount가_2일때_두번의_Score값_모두_더해준다() {
    Score prevScore = new Score(10, 2);
    State spareState = new Spare(new Pins(7), new Pins(3));
    assertThat(spareState.addAdditionalScore(prevScore)).isEqualTo(new Score(20, 0));
  }

}