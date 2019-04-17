package domain.frame;

import domain.base.BaseTest;
import domain.pin.Pin;
import domain.status.FirstBowlFinished;
import domain.status.Strike;
import org.junit.Test;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;
import static domain.pin.Pin.MAXIMUM_PINS;
import static domain.pin.Pin.MINIMUM_PINS;
import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest extends BaseTest {
    @Test
    public void createNextFrame() {
        Frame currentFrame = new NormalFrame(START_FRAME, Pin.ofStrike());
        Pin newFramePin = Pin.of(8);
        Frame nextFrame = currentFrame.createNextFrame(newFramePin);

        assertThat(nextFrame).isInstanceOf(NormalFrame.class);
        assertThat(nextFrame.getNumber()).isEqualTo(currentFrame.getNumber() + 1);
        assertThat(nextFrame.getLastStatus().getCurrentPin()).isEqualTo(newFramePin.getPin());
        assertThat(nextFrame.getLastStatus()).isInstanceOf(FirstBowlFinished.class);
    }

    @Test
    public void createNextFrame_for_last_frame() {
        Frame currentFrame = new NormalFrame(LAST_FRAME - 1, Pin.ofStrike());
        Pin newFramePin = Pin.ofStrike();
        Frame nextFrame = currentFrame.createNextFrame(newFramePin);

        assertThat(nextFrame).isInstanceOf(LastFrame.class);
        assertThat(nextFrame.getNumber()).isEqualTo(currentFrame.getNumber() + 1);
        assertThat(nextFrame.getLastStatus().getCurrentPin()).isEqualTo(newFramePin.getPin());
        assertThat(nextFrame.getLastStatus()).isInstanceOf(Strike.class);
    }

    @Test
    public void bowl_for_strike() {
        Frame currentFrame = new NormalFrame(START_FRAME, Pin.ofStrike());

        assertThat(currentFrame.getStatusesSize()).isEqualTo(1);
    }

    @Test
    public void bowl_for_spare() {
        for (Pin first : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            for (Pin second : getPins(MINIMUM_PINS, MAXIMUM_PINS - first.getPin())) {
                Frame currentFrame = new NormalFrame(START_FRAME, first);
                currentFrame.bowl(second);

                assertThat(currentFrame.getStatusesSize()).isEqualTo(2);
            }
        }
    }

    @Test
    public void bowl_for_open() {
        for (Pin first : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            for (Pin second : getPins(MINIMUM_PINS, MAXIMUM_PINS - first.getPin() - 1)) {
                Frame currentFrame = new NormalFrame(START_FRAME, first);
                currentFrame.bowl(second);

                assertThat(currentFrame.getStatusesSize()).isEqualTo(2);
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void bowl_for_over_MAXIMUM_PINS() {
        for (Pin first : getPins(MINIMUM_PINS + 1, MAXIMUM_PINS - 1)) {
            Pin second = Pin.of(MAXIMUM_PINS);

            Frame currentFrame = new NormalFrame(START_FRAME, first);
            currentFrame.bowl(second);
        }
    }

}