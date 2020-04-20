package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.exception.CannotBowlException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FinalFrameTest {

  @ParameterizedTest
  @CsvSource(value = {"0,6=-|6", "3,5=3|5", "3,0=3|-"}, delimiter = '=')
  public void testMiss(String pins, String visualized) throws CannotBowlException {
    FinalFrame finalFrame = new FinalFrame();
    String[] pinCounts = pins.split(",");
    finalFrame.roll(Integer.parseInt(pinCounts[0]));
    finalFrame.roll(Integer.parseInt(pinCounts[1]));

    assertThat(finalFrame.isEnd()).isTrue();
    assertThat(finalFrame.visualize()).isEqualTo(visualized);
    assertThatThrownBy(() -> finalFrame.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }

  @ParameterizedTest
  @CsvSource(
      value = {"3,7,0=3|/|-", "3,7,5=3|/|5", "3,7,10=3|/|X", "0,10,0=-|/|-", "0,10,5=-|/|5"},
      delimiter = '='
  )
  public void testSpare(String pins, String visualized) throws CannotBowlException {
    FinalFrame finalFrame = new FinalFrame();
    String[] pinCounts = pins.split(",");
    finalFrame.roll(Integer.parseInt(pinCounts[0]));
    finalFrame.roll(Integer.parseInt(pinCounts[1]));
    finalFrame.roll(Integer.parseInt(pinCounts[2]));

    assertThat(finalFrame.isEnd()).isTrue();
    assertThat(finalFrame.visualize()).isEqualTo(visualized);
    assertThatThrownBy(() -> finalFrame.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }


  @ParameterizedTest
  @CsvSource(
      value = {"10,7,0=X|7|-", "10,5,5=X|5|/", "10,3,4=X|3|4", "10,10,0=X|X|-", "10,10,10=X|X|X"},
      delimiter = '='
  )
  public void testStrike(String pins, String visualized) throws CannotBowlException {
    FinalFrame finalFrame = new FinalFrame();
    String[] pinCounts = pins.split(",");
    finalFrame.roll(Integer.parseInt(pinCounts[0]));
    finalFrame.roll(Integer.parseInt(pinCounts[1]));
    finalFrame.roll(Integer.parseInt(pinCounts[2]));

    assertThat(finalFrame.isEnd()).isTrue();
    assertThat(finalFrame.visualize()).isEqualTo(visualized);
    assertThatThrownBy(() -> finalFrame.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }
}
