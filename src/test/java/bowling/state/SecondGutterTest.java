package bowling.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.PinCount;
import bowling.domain.state.SecondGutter;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class SecondGutterTest {

  @Test
  @DisplayName("play 확인")
  public void play() {
    assertThatThrownBy(() -> {
      new SecondGutter().play(new PinCount(3));
    }).isInstanceOf(IllegalArgumentException.class);
  }
}
