package bowling.model.framestatus;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import bowling.model.Frame;
import bowling.model.KnockedDownPinsTest;
import bowling.model.NormalFrame;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MissTest {

  @Test
  void getIndexOfNextFrames() {
    FrameStatus frameStatus = new Miss(0);

    assertThat(frameStatus.getCurrentIndex()).isEqualTo(0);
    assertThat(frameStatus.isOver()).isTrue();
  }

  @Test
  void testToString() {
    FrameStatus frameStatus = new Miss(0);

    assertThat(frameStatus.getResultBy(KnockedDownPinsTest.knockedDownPins0_1)).isEqualTo("-|1");
    assertThat(frameStatus.getResultBy(KnockedDownPinsTest.knockedDownPins2_5)).isEqualTo("2|5");
  }

  @ParameterizedTest
  @MethodSource("getNextFrame")
  void getNextFrame(int currentIndex, Frame frame) {
    FrameStatus frameStatus = new Miss(currentIndex);

    assertThat(frameStatus.getNextFrame()).isEqualTo(frame);
  }

  static Stream<Arguments> getNextFrame() {
    return Stream.of(
        arguments(
            0,
            new NormalFrame(1)
        )
    );
  }
}