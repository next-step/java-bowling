package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ScoreTest {

  @Test
  @DisplayName("점수 계산")
  public void calculate() {
    Frame frame = NormalFrame.createFirst();
    frame.play(new PinCount(10));
    frame.play(new PinCount(8));
    frame.play(new PinCount(2));
    assertThat(frame.getScore()).isEqualTo(new Score(20));

  }

}
