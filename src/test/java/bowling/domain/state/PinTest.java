package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinTest {

    @Test
    @DisplayName("핀 객체 비교")
    void equalsToPin() {
        Pin actualPin = new Pin(2);
        Pin expectedPin = new Pin(2);

        boolean same = actualPin.equals(expectedPin);

        assertThat(same).isTrue();
    }

    @Test
    @DisplayName("볼링 핀 예외처리")
    void expectedPin() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Pin(-1));
        assertThatIllegalArgumentException().isThrownBy(() -> new Pin(11));
    }
}
