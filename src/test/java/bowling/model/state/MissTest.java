package bowling.model.state;

import bowling.model.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class MissTest {

    @Test
    @DisplayName("미스는 3번 던지면 예외가 발생한다.")
    public void bowl() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> {
                    Ready ready = new Ready();
                    ready.bowl(Pin.of(8)).bowl(Pin.of(1)).bowl(Pin.of(1));
                });
    }
}
