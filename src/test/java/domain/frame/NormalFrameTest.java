package domain.frame;

import domain.base.BaseTest;
import domain.pin.Pin;
import domain.status.FirstBowlFinished;
import domain.status.Open;
import domain.status.Spare;
import domain.status.Strike;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;
import static domain.pin.Pin.MAXIMUM_PINS;
import static domain.pin.Pin.MINIMUM_PINS;
import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest extends BaseTest {

    @Test
    public void constructor_for_not_strike() {
        for (int curFrameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            for (Pin curPin : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
                Frame frame = new NormalFrame(curFrameNumber, curPin);

                assertThat(frame.statuses.size()).isEqualTo(1);
                assertThat(frame.statuses.getLastStatus()).isInstanceOf(FirstBowlFinished.class);
                assertThat(frame.pins.size()).isEqualTo(1);
                assertThat(frame.pins.get(0)).isEqualTo(curPin);
            }
        }
    }

    @Test
    public void constructor_for_strike() {
        for (int curFrameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            Frame frame = new NormalFrame(curFrameNumber, Pin.ofStrike());

            assertThat(frame.statuses.size()).isEqualTo(1);
            assertThat(frame.statuses.getLastStatus()).isInstanceOf(Strike.class);
            assertThat(frame.pins.size()).isEqualTo(1);
            assertThat(frame.pins.get(0)).isEqualTo(Pin.ofStrike());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_for_less_than_start_frame_number() {
        new NormalFrame(START_FRAME-1, Pin.of(MAXIMUM_PINS));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_for_last_frame_number() {
        new NormalFrame(LAST_FRAME, Pin.of(5));
    }

    @Test
    public void bowl_for_spare() {
        for (int curFrameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
                Pin secondBowl = Pin.of(MAXIMUM_PINS - firstBowl.getPin());
                Frame frame = new NormalFrame(curFrameNumber, firstBowl);
                frame.bowl(secondBowl);

                assertThat(frame.statuses.size()).isEqualTo(2);
                assertThat(frame.statuses.get(0)).isInstanceOf(FirstBowlFinished.class);
                assertThat(frame.statuses.getLastStatus()).isInstanceOf(Spare.class);
                assertThat(frame.pins.get(0)).isEqualTo(firstBowl);
                assertThat(frame.pins.get(1)).isEqualTo(secondBowl);
            }
        }
    }

    @Test
    public void bowl_for_open() {
        for (int curFrameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            for (Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
                for (Pin secondBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - firstBowl.getPin() - 1)) {
                    Frame frame = new NormalFrame(curFrameNumber, firstBowl);
                    frame.bowl(secondBowl);

                    assertThat(frame.statuses.size()).isEqualTo(2);
                    assertThat(frame.statuses.get(0)).isInstanceOf(FirstBowlFinished.class);
                    assertThat(frame.statuses.getLastStatus()).isInstanceOf(Open.class);
                    assertThat(frame.pins.get(0)).isEqualTo(firstBowl);
                    assertThat(frame.pins.get(1)).isEqualTo(secondBowl);
                }
            }
        }
    }

    @Test
    public void bowl_for_new_frame() {
        Pin firstBowl = Pin.of(5);
        Pin secondBowl = Pin.of(5);
        Pin thirdBowl = Pin.of(9);

        Frame frame = new NormalFrame(START_FRAME, firstBowl);
        frame = frame.bowl(secondBowl);
        Frame newFrame = frame.bowl(thirdBowl);

        assertThat(newFrame).isNotEqualTo(frame);
        assertThat(newFrame.getNumber()).isEqualTo(frame.getNumber() + 1);
        assertThat(newFrame.getLastStatus()).isInstanceOf(FirstBowlFinished.class);
        assertThat(newFrame.getLastStatus().isNormalFrameFinished()).isEqualTo(false);
    }

    @Test
    public void isFinished_for_not_strike() {
        for(Pin firstBowl : getPins(MINIMUM_PINS, MAXIMUM_PINS - 1)) {
            Frame frame = new NormalFrame(START_FRAME, firstBowl);

            assertThat(frame.isFinished()).isEqualTo(false);
        }
    }

    @Test
    public void isFinished_for_strike() {
        Frame frame = new NormalFrame(START_FRAME, Pin.ofStrike());

        assertThat(frame.isFinished()).isEqualTo(true);
    }
}