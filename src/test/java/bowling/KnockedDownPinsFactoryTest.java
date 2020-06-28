package bowling;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnockedDownPinsFactoryTest {

  @ParameterizedTest
  @MethodSource("providePinsWithKnockDownNum")
  void createInstanceBy(KnockedDownPins knockedDownPins, int knockDownNum,
      KnockedDownPins expected) {
    assertThat(KnockedDownPinsFactory.createInstanceBy(knockedDownPins, knockDownNum))
        .isEqualTo(expected);
  }

  static Stream<Arguments> providePinsWithKnockDownNum() {
    return Stream.of(
        arguments(
            new KnockedDownPins(),
            10,
            KnockedDownPinsTest.knockedDownPins_Strike
        ),
        arguments(
            KnockedDownPins.getBuilder(5).build(),
            5,
            KnockedDownPinsTest.knockedDownPins5_5
        ),
        arguments(
            new KnockedDownPins(),
            5,
            KnockedDownPins.getBuilder(5).build()
        )
    );
  }
}