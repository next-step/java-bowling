package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class LastFrameTest {

    @Test
    @DisplayName("초구 스트라이크 이후 종료조건")
    void EndStatusWithStrike() {
        LastFrame lastFrame = new LastFrame();

        assertThat(lastFrame.isEnd()).isFalse();

        lastFrame.record(DownedPin.fromNumber(10));
        assertThat(lastFrame.isEnd()).isFalse();

        lastFrame.record(DownedPin.fromNumber(9));
        assertThat(lastFrame.isEnd()).isFalse();

        lastFrame.record(DownedPin.fromNumber(0));
        assertThat(lastFrame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("2회 미스 후의 종료 조건 확인")
    void EndStatusWithMiss() {
        LastFrame lastFrame = new LastFrame();

        assertThat(lastFrame.isEnd()).isFalse();

        lastFrame.record(DownedPin.fromNumber(4));
        assertThat(lastFrame.isEnd()).isFalse();

        lastFrame.record(DownedPin.fromNumber(4));
        assertThat(lastFrame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("2구 스페어 이후의 종료조건")
    void EndWIthSpare() {
        LastFrame lastFrame = new LastFrame();

        assertThat(lastFrame.isEnd()).isFalse();

        lastFrame.record(DownedPin.fromNumber(5));
        assertThat(lastFrame.isEnd()).isFalse();

        lastFrame.record(DownedPin.fromNumber(5));
        assertThat(lastFrame.isEnd()).isFalse();

        lastFrame.record(DownedPin.fromNumber(8));
        assertThat(lastFrame.isEnd()).isTrue();
    }

}
