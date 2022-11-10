package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.domain.status.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("다음 프레임")
    void next_frame(Status state) {
        Frame frame = new NormalFrame(1);
        Pin pin = new Pin(10);
        assertThat(frame.bowl(pin)).isEqualTo(new NormalFrame(2));
    }

    @Test
    @DisplayName("마지막 프레임")
    void next_frame_final(Status state) {
        Frame frame = new NormalFrame(9);
        Pin pin = new Pin(10);
        assertThat(frame.bowl(pin)).isInstanceOf(FinalFrame.class);
    }

    @Test
    void get_frame_score() {
        List<Frame> frameList = new ArrayList<>();
        Frame frame1 = new NormalFrame(1);
        Frame frame2 = new NormalFrame(2);
        frameList.add(frame1);
        frameList.add(frame2);
        Frames frames = new Frames(frameList);

        frames.bowl(new Pin(10));
        frames.bowl(new Pin(8));
        frames.bowl(new Pin(2));
        Score score = frame1.getScore();
        assertThat(score).isEqualTo(new Score(20, 0));

    }
}
