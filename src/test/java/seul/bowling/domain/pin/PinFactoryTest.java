package seul.bowling.domain.pin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PinFactoryTest {
    private Pin pin;

    @BeforeEach
    void setUp() {
        pin = PinFactory.getPin(10);
    }

    @Test
    void getPin() {
        Pin pin = PinFactory.getPin(10);
        Pin newPin = Pin.of(10);

        assertThat(pin == this.pin).isTrue();
        assertThat(newPin == this.pin).isFalse();
    }
}
