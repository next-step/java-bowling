package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FrameTest {

  @Test
  void strike_frame() {
    frame_rolls(true, 10);
  }

  @Test
  void pitching_frame() {
    frame_rolls(false, 8);
  }

  @Test
  void gutter_frame() {
    frame_rolls(false, 0);
  }

  @Test
  void spare_frame() {
    frame_rolls(true, 8, 2);
  }

  @Test
  void open_frame() {
    frame_rolls(true, 8, 1);
  }

  @Test
  void open_with_gutter_frame() {
    frame_rolls(true, 8, 0);
  }

  private void frame_rolls(boolean expected, int... pins) {
    Frame frame = new Frame(1);
    for (int pin : pins) {
      frame.roll(pin);
    }

    assertThat(frame.isDone()).isEqualTo(expected);
  }

  @Test
  void next_frame() {
    Frame strike = new Frame(1);
    strike.roll(10);

    assertThat(strike).isNotEqualTo(strike.roll(10));
  }

  @Test
  void strike_score() {
    Frame strike = new Frame(1);
    strike.roll(10);

    assertThat(strike.score(null)).isNull();

    Frame spare = strike.roll(8);
    assertThat(strike.score(null)).isNull();

    spare.roll(2);
    assertThat(strike.score(null)).isEqualTo(new Score(20));
  }

  @Test
  void spare_score() {
    Frame spare = new Frame(1);
    spare.roll(8);
    assertThat(spare.score(null)).isNull();

    spare.roll(2);
    assertThat(spare.score(null)).isNull();

    Frame pitching = spare.roll(8);
    assertThat(spare.score(null)).isEqualTo(new Score(18));

    pitching.roll(1);
    assertThat(spare.score(null)).isEqualTo(new Score(18));
  }

  @Test
  void open_score() {
    Frame open = new Frame(1);
    open.roll(8);
    assertThat(open.score(null)).isNull();

    open.roll(1);
    assertThat(open.score(null)).isEqualTo(new Score(9));
  }
}