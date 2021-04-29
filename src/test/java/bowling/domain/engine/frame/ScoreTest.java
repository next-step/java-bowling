package bowling.domain.engine.frame;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.Score.UnavailableScore;
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
        strikeScore = strikeScore.inject(RollResult.of(10));
        strikeScore = strikeScore.inject(RollResult.of(3));

        assertThat(strikeScore.getValue()).isEqualTo(23);
    }

    @Test
    @DisplayName("스페어 상태의 Score 는 한 번의 투구 결과를 필요로 한다.")
    void needRollResultForStrikeScore() {
        Score spareStrike = Score.initSpareScore();
        spareStrike = spareStrike.inject(RollResult.of(5));

        assertThat(spareStrike.getValue()).isEqualTo(15);
    }

    @Test
    @DisplayName("UnavailableScore 는 점수 계산 관련 인터페이스를 사용할 수 없다.")
    void cannotUseInterfacesOfUnavailableScore() {
        UnavailableScore score = UnavailableScore.init();
        assertAll(
            () -> assertThatThrownBy(() -> score.inject(RollResult.of(10))).isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(score::getValue).isInstanceOf(IllegalStateException.class)
        );
    }

    @Test
    @DisplayName("UnavailableScore 는 항상 점수 계산이 완료되지 않은 상태로 남아있다.")
    void alwaysUnableCompleteScore() {
        assertThat(UnavailableScore.init().isCalculationCompleted()).isFalse();
    }


}
