package bowling.domain.engine.frame;

import bowling.domain.RollResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ScoreTest {

    @Test
    @DisplayName("스트라이크 상태에서 Score 를 생성하면 점수 계산을 즉시 할수는 없다.")
    void cannotCalculateImmediatelyInStrike() {
        Score strikeScore = Score.initStrikeScore();

        assertAll(
            () -> assertThat(strikeScore.isCalculationCompleted()).isFalse(),
            () -> assertThatThrownBy(strikeScore::getValue).isInstanceOf(IllegalStateException.class)
        );
    }

    @Test
    @DisplayName("스페어 상태에서 Score 를 생성하면 점수 계산을 즉시 할수는 없다.")
    void cannotCalculateImmediatelyInSpare() {
        Score spareScore = Score.initSpareScore();

        assertAll(
            () -> assertThat(spareScore.isCalculationCompleted()).isFalse(),
            () -> assertThatThrownBy(spareScore::getValue).isInstanceOf(IllegalStateException.class)
        );
    }

    @Test
    @DisplayName("Miss 상태에서 Score 를 생성하면 점수 계산도 즉시 할 수 있다.")
    void canCalculateImmediatelyInMiss() {
        Score score = Score.initReadyToUseScore(9);

        assertAll(
            () -> assertThat(score.isCalculationCompleted()).isTrue(),
            () -> assertThat(score.getValue()).isEqualTo(9)
        );
    }

    @Test
    @DisplayName("스트라이크 상태의 Score 는 두 번의 투구 결과를 필요로 한다.")
    void needTwoRollResultsForStrikeScore() {
        Score strikeScore = Score.initStrikeScore();
        strikeScore = strikeScore.add(RollResult.of(10));
        strikeScore = strikeScore.add(RollResult.of(3));

        assertThat(strikeScore.getValue()).isEqualTo(23);
    }

    @Test
    @DisplayName("스페어 상태의 Score 는 한 번의 투구 결과를 필요로 한다.")
    void needRollResultForStrikeScore() {
        Score spareStrike = Score.initSpareScore();
        spareStrike = spareStrike.add(RollResult.of(5));

        assertThat(spareStrike.getValue()).isEqualTo(15);
    }

}
