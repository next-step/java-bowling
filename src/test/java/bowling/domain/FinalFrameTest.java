package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.exception.CannotBowlException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FinalFrameTest {

  @ParameterizedTest
  @CsvSource(value = {"0,6", "3,5", "3,0"})
  public void testMiss(String firstTrial, String secondTrial) throws CannotBowlException {
    FinalFrame finalFrame = new FinalFrame();
    int first = Integer.parseInt(firstTrial);
    int second = Integer.parseInt(secondTrial);
    int score = first + second;

    finalFrame.roll(first);
    assertThat(finalFrame.calculateScore()).isEqualTo(Score.ofNull());

    finalFrame.roll(second);
    assertThat(finalFrame.isEnd()).isTrue();
    assertThat(finalFrame.calculateScore()).isEqualTo(Score.of(score));
    assertThatThrownBy(() -> finalFrame.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }

  @ParameterizedTest
  @CsvSource(value = {"3,7,0", "3,7,5", "3,7,10", "0,10,0", "0,10,5"})
  public void testSpare(String firstTrial, String secondTrial, String thirdTrial)
      throws CannotBowlException
  {
    FinalFrame finalFrame = new FinalFrame();
    int first = Integer.parseInt(firstTrial);
    int second = Integer.parseInt(secondTrial);
    int third = Integer.parseInt(thirdTrial);
    int score = first + second + third;

    finalFrame.roll(first);
    assertThat(finalFrame.calculateScore()).isEqualTo(Score.ofNull());

    finalFrame.roll(second);
    assertThat(finalFrame.calculateScore()).isEqualTo(Score.ofNull());

    finalFrame.roll(third);
    assertThat(finalFrame.isEnd()).isTrue();
    assertThat(finalFrame.calculateScore()).isEqualTo(Score.of(score));
    assertThatThrownBy(() -> finalFrame.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }


  @ParameterizedTest
  @CsvSource(
      value = {"10,7,0", "10,5,5", "10,3,4", "10,10,0", "10,10,10"},
      delimiter = '='
  )
  public void testStrike(String firstTrial, String secondTrial, String thirdTrial)
      throws CannotBowlException
  {
    FinalFrame finalFrame = new FinalFrame();
    int first = Integer.parseInt(firstTrial);
    int second = Integer.parseInt(secondTrial);
    int third = Integer.parseInt(thirdTrial);
    int score = first + second + third;

    finalFrame.roll(first);
    assertThat(finalFrame.calculateScore()).isEqualTo(Score.ofNull());

    finalFrame.roll(second);
    assertThat(finalFrame.calculateScore()).isEqualTo(Score.ofNull());

    finalFrame.roll(third);
    assertThat(finalFrame.isEnd()).isTrue();
    assertThat(finalFrame.calculateScore()).isEqualTo(Score.of(score));
    assertThatThrownBy(() -> finalFrame.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }
}
