package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PinTest {

  @Test
  @DisplayName("[Pin] Pin 생성 테스트")
  void create_pin_test() {
    Pin pin = new Pin(10);
    assertThat(pin).isEqualTo(new Pin(10));
  }

  @Test
  @DisplayName("[Pin] Pin 생성시 0~10 사이 값이 아닌 경우 예외 발생")
  void validate_hit_pin_test() {
    assertThatThrownBy(() -> new Pin(11))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
