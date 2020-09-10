package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FrameTest {

  @Test
  void strike_frame() {
    frame_roll(true, 10);
  }

  @Test
  void pitching_frame() {
    frame_roll(false, 8);
  }

  @Test
  void gutter_frame() {
    frame_roll(false, 0);
  }

  @Test
  void spare_frame() {
    frame_roll(true, 8, 2);
  }

  @Test
  void open_frame() {
    frame_roll(true, 8, 1);
  }

  @Test
  void open_with_gutter_frame() {
    frame_roll(true, 8, 0);
  }

  private void frame_roll(boolean expected, int... pins) {
    Frame strike = new Frame(1);
    for (int pin : pins) {
      strike.roll(pin);
    }

    assertThat(strike.isDone()).isEqualTo(expected);
  }

  @Test
  void next_frame() {
    Frame strike = new Frame(1);
    strike.roll(10);

    assertThat(strike).isNotEqualTo(strike.roll(10));
  }
}
