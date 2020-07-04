package bowling.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreTest {

  @Test
  void add_byInt() {
    Score score = new Score(1);

    score.add(10);

    assertThat(score.getIntValue()).isEqualTo(11);
  }

}