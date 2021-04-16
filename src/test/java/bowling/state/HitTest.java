package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.PinCount;
import bowling.domain.state.Hit;
import bowling.domain.state.Miss;
import bowling.domain.state.SecondGutter;
import bowling.domain.state.Spare;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class HitTest {

  @Test
  @DisplayName("play 확인")
  public void play() {
    assertThat(new Hit(8).play(new PinCount(1))).isInstanceOf(Miss.class);
    assertThat(new Hit(7).play(new PinCount(0))).isInstanceOf(SecondGutter.class);
    assertThat(new Hit(7).play(new PinCount(3))).isInstanceOf(Spare.class);
  }

  @ParameterizedTest
  @ValueSource(ints = {12, 14})
  @DisplayName("총 친 핀의 개수는 0개 이상 10개 이하여야 한다.")
  public void validateNewPinCount(int number) {
    assertThatThrownBy(() -> {
      new Hit(8).play(new PinCount(number));
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 15})
  @DisplayName("친 핀의 개수는 0개 이상 10개 이하여야 한다.")
  public void validatePinCount(int number) {
    assertThatThrownBy(() -> {
      new Hit(number);
    }).isInstanceOf(IllegalArgumentException.class);
  }

}
