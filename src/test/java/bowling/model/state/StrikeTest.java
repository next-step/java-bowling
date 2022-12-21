package bowling.model.state;

import bowling.model.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class StrikeTest {

    @Test
    @DisplayName("스트라이크는 2번 던지면 예외가 발생한다.")
    public void bowl() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> {
                    Ready ready = new Ready();
                    ready.bowl(Pin.of(10)).bowl(Pin.of(2));
                });
    }
}
