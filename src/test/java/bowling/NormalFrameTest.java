package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.state.Ready;
import bowling.domain.state.Strike;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class NormalFrameTest {

  @Test
  @DisplayName("잘못된 핀 개수 확인")
  public void validatePinCount() {
    assertThatThrownBy(() -> {
      NormalFrame normalFrame = NormalFrame.createFirst();
      normalFrame.play(11);
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("첫 프레임에 스트라이크를 칠 경우")
  public void play() {
    Frame firstFrame = NormalFrame.createFirst();
    firstFrame.play(10);

    Frame nextFrame = firstFrame.next();
    assertThat(nextFrame.getPlayCount()).isEqualTo(1);
    assertThat(nextFrame.getStatus()).isInstanceOf(Strike.class);
  }
}
