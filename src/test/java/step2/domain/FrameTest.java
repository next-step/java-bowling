package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FrameTest {
    @DisplayName("볼링핀 넘어뜨렸을 떄의 경우 결과 확인")
    @Test
    void knockDownBowlingPins() {
        Frame frame = new Frame();
        frame.knockDown(4);
        assertThat(frame).isEqualTo(new Frame(6,4));
    }
}