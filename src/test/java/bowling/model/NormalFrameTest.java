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

    assertThat(normalFrame.getFirstKnockDownNumber()).isEqualTo(firstNumberOfPinsKnockedDown);

    normalFrame.roll(secondNumberOfPinsKnockedDown);

    assertThat(
        normalFrame.getFirstKnockDownNumber() +
            normalFrame.createDTO().getPins().getSecondKnockDownNumber())
        .isEqualTo(firstNumberOfPinsKnockedDown + secondNumberOfPinsKnockedDown);

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

    assertThat(normalFrame.getFirstKnockDownNumber()).isEqualTo(10);

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
  void getScore_spare() {
    Frames frames = new Frames();

    Score result_frame1;

    // 준비단계

    result_frame1 = frames.getFrames().get(0).getScore();
    assertThat(result_frame1).isEqualTo(new Score(0));

//    // 1-1프레임
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScore();
    assertThat(result_frame1).isEqualTo(new Score(5));

//    // 1-2프레임(스페어)
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScore();
    assertThat(result_frame1).isEqualTo(new Score(10));

    // 2-1프레임
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScore();
    assertThat(result_frame1).isEqualTo(new Score(15));

    // 2-2프레임(스페어)
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScore();
    assertThat(result_frame1).isEqualTo(new Score(15));

    // 3-1 프레임
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScore();
    assertThat(result_frame1).isEqualTo(new Score(15));
    frames.roll(5);

    frames.roll(10);//4
    frames.roll(10);//5
    frames.roll(10);//6
    frames.roll(10);//7
    frames.roll(10);//8
    frames.roll(10);//9

    // 10-1 프레임
    frames.roll(9);
    result_frame1 = frames.getFrames().get(9).getScore();
    assertThat(result_frame1).isEqualTo(new Score(9));

    // 10-2 프레임
    frames.roll(1);
    result_frame1 = frames.getFrames().get(9).getScore();
    assertThat(result_frame1).isEqualTo(new Score(10));

    // 보너스 프레임
    frames.roll(10);
    result_frame1 = frames.getFrames().get(9).getScore();
    assertThat(result_frame1).isEqualTo(new Score(20));
  }

  @Test
  void getScoreBy_strike() {
    Frames frames = new Frames();
    Score result_frame1 = new Score(0);

    // 준비단계
    result_frame1 = frames.getFrames().get(0).getScore();
    assertThat(result_frame1).isEqualTo(new Score(0));

    // 1프레임(스트라이크)
    frames.roll(10);
    result_frame1 = frames.getFrames().get(0).getScore();
    assertThat(result_frame1).isEqualTo(new Score(10));

    // 2-1프레임
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScore();
    assertThat(result_frame1).isEqualTo(new Score(15));

    // 2-2프레임(스페어)
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScore();
    assertThat(result_frame1).isEqualTo(new Score(15));

    // 3-1 프레임
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScore();
    assertThat(result_frame1).isEqualTo(new Score(20));

    // 3-2 프레임
    frames.roll(5);
    result_frame1 = frames.getFrames().get(0).getScore();
    assertThat(result_frame1).isEqualTo(new Score(20));

    // 4 프레임
    frames.roll(10);
    result_frame1 = frames.getFrames().get(0).getScore();
    assertThat(result_frame1).isEqualTo(new Score(20));

    frames.roll(1);//5
    frames.roll(1);//5
    frames.roll(1);//6
    frames.roll(1);//6
    frames.roll(1);//7
    frames.roll(1);//7
    frames.roll(1);//8
    frames.roll(1);//8
    frames.roll(1);//9
    frames.roll(1);//9

    // 10 프레임
    frames.roll(10);
    result_frame1 = frames.getFrames().get(9).getScore();
    assertThat(result_frame1).isEqualTo(new Score(10));

    // 보너스1
    frames.roll(10);
    result_frame1 = frames.getFrames().get(9).getScore();
    assertThat(result_frame1).isEqualTo(new Score(20));

    // 보너스2
    frames.roll(10);
    result_frame1 = frames.getFrames().get(9).getScore();
    assertThat(result_frame1).isEqualTo(new Score(30));

    // 보너스3
    assertThat(frames.getFrames().get(frames.getFrames().size() - 1).isFinished()).isTrue();
  }
}
