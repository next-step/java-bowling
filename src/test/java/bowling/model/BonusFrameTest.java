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
  @MethodSource("provideKockDownNumWtihRemaningNum")
  void roll(int kockDownNum, int remainingNum) throws FrameOverException {
    BonusFrame bonusFrame = new BonusFrame();

    bonusFrame.roll(kockDownNum);

    assertThat(bonusFrame.getRemainingPinsNum()).isEqualTo(remainingNum);
  }

  static Stream<Arguments> provideKockDownNumWtihRemaningNum() {
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
  void roll_핀범위초과(int knockDownNum) {
    BonusFrame bonusFrame = new BonusFrame();

    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
      bonusFrame.roll(knockDownNum);
    });
  }

  @Test
  void isOver() {
    assertThat(new BonusFrame().isOver()).isTrue();
  }

  @Test
  void getIndexOfScoredFrames() {
    assertThat(new BonusFrame().getIndexOfScoredFrames().isEmpty()).isTrue();
  }
}