package bowling.model.frame;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {
    private static final Pins TEN_PINS = Pins.from(10);
    private static final Pins NINE_PINS = Pins.from(9);

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
        String frameNumber = frame.bowling(TEN_PINS).frameNumber.toString();
        assertThat(Integer.valueOf(frameNumber)).isEqualTo(2);
    }

    @Test
    void bowling_현재_frame_반환() {
        NormalFrame frame = NormalFrame.createFirstFrame();
        String frameNumber = frame.bowling(NINE_PINS).frameNumber.toString();
        assertThat(Integer.valueOf(frameNumber)).isEqualTo(1);
    }

    @Test
    void bowling_마지막_frmae_반환() {
        Frame frame = NormalFrame.createFirstFrame();
        for (int i = FrameNumber.MIN_FRAME_NUMBER; i < FrameNumber.MAX_FRAME_NUMBER; i++) {
            frame = frame.bowling(TEN_PINS);
        }

        assertThat(frame).isInstanceOf(FinalFrame.class);
    }
}