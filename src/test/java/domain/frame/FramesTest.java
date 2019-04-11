package domain.frame;

import domain.base.BaseTest;
import domain.pin.Pin;
import org.junit.Test;

import static domain.frame.Frames.LAST_FRAME;
import static domain.frame.Frames.START_FRAME;
import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest extends BaseTest {

    @Test
    public void getNextFrameNumber_for_empty_frames() {
        Frames frames = new Frames();
        int actual = frames.getNextFrameNumber();

        assertThat(actual).isEqualTo(START_FRAME);
    }

    @Test
    public void getNextFrameNumber_for_finished_frames() {
        Frames frames = new Frames();

        for(int frameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            Frame frame = new NormalFrame(frameNumber, Pin.ofStrike());
            frames.add(frame);
            int actual = frames.getNextFrameNumber();

            assertThat(actual).isEqualTo(frameNumber + 1);
        }
    }

    @Test
    public void getNextFrameNumber_for_unfinished_frames() {
        Frames frames = new Frames();

        for(int frameNumber : getFrameNumbers(START_FRAME, LAST_FRAME - 1)) {
            Frame frame = new NormalFrame(frameNumber, Pin.ofZero());
            frames.add(frame);

            int actual = frames.getNextFrameNumber();
            assertThat(actual).isEqualTo(frameNumber);
        }
    }
}