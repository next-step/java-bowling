package bowling.frame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NormalFrameTest {
  @Test
  void strike() {
    NormalFrame normalFrame = new NormalFrame();
    normalFrame.play(10, 1);
    assertThat(normalFrame.getScore()).isEqualTo("X");
  }

  @Test
  void spare() {
    Frame frame = new NormalFrame();
    frame.play(4,1);
    frame.play(6,1);

    assertThat(frame.getScore()).isEqualTo("4|/");
  }

  @Test
  void miss() {
    Frame frame = new NormalFrame();
    frame.play(4,1);
    frame.play(5,1);

    assertThat(frame.getScore()).isEqualTo("4|9");
  }
}