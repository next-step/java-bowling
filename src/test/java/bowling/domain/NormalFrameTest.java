package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NormalFrameTest {

  @Test
  void init() {
    NormalFrame normalFrame = new NormalFrame();
    assertEquals(normalFrame.number(), 1);
  }

  @Test
  void next() {
    NormalFrame normalFrame = new NormalFrame();
    NormalFrame nextFrame = normalFrame.next();
    assertEquals(nextFrame.number(), 2);
  }

  @Test
  void play() {
    NormalFrame normalFrame = new NormalFrame();
    normalFrame.play(4);
    assertEquals(normalFrame.lastPitching().leftPins(), 6);
  }

  @Test
  void strike() {
    NormalFrame normalFrame = new NormalFrame();
    assertThat(normalFrame.play(10)).containsExactly(10);
  }

  @Test
  void spare() {
    NormalFrame normalFrame = new NormalFrame();
    normalFrame.play(5);
    assertThat(normalFrame.play(5)).containsExactly(5, 5);
  }

  @Test
  void miss() {
    NormalFrame normalFrame = new NormalFrame();
    assertThat(normalFrame.play(5)).containsExactly(5);
    assertThat(normalFrame.play(4)).containsExactly(5, 4);
  }
}