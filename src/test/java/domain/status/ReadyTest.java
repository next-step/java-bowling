package domain.status;

import domain.base.BaseTest;
import domain.pin.Pin;
import org.junit.Test;

import static domain.pin.Pin.MAXIMUM_PINS;
import static domain.pin.Pin.MINIMUM_PINS;
import static domain.status.FirstBowlFinished.ZERO_PIN_DISPLAY_STRING;
import static domain.status.Strike.STRIKE_DISPLAY_STRING;
import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest extends BaseTest {

    @Test
    public void getNext_zeroPin() {
        Pin pin = Pin.of(MINIMUM_PINS);
        Status status = new Ready().getNext(pin);

        assertThat(status).isInstanceOf(FirstBowlFinished.class);
        assertThat(status.isClear()).isEqualTo(false);
        assertThat(status.isNormalFrameFinished()).isEqualTo(false);
        assertThat(status.toString()).isEqualTo(ZERO_PIN_DISPLAY_STRING);
    }

    @Test
    public void getNext() {
        for (Pin firstBowl : getPins(MINIMUM_PINS + 1, MAXIMUM_PINS - 1)) {
            Status status = new Ready().getNext(firstBowl);

            assertThat(status).isInstanceOf(FirstBowlFinished.class);
            assertThat(status.isClear()).isEqualTo(false);
            assertThat(status.isNormalFrameFinished()).isEqualTo(false);
            assertThat(status.toString()).isEqualTo(firstBowl.toString());
        }
    }

    @Test
    public void getNext_for_strike() {
        Pin pin = Pin.of(MAXIMUM_PINS);
        Status status = new Ready().getNext(pin);

        assertThat(status).isInstanceOf(Strike.class);
        assertThat(status.isClear()).isEqualTo(true);
        assertThat(status.isNormalFrameFinished()).isEqualTo(true);
        assertThat(status.toString()).isEqualTo(STRIKE_DISPLAY_STRING);
    }
}