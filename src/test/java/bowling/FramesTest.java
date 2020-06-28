package bowling;

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

  public static Frame frame0_null;
  public static Frame frame0_0;
  public static Frame frame0_1;
  public static Frame frame0_10;
  public static Frame frame5_5;
  public static Frame frameStrike;

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
    frame0_null = new Frame(0);
    frame0_null.roll(0);

    frame0_0 = new Frame(0);
    frame0_0.roll(0);
    frame0_0.roll(0);

    frame0_1 = new Frame(1);
    frame0_1.roll(0);
    frame0_1.roll(1);

    frame0_10 = new Frame(2);
    frame0_10.roll(0);
    frame0_10.roll(10);

    frame5_5 = new Frame(3);
    frame5_5.roll(5);
    frame5_5.roll(5);

    frameStrike = new Frame(4);
    frameStrike.roll(10);
  }

  @ParameterizedTest
  @MethodSource("provideKonckDownNumWithExpected")
  void roll(List<Integer> knockDownNums, List<Frame> expected) {
    Frames frames = new Frames();

    knockDownNums.forEach(knockDownNum -> {
      frames.roll(knockDownNum);
    });

    assertThat(frames.getFrames()).isEqualTo(expected);
  }

  static Stream<Arguments> provideKonckDownNumWithExpected() {
    return Stream.of(
        arguments(
            Arrays.asList(0),
            Arrays.asList(
                frame0_null
            )
        ),
        arguments(
            Arrays.asList(0, 0),
            Arrays.asList(
                frame0_0
            )
        ),
        arguments(
            Arrays.asList(0, 0, 0, 1, 0, 10, 5, 5, 10),
            Arrays.asList(
                frame0_0,
                frame0_1,
                frame0_10,
                frame5_5,
                frameStrike
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
}