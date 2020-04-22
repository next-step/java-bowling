package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.exception.CannotBowlException;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RegularResultTest {

  @Test
  public void testStrike() throws CannotBowlException {
    RegularResult regularResult = new RegularResult();

    regularResult.roll(PinCount.STRIKE);
    assertThat(regularResult.getState()).isEqualTo(FrameState.STRIKE);
    assertThat(regularResult.isFinished()).isTrue();
    assertThatThrownBy(() -> regularResult.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"3,7=3|/", "0,10=-|/", "5,5=5|/"}, delimiter = '=')
  public void testSpare(String pins, String visualized) throws CannotBowlException {
    RegularResult regularResult = new RegularResult();
    String[] pinCounts = pins.split(",");

    regularResult.roll(Integer.parseInt(pinCounts[0]));
    assertThat(regularResult.getState()).isEqualTo(FrameState.IN_PROGRESS);
    assertThat(regularResult.isFinished()).isFalse();

    regularResult.roll(Integer.parseInt(pinCounts[1]));
    assertThat(regularResult.getState()).isEqualTo(FrameState.SPARE);
    assertThat(regularResult.isFinished()).isTrue();
    assertThatThrownBy(() -> regularResult.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"3,5=3|5", "0,8=-|8", "5,0=5|-"}, delimiter = '=')
  public void testMiss(String pins, String visualized) throws CannotBowlException {
    RegularResult regularResult = new RegularResult();
    String[] pinCounts = pins.split(",");

    regularResult.roll(Integer.parseInt(pinCounts[0]));
    assertThat(regularResult.getState()).isEqualTo(FrameState.IN_PROGRESS);
    assertThat(regularResult.isFinished()).isFalse();

    regularResult.roll(Integer.parseInt(pinCounts[1]));
    assertThat(regularResult.getState()).isEqualTo(FrameState.MISS);
    assertThat(regularResult.isFinished()).isTrue();
    assertThatThrownBy(() -> regularResult.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"3,8", "1,10", "5,8"})
  public void testInvalidSecondTrial(String firstTrial, String secondTrial)
      throws CannotBowlException
  {
    RegularResult regularResult = new RegularResult();

    regularResult.roll(Integer.parseInt(firstTrial));
    assertThatThrownBy(() -> regularResult.roll(Integer.parseInt(secondTrial)))
        .isInstanceOf(CannotBowlException.class);
  }
}
