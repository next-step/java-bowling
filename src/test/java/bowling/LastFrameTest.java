package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LastFrameTest {

  @Test
  void 스트라이크_세번_하면_끝이난다() {
    assertThat(new LastFrame().roll(10).roll(10).roll(10).isGameEnd()).isEqualTo(true);
  }

  @Test
  void 스페어_그리고_보너스샷을하면_끝이난다() {
    assertThat(new LastFrame().roll(2).roll(8).roll(10).isGameEnd()).isEqualTo(true);
  }

  @Test
  void 스페어나_스크라이크_못하면_보너스_없다() {
    assertThat(new LastFrame().roll(2).roll(7).isGameEnd()).isEqualTo(true);
  }

  @Test
  void 구프레임_뒤에는_LastFrame이_리턴된다() {
    Frame currentFrame = new NormalFrame(9).roll(10);
    Frame nextFrame = currentFrame.nextFrame();
    assertThat(currentFrame.score()).isEqualTo(-1);

    nextFrame.roll(10);
    assertThat(currentFrame.score()).isEqualTo(-1);

    nextFrame.roll(10);
    assertThat(currentFrame.score()).isEqualTo(30);
  }

  @Test
  void 마지막_프레임결과를_리턴한다_strike() {
    Frame lastFrame = new LastFrame();
    lastFrame.roll(10);
    lastFrame.roll(10);
    lastFrame.roll(10);

    assertThat(lastFrame.score()).isEqualTo(30);
  }

  @Test
  void 마지막_프레임결과를_리턴한다_spare() {
    Frame lastFrame = new LastFrame();
    lastFrame.roll(5);
    lastFrame.roll(5);
    lastFrame.roll(10);

    assertThat(lastFrame.score()).isEqualTo(20);
  }

  @Test
  void 마지막_프레임결과를_리턴한다_Miss() {
    Frame lastFrame = new LastFrame();
    lastFrame.roll(3);
    lastFrame.roll(5);

    assertThat(lastFrame.score()).isEqualTo(8);
  }
}
