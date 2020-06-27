package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameScoreTest {

    @DisplayName("Miss일 때의 FrameScore 계산이 가능함")
    @Test
    public void makeMissFrameScore_정상() {
        FrameScore frameScore = FrameScore.ofMiss(9);

        assertThat(frameScore.isAbleToCalculate()).isTrue();
    }

    @DisplayName("Strike일 때 FrameScore는 바로 계산이 불가능함")
    @Test
    public void makeStrikeFrameScore_정상() {
        FrameScore frameScore = FrameScore.ofStrike();

        assertThat(frameScore.isAbleToCalculate()).isFalse();
    }

    @DisplayName("Spare일 때 FrameScore는 바로 계산이 불가능함")
    @Test
    public void makeSpareFrameScore_정상() {
        FrameScore frameScore = FrameScore.ofSpare();

        assertThat(frameScore.isAbleToCalculate()).isFalse();
    }

    @DisplayName("next를 통해 FrameScore를 생성하면 남은투구회수가 1차감됨")
    @Test
    public void next_차감_확인() {
        FrameScore frameScore = FrameScore.ofSpare();

        assertThat(frameScore.isAbleToCalculate()).isFalse();

        FrameScore nextFrameScore = frameScore.next(5);

        assertThat(nextFrameScore.isAbleToCalculate()).isTrue();
    }
}
