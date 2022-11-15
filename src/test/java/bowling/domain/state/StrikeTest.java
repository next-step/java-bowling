package bowling.domain.state;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @DisplayName("다음 2번의 투구를 가지고 있으면, 점수를 가지고 있어야 한다.")
    @Test
    void hasScore() {
        Frame frame3 = new NormalFrame(null);
        Frame frame2 = new NormalFrame(frame3);
        Frame frame1 = new NormalFrame(frame2);
        frame1.add(10);
        assertThat(FrameState.STRIKE.hasScore(frame1)).isFalse();

        frame2.add(10);
        assertThat(FrameState.STRIKE.hasScore(frame1)).isFalse();

        frame3.add(9);
        assertThat(FrameState.STRIKE.hasScore(frame1)).isTrue();
    }

    @DisplayName("다음 2번의 투구 점수를 더한 점수를 가지고 있어야 한다.")
    @Test
    void calculateScore() {
        Frame frame3 = new NormalFrame(null);
        Frame frame2 = new NormalFrame(frame3);
        Frame frame1 = new NormalFrame(frame2);
        frame1.add(10);
        frame2.add(10);
        frame3.add(7);
        assertThat(FrameState.STRIKE.calculateScore(frame1)).isEqualTo(27);
    }

}
