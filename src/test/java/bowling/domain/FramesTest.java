package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
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
    assertThat(frames.play(4).get(0)).contains("4");
    assertThat(frames.play(6).get(0)).contains("4|/");
    assertThat(frames.play(10).get(1)).contains("X");
  }
}