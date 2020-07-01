package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class KnockDownNumberTest {

  @ParameterizedTest
  @CsvSource({
      "-1",
      "11"
  })
  void init_핀범위초과(int knockDownNumber) {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
      new KnockDownNumber(knockDownNumber);
    });
  }

  @ParameterizedTest
  @MethodSource("provideKockDownNumWtihExpected")
  void getIntValue(int number, int expected) {
    KnockDownNumber knockDownNumber = new KnockDownNumber(number);

    assertThat(knockDownNumber.getIntValue()).isEqualTo(expected);
  }

  static Stream<Arguments> provideKockDownNumWtihExpected() {
    return Stream.of(
        arguments(
            0, 0
        ),
        arguments(
            10, 10
        ),
        arguments(
            5, 5
        )
    );
  }

  @Test
  void getIntValue_whenEmpty() {
    KnockDownNumber knockDownNumber = new KnockDownNumber();

    assertThat(knockDownNumber.getIntValue()).isEqualTo(0);
  }

  @Test
  void isNull() {
    KnockDownNumber knockDownNumber = new KnockDownNumber();

    assertThat(knockDownNumber.isNull()).isTrue();
  }

  @Test
  void isNull_Notnull() {
    KnockDownNumber knockDownNumber = new KnockDownNumber(0);

    assertThat(knockDownNumber.isNull()).isFalse();
  }
}