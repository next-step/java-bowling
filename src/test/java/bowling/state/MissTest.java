package bowling.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.state.Miss;
import bowling.domain.state.SecondGutter;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class MissTest {
  @Test
  @DisplayName("play 확인")
  public void play() {
    assertThatThrownBy(() -> {
      new Miss(8).play(3);
    }).isInstanceOf(IllegalArgumentException.class);
  }
}
