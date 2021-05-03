package bowling.domain;

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
    assertEquals(normalFrame.play(10), Result.STRIKE.getMark());
  }

  @Test
  void spare() {
    NormalFrame normalFrame = new NormalFrame();
    normalFrame.play(5);
    assertEquals(normalFrame.play(5), String.format("%s|%s", 5, Result.SPARE.getMark()));
  }

  @Test
  void miss() {
    NormalFrame normalFrame = new NormalFrame();
    assertEquals(normalFrame.play(5), "5");
    assertEquals(normalFrame.play(4), "5|4");
  }
}