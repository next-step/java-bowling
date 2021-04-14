package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.state.FirstGutter;
import bowling.domain.state.Hit;
import bowling.domain.state.Ready;
import bowling.domain.state.Strike;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ReadyTest {

  @Test
  @DisplayName("play 확인")
  public void play() {
    assertThat(new Ready().play(10)).isInstanceOf(Strike.class);
    assertThat(new Ready().play(0)).isInstanceOf(FirstGutter.class);
    assertThat(new Ready().play(8)).isInstanceOf(Hit.class);
  }
}
