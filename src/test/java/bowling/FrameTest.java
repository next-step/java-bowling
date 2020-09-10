package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FrameTest {

  @Test
  void strike_frame() {
    Frame strike = new Frame();
    strike.roll(10);

    assertThat(strike.isDone()).isTrue();
  }

  @Test
  void pitching_frame() {
    Frame pitching = new Frame();
    pitching.roll(8);

    assertThat(pitching.isDone()).isFalse();
  }

  @Test
  void gutter_frame() {
    Frame gutter = new Frame();
    gutter.roll(0);

    assertThat(gutter.isDone()).isFalse();
  }

  @Test
  void spare_frame() {
    Frame pitching = new Frame();
    pitching.roll(8);
    pitching.roll(2);

    assertThat(pitching.isDone()).isTrue();
  }

  @Test
  void open_frame() {
    Frame pitching = new Frame();
    pitching.roll(8);
    pitching.roll(1);

    assertThat(pitching.isDone()).isTrue();
  }

  @Test
  void open_with_gutter_frame() {
    Frame pitching = new Frame();
    pitching.roll(8);
    pitching.roll(0);

    assertThat(pitching.isDone()).isTrue();
  }
}
