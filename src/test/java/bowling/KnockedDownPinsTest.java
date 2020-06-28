package bowling;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnockedDownPinsTest {

  public static KnockedDownPins knockedDownPins0_1;

  static {
    initKnockedDownPinsTest();
  }

  @AfterEach
  void tearDown() {
    initKnockedDownPinsTest();
  }

  private static void initKnockedDownPinsTest() {
    knockedDownPins0_1 = KnockedDownPins.getBuilder(0)
        .secondKnockDownNum(1)
        .build();
  }

  @ParameterizedTest
  @MethodSource("provideForRemainingNum")
  void getRemainingNum(KnockedDownPins knockedDownPins, int expected) {
    assertThat(knockedDownPins.getRemainingNum()).isEqualTo(expected);
  }

  static Stream<Arguments> provideForRemainingNum() {
    return Stream.of(
        arguments(
            knockedDownPins0_1,
            9
        )
    );
  }

}