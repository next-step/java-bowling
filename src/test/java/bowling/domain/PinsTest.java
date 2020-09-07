package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinsTest {

    @Test
    void from() {
        assertThat(Pins.from(Pins.DEFAULT_PIN_COUNT)).isEqualTo(Pins.from(Pins.DEFAULT_PIN_COUNT));
    }

    @Test
    void fromThrowException() {
        assertThatThrownBy(() -> Pins.from(Pins.DEFAULT_PIN_COUNT + 1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Pins.from(Pins.DEFAULT_PIN_COUNT - 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void newPins() {
        assertThat(Pins.newPins()).isEqualTo(Pins.from(Pins.DEFAULT_PIN_COUNT));
    }

    @Test
    void hitting() {
        int hitCount = 1;

        Pins pins = Pins.from(Pins.DEFAULT_PIN_COUNT);
        pins.hitting(hitCount);

        assertThat(pins.checkCount(Pins.DEFAULT_PIN_COUNT - hitCount)).isTrue();
    }

    @Test
    void hittingThrowException() {
        assertThatThrownBy(() -> Pins.from(Pins.DEFAULT_PIN_COUNT).hitting(Pins.DEFAULT_PIN_COUNT + 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isClear() {
        Pins pins = Pins.from(Pins.DEFAULT_PIN_COUNT);

        assertThat(pins.isClear()).isFalse();

        pins.hitting(Pins.DEFAULT_PIN_COUNT);

        assertThat(pins.isClear()).isTrue();
    }
}
