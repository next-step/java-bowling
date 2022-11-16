package bowling.domain.frame;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("점수 계산하기")
    void get_frame_score() {
        Frame frame1 = new NormalFrame(1);
        frame1.bowl(new Pin(10));
        assertThat(frame1.getScore().getValue()).isEqualTo(10);
    }
}
