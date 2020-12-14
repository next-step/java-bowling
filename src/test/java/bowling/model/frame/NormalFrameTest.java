package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {

    @Test
    @DisplayName("NormalFrame 정상 생성")
    void createFirstFrame() {
        NormalFrame frame = NormalFrame.createFirstFrame();
        String frameNumber = frame.frameNumber.toString();
        assertThat(Integer.valueOf(frameNumber)).isEqualTo(1);
    }

    @Test
    void bowling_스트라이크인_경우_다음_frame_반환() {
        NormalFrame frame = NormalFrame.createFirstFrame();
        String frameNumber = frame.bowling(10).frameNumber.toString();
        assertThat(Integer.valueOf(frameNumber)).isEqualTo(2);
    }

    @Test
    void bowling_현재_frame_반환() {
        NormalFrame frame = NormalFrame.createFirstFrame();
        String frameNumber = frame.bowling(9).frameNumber.toString();
        assertThat(Integer.valueOf(frameNumber)).isEqualTo(1);
    }

    @Test
    void bowling_마지막_frmae_반환() {
        Frame frame = NormalFrame.createFirstFrame();
        for (int i = FrameNumber.MIN_FRAME_NUMBER; i < FrameNumber.MAX_FRAME_NUMBER; i++) {
            frame = frame.bowling(10);
        }

        assertThat(frame).isInstanceOf(FinalFrame.class);
    }
}