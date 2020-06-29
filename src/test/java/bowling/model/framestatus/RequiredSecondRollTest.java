package bowling.model.framestatus;

import static org.assertj.core.api.Assertions.*;

import bowling.model.KnockedDownPins;
import org.junit.jupiter.api.Test;

class RequiredSecondRollTest {

  @Test
  void createNextStatusBy_Spare() {
    FrameStatus frameStatus = new RequiredFirstRoll(0);

    frameStatus = frameStatus.createNextStatusBy(
        KnockedDownPins.getBuilder(5)
            .build()
    );

    assertThat(frameStatus.isOver()).isFalse();

    assertThat(frameStatus.createNextStatusBy(
        KnockedDownPins.getBuilder(5)
            .secondKnockDownNum(5)
            .build()
    )).isInstanceOf(Spare.class);
  }

  @Test
  void createNextStatusBy_Miss() {
    FrameStatus frameStatus = new RequiredFirstRoll(0);

    frameStatus = frameStatus.createNextStatusBy(
        KnockedDownPins.getBuilder(5)
            .build()
    );

    assertThat(frameStatus.isOver()).isFalse();

    assertThat(frameStatus.createNextStatusBy(
        KnockedDownPins.getBuilder(5)
            .secondKnockDownNum(3)
            .build()
    )).isInstanceOf(Miss.class);
  }
}