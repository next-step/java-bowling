package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.frame.NormalFrame;
import bowling.domain.state.FirstGutter;
import bowling.domain.state.Miss;
import bowling.domain.state.SecondGutter;
import bowling.domain.state.Spare;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class SecondGutterTest {

  @Test
  @DisplayName("play 확인")
  public void play() {
    assertThatThrownBy(() -> {
      new SecondGutter().play(3);
    }).isInstanceOf(IllegalArgumentException.class);
  }
}
