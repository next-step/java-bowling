package bowling;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnockedDownPinsTest {

  @ParameterizedTest
  @MethodSource("provideScore")
  void knockDownPins(int firstNumberOfPinsKnockedDown, int secondNumberOfPinsKnockedDown) {
    KnockedDownPins pins = new KnockedDownPins();

    pins.knockDown(firstNumberOfPinsKnockedDown);

    assertThat(pins.getRemainingNum()).isEqualTo(10 - firstNumberOfPinsKnockedDown);
    assertThat(pins.getFirstKnockedDownNum().getValue()).isEqualTo(firstNumberOfPinsKnockedDown);

    pins.knockDown(secondNumberOfPinsKnockedDown);

    assertThat(pins.getRemainingNum())
        .isEqualTo(10 - firstNumberOfPinsKnockedDown - secondNumberOfPinsKnockedDown);
    assertThat(pins.getFirstKnockedDownNum().getValue()).isEqualTo(firstNumberOfPinsKnockedDown);
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
  void knockDownPins_Exceeded(int firstNumberOfPinsKnockedDown, int secondNumberOfPinsKnockedDown) {
    KnockedDownPins pins = new KnockedDownPins();

    pins.knockDown(firstNumberOfPinsKnockedDown);

    assertThat(pins.getRemainingNum()).isEqualTo(10 - firstNumberOfPinsKnockedDown);

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