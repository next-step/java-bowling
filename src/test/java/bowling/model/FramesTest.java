package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FramesTest {

  public static NormalFrame normalFrame0_null;
  public static NormalFrame normalFrame0_0;
  public static NormalFrame normalFrame0_1;
  public static NormalFrame normalFrame0_10;
  public static NormalFrame normalFrame5_5;
  public static NormalFrame normalFrameStrike;
  public static NormalFrame normalFrame5_0;
  public static NormalFrame normalFrame5_1;

  static {
    try {
      initFramesTest();
    } catch (FrameOverException e) {
      throw new AssertionError();
    }
  }

  @AfterEach
  void tearDown() throws FrameOverException {
    initFramesTest();
  }

  private static void initFramesTest() throws FrameOverException {
    normalFrame0_null = new NormalFrame(0);
    normalFrame0_null.roll(0);

    normalFrame0_0 = new NormalFrame(0);
    normalFrame0_0.roll(0);
    normalFrame0_0.roll(0);

    normalFrame0_1 = new NormalFrame(1);
    normalFrame0_1.roll(0);
    normalFrame0_1.roll(1);

    normalFrame0_10 = new NormalFrame(2);
    normalFrame0_10.roll(0);
    normalFrame0_10.roll(10);

    normalFrame5_5 = new NormalFrame(3);
    normalFrame5_5.roll(5);
    normalFrame5_5.roll(5);

    normalFrameStrike = new NormalFrame(4);
    normalFrameStrike.roll(10);

    normalFrame5_0 = new NormalFrame(5);
    normalFrame5_0.roll(5);
    normalFrame5_0.roll(0);

    normalFrame5_1 = new NormalFrame(5);
    normalFrame5_1.roll(5);
    normalFrame5_1.roll(1);
  }

  @ParameterizedTest
  @MethodSource("provideKonckDownNumWithExpected")
  void roll(List<Integer> knockDownNumbers, List<NormalFrame> expected) {
    Frames frames = new Frames();

    knockDownNumbers.forEach(knockDownNumber -> {
      frames.roll(knockDownNumber);
    });

    assertThat(frames.getFrames()).isEqualTo(expected);
  }

  static Stream<Arguments> provideKonckDownNumWithExpected() {
    return Stream.of(
        arguments(
            Arrays.asList(0),
            Arrays.asList(
                normalFrame0_null
            )
        ),
        arguments(
            Arrays.asList(0, 0),
            Arrays.asList(
                normalFrame0_0
            )
        ),
        arguments(
            Arrays.asList(0, 0, 0, 1, 0, 10, 5, 5, 10),
            Arrays.asList(
                normalFrame0_0,
                normalFrame0_1,
                normalFrame0_10,
                normalFrame5_5,
                normalFrameStrike
            )
        )
    );
  }

  @Test
  void isOver() {
    Frames frames = new Frames();

    while (!frames.isOver()) {
      frames.roll(10);
    }

    assertThat(frames.getSize()).isEqualTo(10);
  }

  @ParameterizedTest
  @MethodSource("provideFrames")
  void getScores(Frames frames, List<Integer> expected) {
    assertThat(frames.getScores()).isEqualTo(expected);
  }

  static Stream<Arguments> provideFrames() {
    return Stream.of(
        arguments(
            new Frames() {{
              roll(10); // 1
              roll(1); // 2
              roll(9); // 2
              roll(1); // 3
              roll(1); // 3
              roll(1); // 4
              roll(1); // 4
            }},
            Arrays.asList(
                new Score(12), new Score(11), new Score(2), new Score(2)
            )
        )
    );
  }
}