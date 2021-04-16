package bowling.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.PinCount;
import bowling.domain.state.Spare;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class SpareTest {

  @Test
  @DisplayName("play 확인")
  public void play() {
    assertThatThrownBy(() -> {
      new Spare().play(new PinCount(3));
    }).isInstanceOf(IllegalArgumentException.class);
  }
}
