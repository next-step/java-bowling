package domain.pin;

import domain.base.BaseTest;
import org.junit.Test;

import static domain.pin.Pin.MAXIMUM_PINS;
import static domain.pin.Pin.MINIMUM_PINS;
import static org.assertj.core.api.Assertions.assertThat;

public class PinsTest extends BaseTest {

    @Test
    public void getScore_for_strike() {
        Pin firstBowl = Pin.ofStrike();

        Pins pins = new Pins();
        pins.add(firstBowl);

        assertThat(pins.getScore()).isEqualTo(firstBowl.getPin());
    }

    @Test
    public void getScore_for_spare() {
        for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Pin secondBowl = Pin.of(MAXIMUM_PINS - firstBowl.getPin());

            Pins pins = new Pins();
            pins.add(firstBowl);
            pins.add(secondBowl);

            assertThat(pins.getScore()).isEqualTo(firstBowl.add(secondBowl).getPin());
        }
    }

    @Test
    public void getScore_for_open() {
        for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Pin secondBowl = Pin.of(MAXIMUM_PINS - firstBowl.getPin() - 1);

            Pins pins = new Pins();
            pins.add(firstBowl);
            pins.add(secondBowl);

            assertThat(pins.getScore()).isEqualTo(firstBowl.add(secondBowl).getPin());
        }
    }

    @Test
    public void getScore_for_spare_last_frame() {
        for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS)) {
            Pin secondBowl = Pin.of(MAXIMUM_PINS - firstBowl.getPin());
            for (Pin thirdBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS)) {
                Pins pins = new Pins();
                pins.add(firstBowl);
                pins.add(secondBowl);
                pins.add(thirdBowl);

                assertThat(pins.getScore()).isEqualTo(firstBowl.getPin() + secondBowl.getPin() + thirdBowl.getPin());
            }
        }
    }
}