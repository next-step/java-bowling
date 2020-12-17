package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @Test
    void FinalFrameCreateTest() {
        Frame frame = FinalFrame.create();
        assertThat(frame).isNotNull();
    }

    @Test
    @DisplayName("스트라이크의 경우 2번 더 던지기 테스트")
    void FinalFrameStrikeTest() {
        Frame frame = FinalFrame.create();
        Point point = Point.inputPoint(10);
        boolean lastPitch = frame.bowl(point).bowl(point).bowl(point).isLastPitch();
        assertThat(lastPitch).isTrue();
    }

    @Test
    @DisplayName("스페어의 경우 1번 더 던지기 테스트")
    void FinalFrameSpareTest() {
        Frame frame = FinalFrame.create();
        Point point = Point.inputPoint(5);
        boolean lastPitch = frame.bowl(point).bowl(point).bowl(point).isLastPitch();
        assertThat(lastPitch).isTrue();
    }
}
