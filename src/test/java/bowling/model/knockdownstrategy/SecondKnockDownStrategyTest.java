package bowling.model.knockdownstrategy;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import bowling.model.KnockedDownPins;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SecondKnockDownStrategyTest {

  @ParameterizedTest
  @MethodSource("provideFirstAndSecondKnockDownNumber")
  void knockDown(int first, int second) {
    KnockedDownPins knockedDownPins = new FirstKnockDownStrategy().knockDown(first);

    knockedDownPins = new SecondKnockDownStrategy(knockedDownPins).knockDown(second);

    assertThat(knockedDownPins.getFirstKnockDownNum()).isEqualTo(first);
    assertThat(knockedDownPins.isSecondKnockDownNumNull()).isFalse();
    assertThat(knockedDownPins.getRemainingNum()).isEqualTo(10 - first - second);

  }

  static Stream<Arguments> provideFirstAndSecondKnockDownNumber() {
    return Stream.of(
        arguments(
            0,
            0
        ),
        arguments(
            0,
            10
        ),
        arguments(
            5,
            4
        )
    );
  }
}