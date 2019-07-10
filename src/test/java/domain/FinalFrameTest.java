package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    private FinalFrame finalFrame;

    @BeforeEach
    void setUp() {
        finalFrame = new FinalFrame();
    }

    @DisplayName("넘어뜨린 핀 개수 모두")
    @Test
    void sumScoreTest() {
        finalFrame.doBowling(4);
        finalFrame.doBowling(6);
        finalFrame.doBowling(3);
        assertThat(finalFrame.sumScore()).isEqualTo(13);
    }

    @DisplayName("포인트 출력결과")
    @Test
    void framePointTest() {
        finalFrame.doBowling(4);
        finalFrame.doBowling(6);
        assertThat(finalFrame.framePoint()).isEqualTo("4|/  ");
    }

    @DisplayName("열 번째 프레임의 종료")
    @Test
    void isGameOverTest() {
        finalFrame.doBowling(4);
        finalFrame.doBowling(6);
        assertThat(finalFrame.isGameOver()).isFalse();

        finalFrame.doBowling(10);
        assertThat(finalFrame.isGameOver()).isTrue();
    }

    @DisplayName("열 번째 프레임 시작")
    @Test
    void isStartTest() {
        assertThat(finalFrame.isStart()).isFalse();
        finalFrame.doBowling(4);
        assertThat(finalFrame.isStart()).isTrue();
    }

    @DisplayName("프레임 점수 합산")
    @Test
    void frameScoreTest() {
        final int SECOND = 2;
        finalFrame.doBowling(4);
        finalFrame.doBowling(6);
        assertThat(finalFrame.frameScore(SECOND)).isEqualTo(10);
    }
}
