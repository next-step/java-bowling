package bowling;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RemainingPinsTest {

  @ParameterizedTest
  @MethodSource("provideScore")
  void knockDownPins(int firstNumberOfPinsKnockedDown,
      int secondNumberOfPinsKnockedDown) throws Exception {
    RemainingPins pins = new RemainingPins();

    pins.knockDown(firstNumberOfPinsKnockedDown);

    assertThat(pins.getRemainingPins()).isEqualTo(10 - firstNumberOfPinsKnockedDown);
    assertThat(pins.getFirstNumberOfKnockedDown()).isEqualTo(firstNumberOfPinsKnockedDown);

    pins.knockDown(secondNumberOfPinsKnockedDown);

    assertThat(pins.getRemainingPins())
        .isEqualTo(10 - firstNumberOfPinsKnockedDown - secondNumberOfPinsKnockedDown);
    assertThat(pins.getFirstNumberOfKnockedDown()).isEqualTo(firstNumberOfPinsKnockedDown);
  }

  static Stream<Arguments> provideScore() {
    return Stream.of(
        arguments(
            8,
            2
        ),
        arguments(
            0,
            10
        )
    );
  }

  @ParameterizedTest
  @MethodSource("provideExceededScore")
  void knockDownPins_Exceeded(int firstNumberOfPinsKnockedDown, int secondNumberOfPinsKnockedDown)
      throws Exception {
    RemainingPins pins = new RemainingPins();

    pins.knockDown(firstNumberOfPinsKnockedDown);

    assertThat(pins.getRemainingPins()).isEqualTo(10 - firstNumberOfPinsKnockedDown);

    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy((() -> {
      pins.knockDown(secondNumberOfPinsKnockedDown);
    }));

  }

  static Stream<Arguments> provideExceededScore() {
    return Stream.of(
        arguments(
            8,
            3
        )
    );
  }
}