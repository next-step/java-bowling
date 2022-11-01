package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @Test
    @DisplayName("프레임 종료 판단 함수 테스트 : 끝나지 않은 경우")
    void isEndFrame1() {
        Frame frame = new Frame();
        assertThat(frame.isEndFrame()).isFalse();
        frame.record(0);
        assertThat(frame.isEndFrame()).isFalse();
    }

    @Test
    @DisplayName("프레임 종료 판단 함수 테스트 : GUTTER 로 끝난 경우")
    void isEndFrame2() {
        Frame frame = new Frame();
        frame.record(0);
        frame.record(0);
        assertThat(frame.isEndFrame()).isTrue();
    }

    @Test
    @DisplayName("프레임 종료 판단 함수 테스트 : MISS 로 끝난 경우")
    void isEndFrame3() {
        Frame frame = new Frame();
        frame.record(5);
        frame.record(4);
        assertThat(frame.isEndFrame()).isTrue();
    }

    @Test
    @DisplayName("프레임 종료 판단 함수 테스트 : SPARE 로 끝난 경우")
    void isEndFrame4() {
        Frame frame = new Frame();
        frame.record(5);
        frame.record(5);
        assertThat(frame.isEndFrame()).isTrue();
    }

    @Test
    @DisplayName("프레임 종료 판단 함수 테스트 : STRIKE 로 끝난 경우")
    void isEndFrame5() {
        Frame frame = new Frame();
        frame.record(10);
        assertThat(frame.isEndFrame()).isTrue();
    }

    @Test
    @DisplayName("프레임의 일반 점수 조회 함수 검증")
    void getScoreTest() {
        Frame frame = new Frame();
        assertThat(frame.getPinScore()).isEqualTo(0);
        frame.record(4);
        assertThat(frame.getPinScore()).isEqualTo(4);
        frame.record(5);
        assertThat(frame.getPinScore()).isEqualTo(4 + 5);
    }

}