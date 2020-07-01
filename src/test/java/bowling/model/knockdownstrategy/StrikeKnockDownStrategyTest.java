package bowling.model.knockdownstrategy;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StrikeKnockDownStrategyTest {

  @Test
  void knockDown() {
    KnockDownStrategy knockDownStrategy = new StrikeKnockDownStrategy();

    assertThat(knockDownStrategy.knockDown(10).getFirstKnockDownNumber()).isEqualTo(10);
  }

  @ParameterizedTest
  @MethodSource("provideNotStrikeNumber")
  void knockDown_notStrike(int number) {
    KnockDownStrategy knockDownStrategy = new StrikeKnockDownStrategy();

    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
      knockDownStrategy.knockDown(number);
    });
  }

  static Stream<Arguments> provideNotStrikeNumber() {
    return Stream.of(
        arguments(
            11
        ),
        arguments(
            0
        ),
        arguments(
            -1
        ),
        arguments(
            9
        )
    );
  }
}