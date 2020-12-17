package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    void NormalFrameCreateTest() {
        Frame frame = NormalFrame.create();
        assertThat(frame).isNotNull();
    }

    @Test
    @DisplayName("스트라이크의 경우 넘어가기")
    void NormalFrameStrikeTest() {
        Frame frame = NormalFrame.create();
        Point point = Point.inputPoint(10);
        boolean lastPitch = frame.bowl(point).isLastPitch();
        assertThat(lastPitch).isTrue();
    }

    @Test
    @DisplayName("스페어의 경우 넘어가기")
    void NormalFrameSpareTest() {
        Frame frame = NormalFrame.create();
        Point point = Point.inputPoint(5);
        boolean lastPitch = frame.bowl(point).bowl(point).isLastPitch();
        assertThat(lastPitch).isTrue();
    }
}
