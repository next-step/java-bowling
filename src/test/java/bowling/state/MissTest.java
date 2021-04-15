package bowling.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.state.Miss;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MissTest {

  @Test
  @DisplayName("play 확인")
  public void play() {
    assertThatThrownBy(() -> {
      new Miss(8).play(3);
    }).isInstanceOf(IllegalArgumentException.class);
  }


  @ParameterizedTest
  @ValueSource(ints = {-1, 15})
  @DisplayName("친 핀의 개수는 0개 이상 10개 이하여야 한다.")
  public void validatePinCount(int number) {
    assertThatThrownBy(() -> {
      new Miss(number);
    }).isInstanceOf(IllegalArgumentException.class);
  }
}
