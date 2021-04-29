package bowling_refactor;

import bowling_refactor.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinTest {

    @Test
    @DisplayName("핀의개수가 0개보다 작은 경우")
    void lessThanZero() {
        assertThatThrownBy(() -> {
            Pin pin = Pin.ofStart();
            pin.fallDown(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("핀의개수가 10개보다 큰경우")
    void moreThanTen() {
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
