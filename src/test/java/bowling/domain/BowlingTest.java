package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.Bowling;
import org.junit.jupiter.api.Test;

class BowlingTest {

  @Test
  void strike_roll() {
    Bowling first = Bowling.first(10);
    assertThat(first.nextFrame()).isEqualTo(2);
  }

  @Test
  void pitching_roll() {
    Bowling first = Bowling.first(8);
    assertThat(first.nextFrame()).isEqualTo(1);
  }

  @Test
  void gutter_roll() {
    Bowling first = Bowling.first(0);
    assertThat(first.nextFrame()).isEqualTo(1);
  }

  @Test
  void spare_roll() {
    Bowling first = Bowling.first(8);
    first.roll(2);
    assertThat(first.nextFrame()).isEqualTo(2);
  }

  @Test
  void open_roll() {
    Bowling first = Bowling.first(8);
    first.roll(1);
    assertThat(first.nextFrame()).isEqualTo(2);
  }

  @Test
  void open_with_gutter_roll() {
    Bowling first = Bowling.first(8);
    first.roll(0);
    assertThat(first.nextFrame()).isEqualTo(2);
  }

  @Test
  void next_roll() {
    Bowling first = Bowling.first(10);
    first.roll(10);
    assertThat(first.nextFrame()).isEqualTo(3);
  }
}
