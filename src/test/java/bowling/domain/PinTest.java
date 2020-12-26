package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinTest {

    @Test
    @DisplayName("Pin 생성후 객체 확인")
    public void pin_normalCount_isEqualTo() {
        assertThat(Pin.from(5)).isEqualTo(Pin.from(5));
    }


    @Test
    @DisplayName("pinCount 가 음수 일 경우  IllegalArgumentException 발생")
    public void pin_minimumCount_throwException() {
        assertThatThrownBy(() -> Pin.from(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Pin.MIN_PIN_SIZE_ERROR);
    }

    @Test
    @DisplayName("pinCount 가 10개를 초과 할 경우  IllegalArgumentException 발생")
    public void pin_overCount_throwException() {
        assertThatThrownBy(() -> Pin.from(11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Pin.MAX_PIN_SIZE_ERROR);
    }
}
