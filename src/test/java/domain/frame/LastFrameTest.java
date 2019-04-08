package domain.frame;

import domain.base.BaseTest;
import domain.pin.Pin;
import domain.status.FirstBowlFinished;
import domain.status.Open;
import domain.status.Spare;
import domain.status.Strike;
import org.junit.Test;

import static domain.pin.Pin.MAXIMUM_PINS;
import static domain.pin.Pin.MINIMUM_PINS;
import static org.assertj.core.api.Assertions.assertThat;

public class LastFrameTest extends BaseTest {

    @Test
    public void constructor_for_not_strike() {
        for (Pin curPin : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Frame frame = new LastFrame(curPin);

            assertThat(frame.statuses.size()).isEqualTo(1);
            assertThat(frame.statuses.getLastStatus()).isInstanceOf(FirstBowlFinished.class);
            assertThat(frame.pins.size()).isEqualTo(1);
            assertThat(frame.pins.get(0)).isEqualTo(curPin);
        }
    }

    @Test
    public void constructor_for_strike() {
        Frame frame = new LastFrame(Pin.ofStrike());

        assertThat(frame.statuses.size()).isEqualTo(1);
        assertThat(frame.statuses.getLastStatus()).isInstanceOf(Strike.class);
        assertThat(frame.pins.size()).isEqualTo(1);
        assertThat(frame.pins.get(0)).isEqualTo(Pin.ofStrike());
    }

    @Test
    public void isFinished_after_third_bowl_for_strike() {
        Pin firstBowl = Pin.ofStrike();
        for (Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS)) {
            for (Pin thirdBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - secondBowl.getPin())) {
                Frame frame = new LastFrame(firstBowl);

                assertThat(frame.isFinished()).isFalse();
                assertThat(frame.getLastStatus()).isInstanceOf(Strike.class);
                assertThat(frame.getLastStatus().isNormalFrameFinished()).isTrue();
                assertThat(frame.getLastStatus().isClear()).isTrue();

                frame.bowl(secondBowl);

                assertThat(frame.isFinished()).isFalse();

                frame.bowl(thirdBowl);

                assertThat(frame.pins.size()).isGreaterThan(2);
                assertThat(frame.isFinished()).isTrue();
            }
        }
    }

    @Test
    public void isFinished_after_third_bowl_for_spare() {
        for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Pin secondBowl = Pin.of(MAXIMUM_PINS - firstBowl.getPin());
            for (Pin thirdBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS)) {
                Frame frame = new LastFrame(firstBowl);

                assertThat(frame.isFinished()).isFalse();

                frame = frame.bowl(secondBowl);

                assertThat(frame.getLastStatus()).isInstanceOf(Spare.class);
                assertThat(frame.getLastStatus().isNormalFrameFinished()).isTrue();
                assertThat(frame.getLastStatus().isClear()).isTrue();
                assertThat(frame.isFinished()).isFalse();

                frame = frame.bowl(thirdBowl);

                assertThat(frame.pins.size()).isGreaterThan(2);
                assertThat(frame.isFinished()).isTrue();
            }
        }
    }

    @Test
    public void isFinished_after_second_bowl_for_open() {
        for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            for (Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - firstBowl.getPin() - 1)) {
                Frame frame = new LastFrame(firstBowl);
                frame = frame.bowl(secondBowl);

                assertThat(frame.getLastStatus()).isInstanceOf(Open.class);
                assertThat(frame.getLastStatus().isNormalFrameFinished()).isTrue();
                assertThat(frame.getLastStatus().isClear()).isFalse();
                assertThat(frame.isFinished()).isTrue();
            }
        }
    }
}