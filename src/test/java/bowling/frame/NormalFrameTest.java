package bowling.frame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NormalFrameTest {
  @Test
  void strike() {
    NormalFrame normalFrame = new NormalFrame(1);
    normalFrame.play(10);
    assertThat(normalFrame.getScore()).isEqualTo("X");
  }

  @Test
  void spare() {
    Frame frame = new NormalFrame(1);
    frame.play(4);
    frame.play(6);

    assertThat(frame.getScore()).isEqualTo("4|/");
  }

  @Test
  void miss() {
    Frame frame = new NormalFrame(1);
    frame.play(4);
    frame.play(5);

    assertThat(frame.getScore()).isEqualTo("4|9");
  }
}