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

    @Test
    void getLastPin() {
        int firstPin = 8;
        int secondPin = 7;
        Pins pins = new Pins();
        pins.add(new Pin(firstPin));
        pins.add(new Pin(secondPin));

        Pin actual = pins.getLastPin();

        assertThat(actual).isEqualTo(new Pin(secondPin));
    }

    @Test
    void getBeforePin() {
        int firstPin = 8;
        int secondPin = 7;
        Pins pins = new Pins();
        pins.add(new Pin(firstPin));
        pins.add(new Pin(secondPin));

        Pin actual = pins.getBeforePin();

        assertThat(actual).isEqualTo(new Pin(firstPin));
    }
}
