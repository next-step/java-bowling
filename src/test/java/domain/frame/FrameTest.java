package domain.frame;

import domain.pin.Pin;
import domain.status.FirstBowlFinished;
import domain.status.Strike;
import org.junit.Test;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;
import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    @Test
    public void createNextFrame() {
        Frame currentFrame = new NormalFrame(START_FRAME, Pin.ofStrike());
        Pin newFramePin = Pin.of(8);
        Frame nextFrame = currentFrame.createNextFrame(newFramePin);

        assertThat(nextFrame).isInstanceOf(NormalFrame.class);
        assertThat(nextFrame.getNumber()).isEqualTo(currentFrame.getNumber() + 1);
        assertThat(nextFrame.getPin(0)).isEqualTo(newFramePin);
        assertThat(nextFrame.getLastStatus()).isInstanceOf(FirstBowlFinished.class);
    }

    @Test
    public void createNextFrame_for_last_frame() {
        Frame currentFrame = new NormalFrame(LAST_FRAME - 1, Pin.ofStrike());
        Pin newFramePin = Pin.ofStrike();
        Frame nextFrame = currentFrame.createNextFrame(newFramePin);

        assertThat(nextFrame).isInstanceOf(LastFrame.class);
        assertThat(nextFrame.getNumber()).isEqualTo(currentFrame.getNumber() + 1);
        assertThat(nextFrame.getPin(0)).isEqualTo(newFramePin);
        assertThat(nextFrame.getLastStatus()).isInstanceOf(Strike.class);
    }
}