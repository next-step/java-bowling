package bowling.model.framestatus;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import bowling.model.KnockedDownPins;
import bowling.model.KnockedDownPinsTest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

  @ParameterizedTest
  @MethodSource("provideCurrentIndexWithExpectedList")
  void getIndexOfNextFrames(int currentIndex, List<Integer> expected) {
    FrameStatus frameStatus = new RequiredSecondRoll(new RequiredFirstRoll(currentIndex));

    assertThat(frameStatus.getScoringFramesIndexes()).isEqualTo(expected);
    assertThat(frameStatus.isOver()).isFalse();
  }

  static Stream<Arguments> provideCurrentIndexWithExpectedList() {
    return Stream.of(
        arguments(
            0,
            Arrays.asList(
                0
            )
        )
    );
  }

  @ParameterizedTest
  @MethodSource("provideCurrentIndexWithExpectedSize")
  void getSizeOfScoringFramesIndexes(int currentIndex, int expected) {
    FrameStatus frameStatus = new RequiredSecondRoll(new RequiredFirstRoll(currentIndex));

    assertThat(frameStatus.getSizeOfScoringFramesIndexes()).isEqualTo(expected);
  }

  static Stream<Arguments> provideCurrentIndexWithExpectedSize() {
    return Stream.of(
        arguments(
            0,
            1
        )
    );
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