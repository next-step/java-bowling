package bowling.model.framestatus;

import static org.assertj.core.api.Assertions.*;

import bowling.model.BonusFrame;
import bowling.model.EmptyFrame;
import bowling.model.KnockedDownPinsTest;
import org.junit.jupiter.api.Test;

class BonusTest {

  @Test
  void isOver() {
    assertThat(new Bonus().isOver()).isTrue();
  }

  @Test
  void createHasNext() {
    FrameStatus bonus = Bonus.createHasNext();
    assertThat(bonus.getNextFrame()).isEqualTo(new BonusFrame(Bonus.createHasFinished()));
    assertThat(bonus.isFinished()).isEqualTo(false);
  }

  @Test
  void createHasFinished() {
    FrameStatus bonus = Bonus.createHasFinished();
    assertThat(bonus.getNextFrame()).isEqualTo(new EmptyFrame());
    assertThat(bonus.isFinished()).isEqualTo(true);
  }

  @Test
  void testToString_STRKE() {
    FrameStatus frameStatus = new Bonus();

    assertThat(frameStatus.getResultBy(KnockedDownPinsTest.knockedDownPins_Strike)).isEqualTo("X");
  }

  @Test
  void testToString() {
    FrameStatus frameStatus = new Bonus();

    assertThat(frameStatus.getResultBy(KnockedDownPinsTest.knockedDownPins5_5)).isEqualTo("5");
  }
}