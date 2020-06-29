package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnockedDownPinsTest {

  public static KnockedDownPins knockedDownPins0_0;
  public static KnockedDownPins knockedDownPins0_1;
  public static KnockedDownPins knockedDownPins2_5;
  public static KnockedDownPins knockedDownPins5_5;
  public static KnockedDownPins knockedDownPins0_10;
  public static KnockedDownPins knockedDownPins_Strike;

  static {
    initKnockedDownPinsTest();
  }

  @AfterEach
  void tearDown() {
    initKnockedDownPinsTest();
  }

  private static void initKnockedDownPinsTest() {
    knockedDownPins0_0 = KnockedDownPins.getBuilder(0)
        .secondKnockDownNum(0)
        .build();

    knockedDownPins2_5 = KnockedDownPins.getBuilder(2)
        .secondKnockDownNum(5)
        .build();

    knockedDownPins5_5 = KnockedDownPins.getBuilder(5)
        .secondKnockDownNum(5)
        .build();

    knockedDownPins0_10 = KnockedDownPins.getBuilder(0)
        .secondKnockDownNum(10)
        .build();

    knockedDownPins0_1 = KnockedDownPins.getBuilder(0)
        .secondKnockDownNum(1)
        .build();

    knockedDownPins_Strike = KnockedDownPins.getBuilder(10)
        .secondKnockDownNum(0)
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
            knockedDownPins0_0,
            10
        ),
        arguments(
            knockedDownPins0_1,
            9
        ),
        arguments(
            knockedDownPins2_5,
            3
        ),
        arguments(
            knockedDownPins5_5,
            0
        ),
        arguments(
            knockedDownPins0_10,
            0
        ),
        arguments(
            knockedDownPins_Strike,
            0
        )
    );
  }

}