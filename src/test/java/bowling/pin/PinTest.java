package bowling.pin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PinTest {

  public static Pin from(int first) {
    return Pin.from(first);
  }

  @DisplayName("쓰러진핀 수를 입력해서 객체를 생성 한다.")
  @Test
  void fallenPins() {
    Pin pin = Pin.from(10);
    assertThat(pin).isEqualTo(Pin.from(10));
  }

  @DisplayName("입력하는 핀의 값이 0보다 작거나 10보다 클 수 없다.")
  @ParameterizedTest
  @ValueSource(ints = {-1, 11})
  void validationCreatePins(int pin) {
    assertThatThrownBy(() -> Pin.from(pin))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @DisplayName("공을 두번 굴려서 최종적으로 쓰러트린 핀의 수를 반환한다.")
  @Test
  void totalPin() {
    Pin first = Pin.from(5);
    Pin second = Pin.from(5);
    assertThat(second.totalDownPin(first)).isEqualTo(Pin.from(10));
  }

  @DisplayName("공을 두번 굴려서 최종적으로 쓰러트린 핀의 수가 10을 넘을 수 없는지 확인한다.")
  @Test
  void validationTotalPin() {
    Pin first = Pin.from(5);
    Pin second = Pin.from(6);

    assertThatThrownBy(() -> second.totalDownPin(first))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @DisplayName("핀의 수를 반환한다.")
  @Test
  void pinCount() {
    Pin first = Pin.from(5);
    Pin second = Pin.from(5);
    assertThat(first.pinCount()).isEqualTo(second.pinCount());
  }
}