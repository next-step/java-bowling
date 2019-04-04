package domain;

import org.junit.Test;

import static domain.Pins.MINIMUM_PINS;
import static domain.Pins.MAXIMUM_PINS;
import static org.assertj.core.api.Assertions.assertThat;

public class PinTest {
    @Test(expected = IllegalArgumentException.class)
    public void constructor_for_less_than_MINIMUM_NUMBER() {
        new Pins(MINIMUM_PINS -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_for_more_than_MAXIMUM_NUMBER() {
        new Pins(MAXIMUM_PINS +1);
    }

    @Test
    public void constructor_for_MINIMUM_NUMBER() {
        int expected = MINIMUM_PINS;

        Pins pin = new Pins(expected);

        assertThat(pin.getNumber()).isEqualTo(expected);
    }

    @Test
    public void constructor_for_MEDIAN_VALUE() {
        int expected = MAXIMUM_PINS + MINIMUM_PINS / 2;

        Pins pin = new Pins(expected);

        assertThat(pin.getNumber()).isEqualTo(expected);
    }

    @Test
    public void constructor_for_MAXIMUM_NUMBER() {
        int expected = MAXIMUM_PINS;

        Pins pin = new Pins(expected);

        assertThat(pin.getNumber()).isEqualTo(expected);
    }
}