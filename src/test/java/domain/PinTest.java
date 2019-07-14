package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PinTest {
    private final int FIRST_POINT = 2;
    private final int SECOND_POINT = 3;
    private final int SPARE_POINT = 8;
    private final int STRIKE_POINT = 10;

    private Pin pin;

    @BeforeEach
    void setUp() {
        pin = Pin.of(FIRST_POINT);
    }

    @Test
    void ofTest() {
        Pin pin_2 = Pin.of(FIRST_POINT);
        assertThat(pin.equals(pin_2)).isTrue();
    }

    @Test
    void isStrikeTest() {
        assertThat(Pin.of(STRIKE_POINT).isStrike()).isTrue();
    }

    @Test
    void isSpareTest() {
        assertThat(Pin.of(FIRST_POINT).isSpare(Pin.of(SPARE_POINT))).isTrue();
    }

    @Test
    void isMissTest() {
        assertThat(Pin.of(FIRST_POINT).isMiss(Pin.of(SECOND_POINT))).isTrue();
    }
}
