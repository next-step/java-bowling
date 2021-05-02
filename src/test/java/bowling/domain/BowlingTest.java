package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BowlingTest {


  @Test
  void play() {
    Bowling bowling = new Bowling(new Player("cyr"));
    assertThat(bowling.play(4).get(0)).contains("4");
    assertThat(bowling.play(6).get(0)).contains("4|/");
    assertThat(bowling.play(10).get(1)).contains("X");
  }
}