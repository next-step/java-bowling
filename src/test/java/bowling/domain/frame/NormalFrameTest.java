package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    private final int firstPins = 7;
    private final int secondPins = 3;
    private final int nextPins = 10;

    @Test
    @DisplayName("1/2차 투구 후 다음 프레임 생성")
    void nextFrame() {
        Frame first = NormalFrame.bowling(8, firstPins).bowling(secondPins);

        assertThat(first.next(nextPins)).isNotEqualTo(first);
    }

    @Test
    @DisplayName("9번째 프레임 종료 후, FinalFrame 생성")
    void finalNext() {
        Frame semiFinal = NormalFrame.bowling(9, firstPins).bowling(secondPins);

        assertThat(semiFinal.next(nextPins)).isInstanceOf(FinalFrame.class);
    }

    @Test
    @DisplayName("Strike 상태의 프레임일 때, 점수 계산")
    void strikeFrameScore() {
        NormalFrame strike = NormalFrame.bowling(1, 10);
        strike.next(5).bowling(4);

        assertThat(strike.score()).isEqualTo(19);
    }

    @Test
    @DisplayName("연속된 Strike 상태일 때 현재 프레임 점수 계산")
    void doubleStrikeScore() {
        NormalFrame strike = NormalFrame.bowling(1, 10);
        strike.next(10).next(10);

        assertThat(strike.score()).isEqualTo(30);
    }

    @Test
    @DisplayName("연속된 Strike, 마지막은 Miss / Spare 상태일 때 현재 프레임 점수 계산")
    void doubleStrikeAndMissOrSpareScore() {
        Frame strike = NormalFrame
                .bowling(1, 10)
                .next(10)
                .next(5)
                .bowling(5);

        assertThat(strike.score()).isEqualTo(25);
    }

    @Test
    @DisplayName("Spare 상태의 프레임일 때, 점수 계산")
    void spareFrameScore() {
        Frame spare = NormalFrame
                .bowling(1, 5)
                .bowling(5)
                .next(3)
                .bowling(3);

        assertThat(spare.score()).isEqualTo(13);
    }

    @Test
    @DisplayName("9번 째 프레임이 Strike, 최종 프레임 결과가 모두 Strike 일 때, 최종 점수 계산")
    void SemiFinalFrameStrikeScore_1() {
        Frame semiFinalFrame = NormalFrame.bowling(9, 10)
                .next(10)
                .bowling(10)
                .bowling(10);

        assertThat(semiFinalFrame.score()).isEqualTo(30);
    }

    @Test
    @DisplayName("9번 째 프레임이 Strike 일 때, 최종 점수 계산")
    void SemiFinalFrameStrikeScore_2() {
        Frame semiFinalFrame = NormalFrame.bowling(9, 10)
                .next(10)
                .bowling(5)
                .bowling(10);

        assertThat(semiFinalFrame.score()).isEqualTo(25);
    }


    @Test
    @DisplayName("9번 째 프레임이 Spare, 최종 프레임 결과가 모두 Strike 일 때, 최종 점수 계산")
    void SemiFinalFrameSpareScore_1() {
        Frame semiFinalFrame = NormalFrame.bowling(9, 5)
                .bowling(5)
                .next(10)
                .bowling(10)
                .bowling(10);

        assertThat(semiFinalFrame.score()).isEqualTo(30);
    }

    @Test
    @DisplayName("9번 째 프레임이 Spare 일 때, 최종 점수 계산")
    void SemiFinalFrameSpareScore_2() {
        Frame semiFinalFrame = NormalFrame.bowling(9, 5)
                .bowling(5)
                .next(3)
                .bowling(7)
                .bowling(10);

        assertThat(semiFinalFrame.score()).isEqualTo(20);
    }
}
