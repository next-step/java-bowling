package bowling.model.framestatus;

import static org.assertj.core.api.Assertions.*;

import bowling.model.KnockedDownPins;
import bowling.model.KnockedDownPinsTest;
import org.junit.jupiter.api.Test;

class RequiredFirstRollTest {

  @Test
  void createNextStatusBy_Strike() {
    FrameStatus frameStatus = new RequiredFirstRoll(0);

    assertThat(frameStatus.isOver()).isFalse();

    frameStatus = frameStatus.createNextStatusBy(
        KnockedDownPins.getBuilder()
            .firstKnockDownNumber(10)
            .build()
    );

    assertThat(frameStatus).isInstanceOf(Strike.class);
  }

  @Test
  void createNextStatusBy_RequiredSecondRoll() {
    FrameStatus frameStatus = new RequiredFirstRoll(0);

    frameStatus = frameStatus.createNextStatusBy(
        KnockedDownPins.getBuilder()
            .firstKnockDownNumber(5)
            .build()
    );

    assertThat(frameStatus.isOver()).isFalse();

    assertThat(frameStatus).isInstanceOf(RequiredSecondRoll.class);
  }

  @Test
  void testToString() {
    assertThat(new RequiredFirstRoll(0).getResultBy(KnockedDownPinsTest.knockedDownPins0_0))
        .isEqualTo("");
  }
}