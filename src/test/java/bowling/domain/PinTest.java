package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class PinTest {

  @ParameterizedTest
  @ValueSource(ints = {-1, 15})
  @DisplayName("친 핀의 개수는 0개 이상 10개 이하여야 한다.")
  public void validatePinCount(int number) {
    assertThatThrownBy(() -> {
      new Pin(number);
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("게임 종료된 핀 확인")
  void isEnd() {
    Pin pin = new Pin(10);
    assertThat(pin.isEnd()).isTrue();
  }
}
