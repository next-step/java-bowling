package bowling.model.framestatus;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import bowling.model.BonusFrame;
import bowling.model.Frame;
import bowling.model.FrameOverException;
import bowling.model.KnockedDownPinsTest;
import bowling.model.NormalFrame;
import bowling.model.Score;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SpareTest {

  @ParameterizedTest
  @MethodSource("provideCurrentIndexWithExpectedList")
  void getIndexOfNextFrames(int currentIndex, int expected) {
    FrameStatus frameStatus = new Spare(new RequiredFirstRoll(currentIndex));

    assertThat(frameStatus.getCurrentIndex()).isEqualTo(expected);
    assertThat(frameStatus.isOver()).isTrue();
  }

  static Stream<Arguments> provideCurrentIndexWithExpectedList() {
    return Stream.of(
        arguments(
            0,
            0
        )
    );
  }

  @Test
  void testToString() {
    FrameStatus frameStatus = new Spare(new RequiredFirstRoll(0));

    assertThat(frameStatus.getResultBy(KnockedDownPinsTest.knockedDownPins5_5)).isEqualTo("5|/");
  }

  @ParameterizedTest
  @MethodSource("getNextFrame")
  void getNextFrame(int currentIndex, Frame frame) {
    FrameStatus frameStatus = new Spare(new RequiredFirstRoll(currentIndex));

    assertThat(frameStatus.getNextFrame()).isEqualTo(frame);
  }

  static Stream<Arguments> getNextFrame() {
    return Stream.of(
        arguments(
            0,
            new NormalFrame(1)
        ),
        arguments(
            9,
            new BonusFrame(false)
        )
    );
  }

  @ParameterizedTest
  @MethodSource("getAdditionalScore")
  void getAdditionalScore(
      int currentIndex, int first, int second, Score expectedFirst, Score expectedSecond
  ) throws FrameOverException {
    FrameStatus frameStatus = new Spare(new RequiredFirstRoll(currentIndex));

    try {
      frameStatus.getNextFrame().roll(first);
    } catch (FrameOverException e) {
      frameStatus.getNextFrame().next().roll(first);
    }

    assertThat(frameStatus.getAdditionalScore()).isEqualTo(expectedFirst);

    try {
      frameStatus.getNextFrame().roll(second);
    } catch (FrameOverException e) {
      if (!frameStatus.getNextFrame().next().isOver()) {
        frameStatus.getNextFrame().next().roll(second);
      }
    }

    assertThat(frameStatus.getAdditionalScore()).isEqualTo(expectedSecond);
  }

  static Stream<Arguments> getAdditionalScore() {
    return Stream.of(
        arguments(
            0,
            1,
            1,
            new Score(1),
            new Score(1)
        ),
        arguments(
            10,
            1,
            1,
            new Score(1),
            new Score(1)
        )
    );
  }
}