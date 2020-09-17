package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PinTest {
    @Test
    void from() {
        assertThat(Pin.from()).isNotNull();
    }

    @Test
    void roll_strike() {
        Pin pin = Pin.of(10);

        assertThat(pin.isStrike()).isTrue();
    }

    @Test
    void roll_spare() {
        Pin pin = Pin.of(8);

        assertThat(pin.isSpare(2)).isTrue();
    }

    @Test
    void roll_gutter() {
        Pin pin = Pin.of(0);

        pin = pin.roll(0);

        assertThat(pin.isGutter()).isTrue();
    }
}
