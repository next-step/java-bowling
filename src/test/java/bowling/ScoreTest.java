package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ScoreTest {

  @Test
  void 생성() {
    Score score = new Score(5, 0);
  }

  @Test
  void 동치테스트() {
    assertThat(new Score(5, 1)).isEqualTo(new Score(5, 1));
  }

  @Test
  void 현재점수를_확인_할수있다() {
    assertThat(new Score(5, 0).getScore()).isEqualTo(5);
  }

  @Test
  void 계산이_다끝났는지_확인할수있다() {
    assertThat(new Score(10, 1).isCompleteScore()).isFalse();
    assertThat(new Score(5, 0).isCompleteScore()).isTrue();
  }
}
