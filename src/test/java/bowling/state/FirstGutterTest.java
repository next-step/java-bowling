package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.state.FirstGutter;
import bowling.domain.state.Hit;
import bowling.domain.state.Miss;
import bowling.domain.state.SecondGutter;
import bowling.domain.state.Spare;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class FirstGutterTest {

  @Test
  @DisplayName("play 확인")
  public void play() {
    assertThat(new FirstGutter().play(0)).isInstanceOf(SecondGutter.class);
    assertThat(new FirstGutter().play(5)).isInstanceOf(Miss.class);
    assertThat(new FirstGutter().play(10)).isInstanceOf(Spare.class);
  }
}
