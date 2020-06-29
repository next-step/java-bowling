package bowling;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
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

    assertThat(normalFrame.getRemainingPinsNum()).isEqualTo(10 - firstNumberOfPinsKnockedDown);

    normalFrame.roll(secondNumberOfPinsKnockedDown);

    assertThat(normalFrame.getRemainingPinsNum())
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

    assertThat(normalFrame.getRemainingPinsNum()).isEqualTo(0);

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
}
