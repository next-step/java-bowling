package domain.pin;

import org.junit.Test;

import static domain.pin.Pin.MINIMUM_PINS;
import static domain.pin.Pin.MAXIMUM_PINS;
import static org.assertj.core.api.Assertions.assertThat;

public class PinTest {
    @Test(expected = IllegalArgumentException.class)
    public void of_for_less_than_MINIMUM_NUMBER() {
        Pin.of(MINIMUM_PINS -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_for_more_than_MAXIMUM_NUMBER() {
        Pin.of(MAXIMUM_PINS +1);
    }

    @Test
    public void of_for_MINIMUM_NUMBER() {
        int expected = MINIMUM_PINS;

        Pin pin = Pin.of(expected);

        assertThat(pin.getPin()).isEqualTo(expected);
    }

    @Test
    public void of_for_MEDIAN_VALUE() {
        int expected = MAXIMUM_PINS + MINIMUM_PINS / 2;

        Pin pin = Pin.of(expected);

        assertThat(pin.getPin()).isEqualTo(expected);
    }

    @Test
    public void of_for_MAXIMUM_NUMBER() {
        int expected = MAXIMUM_PINS;

        Pin pin = Pin.of(expected);

        assertThat(pin.getPin()).isEqualTo(expected);
    }
}