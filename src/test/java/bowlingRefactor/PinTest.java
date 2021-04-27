package bowlingRefactor;

import bowlingRefactor.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinTest {

    @Test
    @DisplayName("핀 검증")
    void pinValidation() {
        assertThatThrownBy(() -> {
            Pin pin = Pin.ofStart();
            pin.fallDown(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("스트라이크 판별")
    void isStrike() {
        Pin pin = Pin.ofStart();
        pin.fallDown(10);
        assertThat(pin.isStrike()).isTrue();
    }
}
