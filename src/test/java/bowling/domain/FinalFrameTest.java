package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-14 22:24
 */
public class FinalFrameTest {
    @DisplayName("게임이 종료되었는지 확인 - 종료")
    @Test
    void isGameOver() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(3);
        finalFrame.bowl(5);
        assertThat(finalFrame.isGameOver()).isTrue();
    }

    @DisplayName("게임이 종료되었는지 확인 - 첫 번째 투구 스트라이크로 인해 진행")
    @Test
    void isGameOver_false_by_firstStrike() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(10);
        finalFrame.bowl(5);
        assertThat(finalFrame.isGameOver()).isFalse();
    }

    @DisplayName("게임이 종료되었는지 확인 - 스페어로 인해 진행")
    @Test
    void isGameOver_false_by_spare() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(5);
        finalFrame.bowl(5);
        assertThat(finalFrame.isGameOver()).isFalse();
    }

    @DisplayName("스트라이크 상태 메시지 비교 - 1번 투구")
    @Test
    void convertToStrike_1() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(10);
        assertThat(finalFrame.convertScoreToBowl()).isEqualTo("X");
    }

    @DisplayName("스트라이크 상태 메시지 비교 - 2번 투구")
    @Test
    void convertToStrike_2() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        assertThat(finalFrame.convertScoreToBowl()).isEqualTo("X|X");
    }

    @DisplayName("스트라이크 상태 메시지 비교 - 2번 투구")
    @Test
    void convertToStrike_3() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        assertThat(finalFrame.convertScoreToBowl()).isEqualTo("X|X|X");
    }

    @DisplayName("거터 상태 메시지 비교 - 1번 투구")
    @Test
    void convertToGutter_1() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(0);
        assertThat(finalFrame.convertScoreToBowl()).isEqualTo("-");
    }

    @DisplayName("거터 상태 메시지 비교 - 2번 투구")
    @Test
    void convertToGutter_2() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(0);
        finalFrame.bowl(0);
        assertThat(finalFrame.convertScoreToBowl()).isEqualTo("-|-");
    }

    @DisplayName("미스 상태 메시지 비교")
    @Test
    void convertToMiss() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(3);
        finalFrame.bowl(6);
        assertThat(finalFrame.convertScoreToBowl()).isEqualTo("3|6");
    }

    @DisplayName("스페어 상태 메시지 비교 - 2번 투구")
    @Test
    void convertToSpare_1() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(3);
        finalFrame.bowl(7);
        assertThat(finalFrame.convertScoreToBowl()).isEqualTo("3|/");
    }

    @DisplayName("스페어 상태 메시지 비교 - 3번 투구")
    @Test
    void convertToSpare_2() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(3);
        finalFrame.bowl(7);
        finalFrame.bowl(10);
        assertThat(finalFrame.convertScoreToBowl()).isEqualTo("3|/|X");
    }
}
