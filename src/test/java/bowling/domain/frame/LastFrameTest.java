package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class LastFrameTest {

    @Test
    @DisplayName("일반적인 점수 입력")
    void recordScore() {
        LastFrame frame = new LastFrame();

        frame.record(9);
    }

    @Test
    @DisplayName("첫 2회 시도가 MISS일 때의 종료 시나리오")
    void endConditionWhenMiss() {
        LastFrame frame = new LastFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(4);
        assertThat(frame.isEnd()).isFalse();
        frame.record(5);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("Strike 이후 추가 2회 종료 시나리오")
    void endConditionWithStrike() {
        LastFrame frame = new LastFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(10);
        assertThat(frame.isEnd()).isFalse();
        frame.record(5);
        assertThat(frame.isEnd()).isFalse();
        frame.record(4);
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("Spare 이후 추가 2회 종료 시나리오")
    void endConditionWithSpare() {
        LastFrame frame = new LastFrame();

        assertThat(frame.isEnd()).isFalse();
        frame.record(4);
        assertThat(frame.isEnd()).isFalse();
        frame.record(6);
        assertThat(frame.isEnd()).isFalse();
        frame.record(9);
        assertThat(frame.isEnd()).isTrue();
    }
}
