package domain.pin;

import org.junit.Test;

import static domain.pin.Pin.MINIMUM_PINS;
import static domain.pin.Pin.MAXIMUM_PINS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

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

    @Test
    public void of() {
        for(int i = MINIMUM_PINS; i <= MAXIMUM_PINS; ++i) {
            Pin first = Pin.of(i);
            Pin second = Pin.of(i);

            assertEquals(first, second);
            assertEquals(first.getPin(), i);
            assertEquals(second.getPin(), i);
        }
    }

    @Test
    public void isZeroPin_for_zeroPin() {
        assertTrue(Pin.of(MINIMUM_PINS).isZeroPin());
    }

    @Test
    public void isZeroPin_for_not_zeroPins() {
        for(int i = MINIMUM_PINS+1; i <= MAXIMUM_PINS; ++i) {
            assertFalse(Pin.of(i).isZeroPin());
        }
    }

    @Test
    public void isSpare() {
        for(int i = MINIMUM_PINS; i <= MAXIMUM_PINS-1; ++i) {
            Pin first = Pin.of(i);
            assertTrue(first.isSpare(Pin.of(MAXIMUM_PINS-i)));
        }
    }

    @Test
    public void isSpare_for_not_spares() {
        for(int i = MINIMUM_PINS; i <= MAXIMUM_PINS-1; ++i) {
            for(int j = MINIMUM_PINS; j < MAXIMUM_PINS-i; ++j) {
                Pin first = Pin.of(i);
                assertFalse(first.isSpare(Pin.of(j)));
            }
        }
    }

    @Test
    public void isStrike() {
        assertTrue(Pin.of(MAXIMUM_PINS).isStrike());
    }

    @Test
    public void isStrike_for_not_strikes() {
        for(int i = MINIMUM_PINS; i < MAXIMUM_PINS; ++i) {
            assertFalse(Pin.of(i).isStrike());
        }
    }
}