package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NormalFrameTest {

  @ParameterizedTest
  @MethodSource("provideScore")
  void roll(int firstNumberOfPinsKnockedDown, int secondNumberOfPinsKnockedDown,
      int thirdNumberOfPinsKnockedDown) throws Exception {
    NormalFrame normalFrame = new NormalFrame(0);

    normalFrame.roll(firstNumberOfPinsKnockedDown);

    assertThat(normalFrame.getRemainingPinsNumber()).isEqualTo(10 - firstNumberOfPinsKnockedDown);

    normalFrame.roll(secondNumberOfPinsKnockedDown);

    assertThat(normalFrame.getRemainingPinsNumber())
        .isEqualTo(10 - firstNumberOfPinsKnockedDown - secondNumberOfPinsKnockedDown);

    assertThatExceptionOfType(FrameOverException.class).isThrownBy(() -> {
      normalFrame.roll(thirdNumberOfPinsKnockedDown);
    });
  }

  static Stream<Arguments> provideScore() {
    return Stream.of(
        arguments(
            8,
            2,
            3
        ),
        arguments(
            0,
            10,
            3
        )
    );
  }

  @ParameterizedTest
  @MethodSource("provideStrikeScore")
  void roll_WhenStrike(int firstNumberOfPinsKnockedDown, int secondNumberOfPinsKnockedDown)
      throws Exception {
    NormalFrame normalFrame = new NormalFrame(0);

    normalFrame.roll(firstNumberOfPinsKnockedDown);

    assertThat(normalFrame.getRemainingPinsNumber()).isEqualTo(0);

    assertThatExceptionOfType(FrameOverException.class).isThrownBy(() -> {
      normalFrame.roll(secondNumberOfPinsKnockedDown);
    });
  }

  static Stream<Arguments> provideStrikeScore() {
    return Stream.of(
        arguments(
            10,
            2
        ),
        arguments(
            10,
            0
        )
    );
  }

  @Test
  void getScoreBy_spare() {
    Frames frames = new Frames();
    int result_frame1 = 0;

    // 준비단계

    result_frame1 = frames.getFrames().get(0).getScoreBy(frames.getFrames());
    assertThat(result_frame1).isEqualTo(0);

//    // 1-1프레임
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScoreBy(frames.getFrames());
    assertThat(result_frame1).isEqualTo(5);

//    // 1-2프레임(스페어)
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScoreBy(frames.getFrames());
    assertThat(result_frame1).isEqualTo(10);

    // 2-1프레임
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScoreBy(frames.getFrames());
    assertThat(result_frame1).isEqualTo(15);

    // 2-2프레임(스페어)
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScoreBy(frames.getFrames());
    assertThat(result_frame1).isEqualTo(15);

    // 3-1 프레임
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScoreBy(frames.getFrames());
    assertThat(result_frame1).isEqualTo(15);
  }

  @Test
  void getScoreBy_strike() {
    Frames frames = new Frames();
    int result_frame1 = 0;

    // 준비단계
    result_frame1 = frames.getFrames().get(0).getScoreBy(frames.getFrames());
    assertThat(result_frame1).isEqualTo(0);

    // 1프레임(스트라이크)
    frames.roll(10);
    result_frame1 = frames.getFrames().get(0).getScoreBy(frames.getFrames());
    assertThat(result_frame1).isEqualTo(10);

    // 2-1프레임
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScoreBy(frames.getFrames());
    assertThat(result_frame1).isEqualTo(15);

    // 2-2프레임(스페어)
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScoreBy(frames.getFrames());
    assertThat(result_frame1).isEqualTo(15);

    // 3-1 프레임
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScoreBy(frames.getFrames());
    assertThat(result_frame1).isEqualTo(20);

    // 3-2 프레임
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScoreBy(frames.getFrames());
    assertThat(result_frame1).isEqualTo(20);

    // 4-1 프레임
    frames.roll(10);
    result_frame1 = frames.getFrames().get(0).getScoreBy(frames.getFrames());
    assertThat(result_frame1).isEqualTo(20);
  }
}
