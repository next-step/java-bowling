package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.exception.CannotBowlException;
import org.junit.Test;

public class TrialTest {

  @Test
  public void testBlocked() {
    Trial blocked = Trial.block();
    assertThat(blocked.isPlayed()).isFalse();
    assertThat(blocked.isGutter()).isFalse();
    assertThatThrownBy(() -> blocked.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }

  @Test
  public void testRollTwice() throws CannotBowlException {
    Trial trial = Trial.initialize();

    trial.roll(3);
    assertThat(trial.isPlayed()).isTrue();
    assertThatThrownBy(() -> trial.roll(3))
        .isInstanceOf(CannotBowlException.class);
  }

  @Test
  public void testStrike() throws CannotBowlException {
    Trial trial = Trial.initialize();

    trial.roll(PinCount.STRIKE);
    assertThat(trial.isPlayed()).isTrue();
    assertThat(trial.isStrike()).isTrue();
    assertThat(trial.visualize()).isEqualTo(Trial.STRIKE_SIGN);
  }

  @Test
  public void testGutter() throws CannotBowlException {
    Trial trial = Trial.initialize();

    trial.roll(PinCount.GUTTER);
    assertThat(trial.isPlayed()).isTrue();
    assertThat(trial.isGutter()).isTrue();
    assertThat(trial.visualize()).isEqualTo(Trial.GUTTER_SIGN);
  }

  @Test
  public void testSpare() throws CannotBowlException {
    int notStrike = 3;
    Trial first = Trial.initialize();
    Trial second = Trial.initialize();

    first.roll(notStrike);
    second.roll(PinCount.STRIKE - notStrike);
    assertThat(second.isSpare(first)).isTrue();
  }
}
