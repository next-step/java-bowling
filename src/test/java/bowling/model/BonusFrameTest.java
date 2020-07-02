package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class BonusFrameTest {

  @ParameterizedTest
  @MethodSource("provideKockDownNumWtihRemaningNumber")
  void roll(int knockDownNumber, int remainingNumber) throws FrameOverException {
    BonusFrame bonusFrame = new BonusFrame();

    bonusFrame.roll(knockDownNumber);

    assertThat(bonusFrame.getRemainingPinsNumber()).isEqualTo(remainingNumber);
  }

  static Stream<Arguments> provideKockDownNumWtihRemaningNumber() {
    return Stream.of(
        arguments(
            0, 10
        ),
        arguments(
            10, 0
        ),
        arguments(
            5, 5
        )
    );
  }

  @ParameterizedTest
  @CsvSource({
      "-1",
      "11"
  })
  void roll_핀범위초과(int knockDownNumber) {
    BonusFrame bonusFrame = new BonusFrame();

    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
      bonusFrame.roll(knockDownNumber);
    });
  }

  @Test
  void isOver() {
    assertThat(new BonusFrame().isOver()).isTrue();
  }

  @Test
  void getSizeOfScoringFramesIndexes() {
    assertThat(new BonusFrame().getSizeOfScoringFramesIndexes()).isEqualTo(0);
  }
}