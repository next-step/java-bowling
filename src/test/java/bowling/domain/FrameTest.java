package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @Test
    @DisplayName("스트라이크 치면 1Frame 종료된다.")
    void isEndByStrike() {
        Frame frame = new Frame();
        frame.addScore(10);

        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("스트라이크가 아닌 투구는 종료되지 않는다.")
    void isEndByTwoStep() {
        Frame frame = new Frame();
        frame.addScore(8);

        assertThat(frame.isEnd()).isFalse();
    }
}
