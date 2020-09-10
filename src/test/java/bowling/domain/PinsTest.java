package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    @Test
    void from() {
        assertThat(Pins.from(Pins.COUNT)).isEqualTo(Pins.from(Pins.COUNT));
    }

    @Test
    void fromThrowException() {
        assertThatThrownBy(() -> Pins.from(Pins.COUNT + 1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Pins.from(Pins.COUNT - 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void newPins() {
        assertThat(Pins.create()).isEqualTo(Pins.from(Pins.COUNT));
    }

    @Test
    void hitting() {
        int hitCount = 1;

        Pins pins = Pins.from(Pins.COUNT);
        assertThat(pins.hitting(hitCount)).isEqualTo(Pins.COUNT - hitCount);
    }

    @Test
    void hittingThrowException() {
        Pins pins = Pins.from(Pins.COUNT);
        assertThatThrownBy(() -> pins.hitting(Pins.COUNT + 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isClear() {
        Pins pins = Pins.from(Pins.COUNT);

        assertThat(pins.isClear()).isFalse();

        pins.hitting(Pins.COUNT);

        assertThat(pins.isClear()).isTrue();
    }

    @Test
    void getDownPins() {
        Pins pins = Pins.from(Pins.COUNT);

        assertThat(pins.getDownPins()).isEqualTo(0);

        pins.hitting(Pins.COUNT);

        assertThat(pins.getDownPins()).isEqualTo(Pins.COUNT);
    }
}
