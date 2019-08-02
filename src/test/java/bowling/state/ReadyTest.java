package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.Pins;
import bowling.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

  Ready ready;

  @BeforeEach
  void 생성() {
    ready = new Ready();
  }

  @Test
  void Bowl이_스트라이크면_Strik을리턴한다() {
    assertThat(ready.bowl(new Pins(10))).isInstanceOf(Strike.class);
  }

  @Test
  void Bowl이_스트라이크가_아니면_FirstBowl을_리턴한다() {
    assertThat(ready.bowl(new Pins(5))).isInstanceOf(FirstBowl.class);
  }

  @Test
  void Ready는_프레임의_끝이아니다() {
    assertThat(new Ready().isFinish()).isFalse();
  }

  @Test
  @DisplayName("Ready의_addAdditionalScore은_noFinishScore을_리턴한다")
  void readey_addAdditionalScore_test() {
    Score prevScore = new Score(10, 2);
    State readyState = new Ready();
    assertThat(readyState.addAdditionalScore(prevScore)).isEqualTo(Score.noFinishScore());
  }
}