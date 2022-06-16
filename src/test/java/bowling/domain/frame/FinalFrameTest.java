package bowling.domain.frame;

import bowling.domain.Score;
import bowling.exception.CannotCalculateScore;
import bowling.exception.NotCreateFrameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    private Frame finalFrame;

    @BeforeEach
    void setUp() throws Exception {
        finalFrame = NormalFrame.initialize();
        for (int i = 1; i < 10; i++) {
            finalFrame.bowling(10);
            finalFrame = finalFrame.next();
        }
    }

    @Test
    @DisplayName("10 Frame 은 finalFrame 이다.")
    void finalFrame() throws Exception {
        Frame finalFrame = NormalFrame.initialize();
        for (int i = 1; i < 10; i++) {
            finalFrame.bowling(10);
            finalFrame = finalFrame.next();
        }

        assertThat(finalFrame.frameNo().toInt()).isEqualTo(10);
        assertThat(finalFrame).isInstanceOf(FinalFrame.class);
    }


    @Test
    @DisplayName("첫 투구에서 스트라이크 시 프레임이 종료되지 않는다.")
    void finishFrame_strike() {
        finalFrame.bowling(10);

        assertThat(finalFrame.isFinish()).isFalse();
    }

    @Test
    @DisplayName("두번째 투구에서 스페어 시 프레임이 종료되지 않는다.")
    void finishFrame_spare() {
        finalFrame.bowling(5);
        finalFrame.bowling(5);

        assertThat(finalFrame.isFinish()).isFalse();
    }

    @Test
    @DisplayName("두번째 투구에서 MIss 시 프레임이 종료된다.")
    void finishFrame_miss() {
        finalFrame.bowling(5);
        finalFrame.bowling(3);

        assertThat(finalFrame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임일 경우 다음 프레임을 생성하면 예외를 반환한다.")
    void invalidNext() {
        assertThatThrownBy(() -> finalFrame.next())
                .isInstanceOf(NotCreateFrameException.class)
                .hasMessage("다음 프레임을 생성할 수 없습니다. 현재 프레임: 10");
    }

    @Test
    @DisplayName("Strike 의 경우 점수는 총 3번의 투구 개수의 합이다.")
    void score_strike() {
        finalFrame.bowling(10);
        finalFrame.bowling(5);
        finalFrame.bowling(5);

        assertThat(finalFrame.score()).isEqualTo(Score.of(20, 0));
    }

    @Test
    @DisplayName("Spare 의 경우 점수는 총 3번의 투구 개수의 합이다.")
    void score_spare() {
        finalFrame.bowling(3);
        finalFrame.bowling(7);
        finalFrame.bowling(4);

        assertThat(finalFrame.score()).isEqualTo(Score.of(14, 0));
    }

    @Test
    @DisplayName("Miss 의 경우 프레임의 점수는 {firstHit} + {secondHit} 이다.")
    void score_miss() {
        finalFrame.bowling(3);
        finalFrame.bowling(6);

        assertThat(finalFrame.score()).isEqualTo(Score.of(9, 0));
    }

    @Test
    @DisplayName("프레임이 종료되지 않을 경우 예외를 반환한다.")
    void score_empty() {
        finalFrame.bowling(5);

        assertThatThrownBy(() -> finalFrame.score()).isInstanceOf(CannotCalculateScore.class);
    }
}