package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {

    private Pins pins;

    @BeforeEach
    void setUp() {
        pins = new Pins();
        pins.add(1);
        pins.add(2);
        pins.add(3);
    }

    @Test
    void size() {
        assertThat(pins.size()).isEqualTo(3);
    }

    @Test
    void first() {
        assertThat(pins.first()).isEqualTo(1);
    }

    @Test
    void add() {
        pins.add(4);
        assertThat(pins.size()).isEqualTo(4);
    }
}
