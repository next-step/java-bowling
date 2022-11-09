package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.status.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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


}