package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Frame;
import bowling.domain.NormalFrame;

class NormalFrameTest {

    @DisplayName("스트라이크를 친 후 공을 생성")
    @Test
    void strikeBowl() {
        NormalFrame frame = new NormalFrame(1);
        frame.bowl(10);
        Frame next = frame.create();
        assertThat(next.getFrameNum()).isEqualTo(2);
    }

    @DisplayName("스페어를 친 후 공을 생성")
    @Test
    void spareBowl() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(1);
        normalFrame.bowl(9);
        Frame next = normalFrame.create();
        assertThat(next.getFrameNum()).isEqualTo(2);
    }

    @DisplayName("미스를 친 후 공을 생성")
    @Test
    void missBowl() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(1);
        normalFrame.bowl(0);
        Frame next = normalFrame.create();
        assertThat(next.getFrameNum()).isEqualTo(2);
    }
}
