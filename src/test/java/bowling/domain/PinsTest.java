package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {

    @DisplayName("상태 체크")
    @Test
    void stateCheck() {
        Pins pins = new Pins();
        assertThat(pins.isGutter()).isTrue();
        pins.setFirstPin(new Pin(10, 0));
        assertThat(pins.isGutter()).isTrue();
        pins.setFirstPin(new Pin(10, 1));
        assertThat(pins.isMiss()).isTrue();
        pins.setFirstPin(new Pin(10, 10));
        assertThat(pins.isStrike()).isTrue();
        pins.setFirstPin(new Pin(10, 4));
        pins.setSecondPin(new Pin(6, 6));
        assertThat(pins.isSpare()).isTrue();
    }


}
