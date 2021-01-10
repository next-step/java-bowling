package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("프레임에 쓰러진 핀 갯수 기록")
    void recordDownedPins() {
        NormalFrame frame = new NormalFrame();

        frame.record(6);
    }

    @Test
    @DisplayName("프레임은 최대 2회의 기록을 가질 수 있다")
    void recordTwice() {
        NormalFrame frame = new NormalFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(4);
        assertThat(frame.isEnd()).isFalse();
        frame.record(5);
        assertThat(frame.isEnd()).isFalse();
    }
}
