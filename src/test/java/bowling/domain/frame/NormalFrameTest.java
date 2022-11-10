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
    void next_frame() {
        Frame frame = new NormalFrame(1);
        Pin pin = new Pin(10);
        assertThat(frame.bowl(pin).frameNo).isEqualTo(2);
    }

    @Test
    @DisplayName("마지막 프레임")
    void next_frame_final() {
        Frame frame = new NormalFrame(9);
        Pin pin = new Pin(10);
        assertThat(frame.bowl(pin)).isInstanceOf(FinalFrame.class);
    }

    @Test
    @DisplayName("점수 계산하기")
    void get_frame_score() {
        Frame frame1 = new NormalFrame(1);
        frame1.bowl(new Pin(10));
        assertThat(frame1.getScore().getScore()).isEqualTo(10);
    }
}
