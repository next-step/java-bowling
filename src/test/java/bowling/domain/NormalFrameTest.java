package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("스트라이크 치면 1Frame 종료된다.")
    void isEndByStrike() {
        NormalFrame frame = new NormalFrame(0,1,10);

        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("스트라이크가 아닌 투구는 종료되지 않는다.")
    void isEndByTwoStep() {
        NormalFrame frame = new NormalFrame(0,1,8);

        assertThat(frame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("점수를 계산한다.")
    void calculateResult() {
        NormalFrame frame = NormalFrame.first(5);
        frame.next(4);

        assertThat(frame.result).isEqualTo(9);
    }
}
