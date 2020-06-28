package bowling;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SpareTest {

  @ParameterizedTest
  @MethodSource("provideCurIndexWithExpectedList")
  void getIndexOfNextFrames(int curIndex, List<Integer> expected) {
    FrameStatus frameStatus = new Spare(new RequiredFirstRoll(curIndex));

    assertThat(frameStatus.getIndexOfScoredFrames()).isEqualTo(expected);
    assertThat(frameStatus.isOver()).isTrue();
  }

  static Stream<Arguments> provideCurIndexWithExpectedList() {
    return Stream.of(
        arguments(
            0,
            Arrays.asList(
                1
            )
        )
    );
  }
}