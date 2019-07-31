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

  @Test
  void Normal프레임에서_addAdditionalScore을_처리해준다_Strike() {
    Frame nineFrame = new NormalFrame(9);
    Frame lastFrame = nineFrame.bowl(new Pins(10));

    assertThat(nineFrame.getScore()).isEqualTo(Score.noFinishScore());

    lastFrame.bowl(new Pins(4));
    assertThat(nineFrame.getScore()).isEqualTo(Score.noFinishScore());

    lastFrame.bowl(new Pins(4));
    assertThat(nineFrame.getScore()).isEqualTo(new Score(18, 0));
  }

  @Test
  void Normal프레임에서_addAdditionalScore을_처리해준다_Spare() {
    Frame nineFrame = new NormalFrame(9);
    Frame lastFrame = nineFrame.bowl(new Pins(3)).bowl(new Pins(7));

    assertThat(nineFrame.getScore()).isEqualTo(Score.noFinishScore());

    lastFrame.bowl(new Pins(4));
    assertThat(nineFrame.getScore()).isEqualTo(new Score(14, 0));
  }

  @Test
  void LastFrame의_점수를_구한다_STRIKE_STRIKE_STRIKE() {
    Frame lastFrame = new LastFrame();
    Pins strikePins = new Pins(10);
    Score score = lastFrame.bowl(strikePins).bowl(strikePins).bowl(strikePins).getScore();

    assertThat(score).isEqualTo(new Score(30, 0));
  }

  @Test
  void LastFrame의_점수를_구한다_SPARE_AND_BONUS() {
    Frame lastFrame = new LastFrame();
    Score score = lastFrame.bowl(new Pins(4)).bowl(new Pins(6)).bowl(new Pins(8)).getScore();

    assertThat(score).isEqualTo(new Score(18, 0));
  }

  @Test
  void LastFrame의_점수를_구한다_MISS() {
    Frame lastFrame = new LastFrame();
    Score score = lastFrame.bowl(new Pins(3)).bowl(new Pins(6)).getScore();

    assertThat(score).isEqualTo(new Score(9, 0));
  }

}