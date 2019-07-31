package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

  Frame frame;

  @BeforeEach
  void 생성() {
    frame = NormalFrame.first();
  }

  @Test
  void 스트라이크를_Bowl하면_다음프레임이_리턴된다() {
    assertThat(frame.bowl(new Pins(10)).frameNo()).isEqualTo(2);
  }

  @Test
  void 스트라이크가아닌_Bowl하면_현재프레임이_리턴된다() {
    assertThat(frame.bowl(new Pins(9)).frameNo()).isEqualTo(1);
  }

  @Test
  void 스트라이크제외_두번_Bowl하면_다음프레임이_리턴된다() {
    Frame currentframe = this.frame.bowl(new Pins(5)).bowl(new Pins(5));
    assertThat(currentframe.frameNo()).isEqualTo(2);
  }

  @Test
  void 구프레임_다음에는_LastFrame이_리턴된다() {
    Frame nineFrame = new NormalFrame(9);

    assertThat(nineFrame.bowl(new Pins(10))).isInstanceOf(LastFrame.class);
  }

  @Test
  void Miss인_프레임의_Score를_확인할수있다() {
    frame.bowl(new Pins(5));
    frame.bowl(new Pins(3));

    assertThat(frame.getScore()).isEqualTo(new Score(8, 0));
  }

  @Test
  void Strike의_점수계산이_완료되지않으면_noFinishScore을_리턴한다() {
    frame.bowl(new Pins(10));
    assertThat(frame.getScore()).isEqualTo(Score.noFinishScore());
  }

  @Test
  void Spare의_점수계산이_완료되지않으면_noFinishScore을_리턴한다() {
    frame.bowl(new Pins(6)).bowl(new Pins(4));
    assertThat(frame.getScore()).isEqualTo(Score.noFinishScore());
  }
}