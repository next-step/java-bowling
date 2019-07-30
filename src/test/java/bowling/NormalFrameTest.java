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
}