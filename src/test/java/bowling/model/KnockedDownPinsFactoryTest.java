package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnockedDownPinsFactoryTest {

  @ParameterizedTest
  @MethodSource("providePinsWithKnockDownNumber")
  void createInstanceBy(KnockedDownPins knockedDownPins, int knockDownNumber,
      KnockedDownPins expected) {
    assertThat(KnockedDownPinsFactory.createInstanceBy(knockedDownPins, knockDownNumber))
        .isEqualTo(expected);
  }

  static Stream<Arguments> providePinsWithKnockDownNumber() {
    return Stream.of(
        arguments(
            new KnockedDownPins(),
            10,
            KnockedDownPinsTest.knockedDownPins_Strike
        ),
        arguments(
            KnockedDownPins.getBuilder()
                .firstKnockDownNumber(5)
                .build(),
            5,
            KnockedDownPinsTest.knockedDownPins5_5
        ),
        arguments(
            new KnockedDownPins(),
            5,
            KnockedDownPins.getBuilder()
                .firstKnockDownNumber(5)
                .build()
        )
    );
  }
}