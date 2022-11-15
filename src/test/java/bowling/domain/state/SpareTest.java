package bowling.domain.state;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @DisplayName("다음 1번의 투구를 가지고 있으면, 점수를 가지고 있어야 한다.")
    @Test
    void hasScore() {
        Frame frame2 = new NormalFrame(null);
        Frame frame1 = new NormalFrame(frame2);
        frame1.add(9);
        assertThat(FrameState.SPARE.hasScore(frame1)).isFalse();

        frame1.add(1);
        assertThat(FrameState.SPARE.hasScore(frame1)).isFalse();

        frame2.add(10);
        assertThat(FrameState.SPARE.hasScore(frame1)).isTrue();
    }

    @DisplayName("다음 1번의 투구 점수를 더한 점수를 가지고 있어야 한다.")
    @Test
    void calculateScore() {
        Frame frame2 = new NormalFrame(null);
        Frame frame1 = new NormalFrame(frame2);
        frame1.add(7);
        frame1.add(3);
        frame2.add(10);
        assertThat(FrameState.SPARE.calculateScore(frame1)).isEqualTo(20);
    }

}
