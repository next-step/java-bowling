package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.state.Ready;
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
  @DisplayName("게임 진행 횟수 확인")
  public void validatePlayCount() {
    assertThatThrownBy(() -> {
      NormalFrame normalFrame = new NormalFrame(12, new Ready());
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("첫 프레임에 스트라이크를 칠 경우")
  public void play() {
    Frame firstFrame = NormalFrame.createFirst();
    firstFrame.play(10);

    Frame nextFrame = firstFrame.next();
    assertThat(nextFrame.getPlayCount()).isEqualTo(2);
  }

  @Test
  @DisplayName("첫 프레임에 Gutter를 친 경우")
  public void playGutter() {
    Frame firstFrame = NormalFrame.createFirst();
    firstFrame.play(0);
    Frame nextFrame = firstFrame.next();
    assertThat(nextFrame.getPlayCount()).isEqualTo(1);
  }

  @Test
  @DisplayName("첫 판에 핀 3개 친 경우")
  public void play3() {
    Frame firstFrame = NormalFrame.createFirst();
    firstFrame.play(3);
    Frame nextFrame = firstFrame.next();
    assertThat(nextFrame.getPlayCount()).isEqualTo(1);
  }

}
