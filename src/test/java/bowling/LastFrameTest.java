package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LastFrameTest {

  Frame lastFrame;

  @BeforeEach
  void 생성() {
    lastFrame = new LastFrame();
  }

  @Test
  void Miss하면_게임이_끝이난다() {
    assertThat(lastFrame.bowl(new Pins(5)).bowl(new Pins(3)).isGameEnd()).isTrue();
  }

  @Test
  void 스트라이크하면_세번_투구하고_끝이난다() {
    Frame currentFrame = new LastFrame().bowl(new Pins(10)).bowl(new Pins(10));

    assertThat(currentFrame.isGameEnd()).isFalse();
    assertThat(currentFrame.bowl(new Pins(10)).isGameEnd()).isTrue();
  }

  @Test
  void 스패어하면_세번_투구하고_끝이난다() {
    Frame currentFrame = new LastFrame().bowl(new Pins(5)).bowl(new Pins(5));
    assertThat(currentFrame.isGameEnd()).isFalse();
    assertThat(currentFrame.bowl(new Pins(10)).isGameEnd()).isTrue();
  }

  @Test
  void 스트라이크_desc를_확인한다() {
    assertThat(new LastFrame().bowl(new Pins(10)).desc()).isEqualTo("X");
    assertThat(new LastFrame().bowl(new Pins(10)).bowl(new Pins(10)).desc()).isEqualTo("X|X");
    assertThat(new LastFrame().bowl(new Pins(10)).bowl(new Pins(10)).bowl(new Pins(10)).desc())
        .isEqualTo("X|X|X");
  }

  @Test
  void 스페어_desc를_확인한다() {
    assertThat(new LastFrame().bowl(new Pins(5)).desc()).isEqualTo("5");
    assertThat(new LastFrame().bowl(new Pins(5)).bowl(new Pins(5)).desc()).isEqualTo("5|/");
    assertThat(new LastFrame().bowl(new Pins(5)).bowl(new Pins(5)).bowl(new Pins(5)).desc())
        .isEqualTo("5|/|5");
  }

  @Test
  void 미스_desc를_확인한다() {
    assertThat(new LastFrame().bowl(new Pins(5)).desc()).isEqualTo("5");
    assertThat(new LastFrame().bowl(new Pins(5)).bowl(new Pins(4)).desc()).isEqualTo("5|4");
  }


}