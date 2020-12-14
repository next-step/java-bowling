package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.FallingPinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FrameTest {

    @DisplayName("조건에 맞게 종료가 되는지 테스트")
    @Test
    void isFrameFinished() {
        int index = 0;
        Frame frame1 = new Frame(index);
        frame1.roll(FallingPinCount.fromFallingCount(10));

        Frame frame2 = new Frame(index);
        frame2.roll(FallingPinCount.fromFallingCount(5));
        frame2.roll(FallingPinCount.fromFallingCount(0));

        assertThat(frame1.isFrameFinished()).isTrue();
        assertThat(frame2.isFrameFinished()).isTrue();
    }
}
