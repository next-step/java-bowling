package bowling.domain.score;

import bowling.domain.exception.BowlingBuildingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameScoreTest {

    @DisplayName("Miss 결과를 가지는 frameScore 생성")
    @Test
    public void makeMissFrameScore_정상() {
        FrameScore frameScore = FrameScore.ofMiss(9);

        assertThat(frameScore.getFrameScore()).isEqualTo(9);
    }

    @DisplayName("Spare 결과를 가지는 frameScore 생성")
    @Test
    public void makeSpareFrameScore_정상() {
        FrameScore frameScore = FrameScore.ofSpare();

        assertThat(frameScore.getFrameScore()).isEqualTo(10);
    }

    @DisplayName("Strike 결과를 가지는 frameScore 생성")
    @Test
    public void makeStrikeFrameScore_정상() {
        FrameScore frameScore = FrameScore.ofStrike();

        assertThat(frameScore.getFrameScore()).isEqualTo(10);
    }

    @DisplayName("이전 FrameScore 객체를 통해 다음 frameScore를 생성")
    @Test
    public void makeNextFrameScore_정상() {
        FrameScore frameScore = FrameScore.ofSpare();

        FrameScore nextFrameScore = frameScore.next(5);

        assertThat(nextFrameScore.getFrameScore()).isEqualTo(15);
    }

    @DisplayName("더이상 점수 집계에 관여할 투구가 없는데 next를 통해 점수 객체를 생성하는 경우 예외 발생")
    @Test
    public void makeNextFrameScore_예외() {
        FrameScore frameScore = FrameScore.ofMiss(5);

        assertThatThrownBy(() -> {
            frameScore.next(5);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_SCORE_CALCULATION);
    }

    @DisplayName("Miss 결과를 가지는 frameScore는 점수 집계에 관여할 다음 투구가 없어 바로 계산이 가능")
    @Test
    public void isAvailableToCalculate_Miss_True() {
        FrameScore frameScore = FrameScore.ofMiss(9);

        assertThat(frameScore.isAvailableToCalculate()).isTrue();
    }

    @DisplayName("Spare나 Strike의 frameScore는 점수 집계에 관여할 다음 투구가 있어 바로 계산이 불가능")
    @Test
    public void isAvailableToCalculate_Miss_False() {
        FrameScore spare = FrameScore.ofSpare();
        FrameScore strike = FrameScore.ofStrike();

        assertThat(spare.isAvailableToCalculate()).isFalse();
        assertThat(strike.isAvailableToCalculate()).isFalse();
    }

}
