package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.exception.CannotBowlException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PinCountTest {
  @ParameterizedTest
  @ValueSource(ints = {-1, 11})
  public void testIllegalArgument(int pinCount) {
    assertThatThrownBy(() -> new PinCount(pinCount))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"0,10", "1,9", "5,5", "9,1"})
  public void testSpare(String firstTrial, String secondTrial) {
    PinCount first = new PinCount(Integer.parseInt(firstTrial));
    PinCount second = new PinCount(Integer.parseInt(secondTrial));

    assertThat(second.isSpareOf(first)).isTrue();
  }

  @ParameterizedTest
  @CsvSource(value = {"10,0", "3,9", "5,4"})
  public void testInvalidSpare(String firstTrial, String secondTrial) {
    PinCount first = new PinCount(Integer.parseInt(firstTrial));
    PinCount second = new PinCount(Integer.parseInt(secondTrial));

    assertThat(second.isSpareOf(first)).isFalse();
  }

  @ParameterizedTest
  @CsvSource(value = {"10,1", "3,9", "5,6"})
  public void testInvalidSecondTrial(String firstTrial, String secondTrial) {
    PinCount first = new PinCount(Integer.parseInt(firstTrial));
    PinCount second = new PinCount(Integer.parseInt(secondTrial));

    assertThatThrownBy(() -> second.isValidSecondTrialOf(first))
        .isInstanceOf(CannotBowlException.class);
  }
}
