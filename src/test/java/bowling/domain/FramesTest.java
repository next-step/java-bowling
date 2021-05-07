package bowling.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FramesTest {

  @Test
  void create() {
    Frames frames = new Frames();
    assertEquals(frames.size(), 10);
  }

  @Test
  void play() {
    Frames frames = new Frames();
    String score = frames.play(4).get(0).getScore();
    assertEquals(score, "4");
    assertEquals(frames.play(6).get(0).getScore(), "4|/");
    assertEquals(frames.play(10).get(1).getScore(), "X");
  }
}