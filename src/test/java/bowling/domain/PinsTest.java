package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Pins 클래스 테스트")
class PinsTest {

    @Test
    void add() {
        Pins pins = new Pins();
        pins.add(new Pin(Pin.MAX_PIN));
        pins.add(new Pin(Pin.MIN_PIN));

        assertThat(pins.getPins()).hasSize(2);
        assertThat(pins.getPins().get(0)).isEqualTo(new Pin(Pin.MAX_PIN));
    }
}
