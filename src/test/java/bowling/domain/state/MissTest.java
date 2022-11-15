package bowling.domain.state;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @DisplayName("다음 투구를 가지고 있지 않아도, 점수를 가지고 있어야 한다.")
    @Test
    void hasScore() {
        Frame frame = new NormalFrame(null);
        frame.add(8);
        assertThat(FrameState.MISS.hasScore(frame)).isFalse();

        frame.add(1);

        assertThat(FrameState.MISS.hasScore(frame)).isTrue();
    }

    @DisplayName("보너스 점수 없이 점수를 가지고 있어야 한다.")
    @Test
    void calculateScore() {
        Frame frame1 = new NormalFrame(null);
        frame1.add(1);
        frame1.add(8);
        assertThat(FrameState.MISS.calculateScore(frame1)).isEqualTo(9);
    }

}
