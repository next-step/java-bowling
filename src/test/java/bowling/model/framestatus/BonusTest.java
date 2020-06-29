package bowling.model.framestatus;

import static org.assertj.core.api.Assertions.*;

import bowling.model.KnockedDownPinsTest;
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

  @Test
  void testToString_STRKE() {
    FrameStatus frameStatus = new Bonus();

    assertThat(frameStatus.toString(KnockedDownPinsTest.knockedDownPins_Strike)).isEqualTo("X");
  }

  @Test
  void testToString() {
    FrameStatus frameStatus = new Bonus();

    assertThat(frameStatus.toString(KnockedDownPinsTest.knockedDownPins5_5)).isEqualTo("5");
  }
}