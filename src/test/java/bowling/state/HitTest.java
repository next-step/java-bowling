package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.state.Gutter;
import bowling.domain.state.Hit;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class HitTest {
  @Test
  @DisplayName("play 확인")
  public void play() {
    assertThat(new Hit(8).play(1)).isInstanceOf(Miss.class);
    assertThat(new Hit(7).play(0)).isInstanceOf(Gutter.class);
    assertThat(new Hit(7).play(3)).isInstanceOf(Spare.class);
  }
}
