package domain;

import org.junit.jupiter.api.Test;

import static domain.Bowling.TOTAL_FRAME_COUNT;
import static domain.Frame.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

public class FrameCounterTest {

    @Test
    void 프레임_번호_추가_테스트() {
        new FrameCounter(ZERO);
        FrameCounter.addFrameCount();
        assertThat(FrameCounter.getFrameCounter()).isEqualTo(1);
    }

    @Test
    void 마지막_프레임_번호_테스트() {
        new FrameCounter(TOTAL_FRAME_COUNT);
        assertThat(FrameCounter.isFinalFrame()).isTrue();
    }
}
