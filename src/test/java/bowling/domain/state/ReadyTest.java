package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {

  @Test
  @DisplayName("Ready에서 Strike를 쳤을 때 확인")
  void playStrike() {
    Ready ready = new Ready();
    State state = ready.play(new Pin(10));
    assertThat(state).isInstanceOf(Strike.class);
  }

  @Test
  @DisplayName("Ready에서 첫번째 투구를 했을 때")
  void playFirstBowl() {
    Ready ready = new Ready();
    State state = ready.play(new Pin(3));
    assertThat(state).isInstanceOf(FirstBowl.class);
  }
}
