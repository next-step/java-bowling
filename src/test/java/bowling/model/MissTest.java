package bowling.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class MissTest {

  @Test
  void getIndexOfNextFrames() {
    FrameStatus frameStatus = new Miss(new RequiredFirstRoll(0));

    assertThat(frameStatus.getIndexOfScoredFrames()).isEqualTo(Arrays.asList(0));
    assertThat(frameStatus.isOver()).isTrue();
  }
}