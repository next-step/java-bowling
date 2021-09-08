package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FrameTest {
    @DisplayName("넘어뜨린 볼링핀에 따른 score 결과 확인")
    @Test
    void score() {
        Frame frame = new Frame();
        frame.knockDown(4);
        int actual = frame.score();
        assertThat(actual).isEqualTo(4);
    }

    @DisplayName("10점을 쓰러뜨린 경우 finish 메서드 확인")
    @Test
    void finish() {
        Frame frame = new Frame();
        frame.knockDown(4);
        frame.knockDown(6);
        assertThat(frame.isFinish()).isTrue();
    }

    @DisplayName("볼링핀을 10개 넘게 쓰러뜨린 경우 오류 확인")
    @Test
    void overTenScoreErrorTest() {
        Frame frame = new Frame();
        assertThatThrownBy(() -> {
            frame.knockDown(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("볼링핀을 10개 넘게 쓰러뜨린 경우 오류 확인2")
    @Test
    void overTenScoreErrorTest2() {
        Frame frame = new Frame();
        frame.knockDown(5);
        assertThatThrownBy(() -> {
            frame.knockDown(6);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}