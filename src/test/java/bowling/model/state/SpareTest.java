package bowling.model.state;

import bowling.model.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class SpareTest {

    @Test
    @DisplayName("스페어는 3번 던지면 예외가 발생한다.")
    public void bowl() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> {
                    Ready ready = new Ready();
                    ready.bowl(Pin.of(8)).bowl(Pin.of(2)).bowl(Pin.of(1));
                });
    }
}
