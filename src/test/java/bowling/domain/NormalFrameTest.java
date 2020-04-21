package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.exception.CannotBowlException;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class NormalFrameTest {

  @ParameterizedTest
  @CsvSource(value = {"0,6=-|6", "3,5=3|5", "3,0=3|-"}, delimiter = '=')
  public void testMiss(String pins, String visualized) throws CannotBowlException {
    NormalFrame normalFrame = NormalFrame.initialize();
    String[] pinCounts = pins.split(",");

    normalFrame.roll(Integer.parseInt(pinCounts[0]));
    Frame newFrame = normalFrame.roll(Integer.parseInt(pinCounts[1]));

    assertThat(normalFrame.visualize()).isEqualTo(visualized);
    assertThat(newFrame.visualize()).isEqualTo(Trial.NOT_PLAYED_SIGN);
    assertThatThrownBy(() -> normalFrame.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"0,10=-|/", "3,7=3|/"}, delimiter = '=')
  public void testSpare(String pins, String visualized) throws CannotBowlException {
    NormalFrame normalFrame = NormalFrame.initialize();
    String[] pinCounts = pins.split(",");

    normalFrame.roll(Integer.parseInt(pinCounts[0]));
    Frame newFrame = normalFrame.roll(Integer.parseInt(pinCounts[1]));

    assertThat(normalFrame.visualize()).isEqualTo(visualized);
    assertThat(newFrame.visualize()).isEqualTo(Trial.NOT_PLAYED_SIGN);
    assertThatThrownBy(() -> normalFrame.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }

  @Test
  public void testStrike() throws CannotBowlException {
    NormalFrame normalFrame = NormalFrame.initialize();
    Frame newFrame = normalFrame.roll(PinCount.STRIKE);

    assertThat(normalFrame.visualize()).isEqualTo(Trial.STRIKE_SIGN);
    assertThat(newFrame.visualize()).isEqualTo(Trial.NOT_PLAYED_SIGN);
    assertThatThrownBy(() -> normalFrame.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }

  @Test
  public void testFinalRound() throws CannotBowlException {
    NormalFrame normalFrame = NormalFrame.of(Round.of(Round.FINAL_ROUND - Round.ROUND_UNIT));
    Frame newFrame = normalFrame.roll(PinCount.STRIKE);

    assertThat(newFrame instanceof FinalFrame).isTrue();
  }
}
