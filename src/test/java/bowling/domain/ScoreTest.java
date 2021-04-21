package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ScoreTest {

  @Test
  void strike_test() {
    Score result = Score.score(10, true);
    assertThat(result).isEqualTo(Score.STRIKE);
  }

  @Test
  void spare_test() {
    Score result = Score.score(10, false);
    assertThat(result).isEqualTo(Score.SPARE);
  }

  @Test
  void gutter_test() {
    Score result = Score.score(0, true);
    assertThat(result).isEqualTo(Score.GUTTER);
  }

  @Test
  void miss_test() {
    Score result = Score.score(4, true);
    assertThat(result).isEqualTo(Score.MISS);
  }
}
