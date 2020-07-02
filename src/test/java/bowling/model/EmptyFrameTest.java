package bowling.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmptyFrameTest {

  @Test
  void roll() {
    assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> {
      new EmptyFrame().roll(0);
    });
  }

  @Test
  void next() {
    assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> {
      new EmptyFrame().next();
    });
  }

  @Test
  void getCurrentScore() {
    assertThat(new EmptyFrame().getFirstKnockDownNumber()).isEqualTo(0);
  }

  @Test
  void isOver() {
    assertThat(new EmptyFrame().isOver()).isTrue();
  }

  @Test
  void getPins() {
    assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> {
      new EmptyFrame().getPins();
    });
  }

  @Test
  void getFrameStatus() {
    assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> {
      new EmptyFrame().getFrameStatus();
    });
  }

  @Test
  void getScoreBy() {
    assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> {
      new EmptyFrame().getScoreBy(null);
    });
  }
}