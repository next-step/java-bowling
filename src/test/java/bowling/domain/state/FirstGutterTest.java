package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FirstGutterTest {
  @Test
  @DisplayName("첫번째 거터에서 0개를 쳤을 경우")
  void playSecondGutter() {
    FirstGutter firstGutter = new FirstGutter();
    State state = firstGutter.play(new Pin(0));
    assertThat(state).isInstanceOf(SecondGutter.class);
  }

  @Test
  @DisplayName("첫번째 거터에서 miss를 쳤을 경우")
  void playMiss() {
    FirstGutter firstGutter = new FirstGutter();
    State state = firstGutter.play(new Pin(4));
    assertThat(state).isInstanceOf(Miss.class);
  }

  @Test
  @DisplayName("첫번째 거터에서 spare를 쳤을 경우")
  void playSpare() {
    FirstGutter firstGutter = new FirstGutter();
    State state = firstGutter.play(new Pin(10));
    assertThat(state).isInstanceOf(Spare.class);
  }
}
