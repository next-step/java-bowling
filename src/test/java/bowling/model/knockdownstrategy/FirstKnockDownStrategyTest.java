package bowling.model.knockdownstrategy;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import bowling.model.KnockedDownPins;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FirstKnockDownStrategyTest {

  @ParameterizedTest
  @MethodSource("provideKockedDownNumber")
  void knockDown(int number) {
    KnockDownStrategy knockDownStrategy = new FirstKnockDownStrategy();

    KnockedDownPins knockedDownPins = knockDownStrategy.knockDown(number);

    assertThat(knockedDownPins.getFirstKnockDownNumber())
        .isEqualTo(number);

    assertThat(knockedDownPins.isSecondKnockDownNumNull()).isTrue();
  }

  static Stream<Arguments> provideKockedDownNumber() {
    return IntStream.iterate(0, n -> n + 1)
        .limit(9)
        .mapToObj(n -> arguments(n));
  }
}