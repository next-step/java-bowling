package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PinTest {

    @DisplayName("전부 쓰러진 경우")
    @Test
    void isAllClear() {
        Pin pin = new Pin(10, 10);
        assertThat(pin.isAllClear()).isTrue();
    }

    @Test
    void leftPins() {
        Pin pin = new Pin(10, 5);
        assertThat(pin.leftPins()).isEqualTo(10 - 5);
        pin = new Pin(10, 1);
        assertThat(pin.leftPins()).isEqualTo(10 - 1);
        pin = new Pin(10, 3);
        assertThat(pin.leftPins()).isEqualTo(10 - 3);
        pin = new Pin(10, 4);
        assertThat(pin.leftPins()).isEqualTo(10 - 4);
        pin = new Pin(10, 7);
        assertThat(pin.leftPins()).isEqualTo(10 - 7);
        pin = new Pin(5, 1);
        assertThat(pin.leftPins()).isEqualTo(5 - 1);
    }

    @Test
    void isGutter() {
        Pin pin = new Pin(10, 0);
        assertThat(pin.leftPins()).isEqualTo(10 - 0);
        assertThat(pin.isGutter()).isTrue();
    }

}
