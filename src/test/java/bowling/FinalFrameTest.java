package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.PinCount;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.state.Ready;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class FinalFrameTest {

  @Test
  @DisplayName("9번째 프레임에 스트라이크를 치면 다음 판은 10번째 프레임이다.")
  public void play() {
    Frame frame = new NormalFrame(9, new Ready());
    frame.play(new PinCount(10));
    assertThat(frame.nextFrame()).isInstanceOf(FinalFrame.class);
  }

  @Test
  @DisplayName("마지막에 스트라이크나 스페어를 친 경우 한 번 더 투구 기회가 있다.")
  public void bonus() {
    FinalFrame frame = new FinalFrame();
    frame.play(new PinCount(10));
    frame.play(new PinCount(6));
    frame.play(new PinCount(4));
    assertThat(frame.getStatesSize()).isEqualTo(3);
  }


  @Test
  @DisplayName("마지막에 스트라이크나 스페어를 친 경우 아닌 경우 한 번 더 투구 기회가 없다.")
  public void notBonus() {
    assertThatThrownBy(() -> {
      Frame frame = new FinalFrame();
      frame.play(new PinCount(8));
      frame.play(new PinCount(1));
      frame.play(new PinCount(5));
    }).isInstanceOf(IllegalArgumentException.class);
  }

}
