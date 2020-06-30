package bowling.model.framestatus;

import static org.assertj.core.api.Assertions.*;

import bowling.model.KnockedDownPins;
import bowling.model.KnockedDownPinsTest;
import org.junit.jupiter.api.Test;

class RequiredSecondRollTest {

  @Test
  void createNextStatusBy_Spare() {
    FrameStatus frameStatus = new RequiredFirstRoll(0);

    frameStatus = frameStatus.createNextStatusBy(
        KnockedDownPins.getBuilder()
            .firstKnockDownNumber(5)
            .build()
    );

    assertThat(frameStatus.isOver()).isFalse();

    assertThat(frameStatus.createNextStatusBy(
        KnockedDownPins.getBuilder()
            .firstKnockDownNumber(5)
            .secondKnockDownNumber(5)
            .build()
    )).isInstanceOf(Spare.class);
  }

  @Test
  void createNextStatusBy_Miss() {
    FrameStatus frameStatus = new RequiredFirstRoll(0);

    frameStatus = frameStatus.createNextStatusBy(
        KnockedDownPins.getBuilder()
            .firstKnockDownNumber(5)
            .build()
    );

    assertThat(frameStatus.isOver()).isFalse();

    assertThat(frameStatus.createNextStatusBy(
        KnockedDownPins.getBuilder()
            .firstKnockDownNumber(5)
            .secondKnockDownNumber(3)
            .build()
    )).isInstanceOf(Miss.class);
  }

  @Test
  void testToString() {
    FrameStatus frameStatus = new RequiredSecondRoll(new RequiredFirstRoll(0));

    assertThat(frameStatus.getResultBy(KnockedDownPinsTest.knockedDownPins5_5)).isEqualTo("5");
  }
}