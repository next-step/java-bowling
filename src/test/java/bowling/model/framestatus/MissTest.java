package bowling.model.framestatus;

import static org.assertj.core.api.Assertions.*;

import bowling.model.KnockedDownPinsTest;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class MissTest {

  @Test
  void getIndexOfNextFrames() {
    FrameStatus frameStatus = new Miss(new RequiredFirstRoll(0));

    assertThat(frameStatus.getIndexOfScoredFrames()).isEqualTo(Arrays.asList(0));
    assertThat(frameStatus.isOver()).isTrue();
  }

  @Test
  void testToString() {
    FrameStatus frameStatus = new Miss(new RequiredFirstRoll(0));

    assertThat(frameStatus.getResultBy(KnockedDownPinsTest.knockedDownPins0_1)).isEqualTo("-|1");
    assertThat(frameStatus.getResultBy(KnockedDownPinsTest.knockedDownPins2_5)).isEqualTo("2|5");
  }
}