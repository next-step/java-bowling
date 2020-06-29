package bowling.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BonusTest {

  @Test
  void getIndexOfScoredFrames() {
    assertThat(new Bonus().getIndexOfScoredFrames().isEmpty()).isTrue();
  }

  @Test
  void isOver() {
    assertThat(new Bonus().isOver()).isTrue();
  }
}