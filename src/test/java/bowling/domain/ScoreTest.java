package bowling.domain;

import bowling.domain.score.FinishedScore;
import bowling.domain.score.Score;
import bowling.domain.score.UnDefinedScore;
import bowling.domain.score.UnFinishedScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class ScoreTest {

    @Test
    void create_finished_score() {
        int score = 5;
        Score finishedScore = FinishedScore.of(score);

        assertThat(finishedScore.currentScore()).isEqualTo(score);
        assertThat(finishedScore.isNecessaryToCalculateMore()).isFalse();
        assertThat(finishedScore.isFullyCalculated()).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                finishedScore.calculatedScore(score));
    }

    @Test
    void create_empty_undefined_score() {
        Score finishedScore = UnDefinedScore.ofEmpty();

        assertThat(finishedScore.currentScore()).isEqualTo(0);
        assertThat(finishedScore.isNecessaryToCalculateMore()).isFalse();
        assertThat(finishedScore.isFullyCalculated()).isFalse();
        assertThatIllegalStateException().isThrownBy(() ->
                finishedScore.calculatedScore(4));
    }

    @Test
    void create_not_empty_undefined_score() {
        int score = 5;
        Score finishedScore = UnDefinedScore.of(score);

        assertThat(finishedScore.currentScore()).isEqualTo(score);
        assertThat(finishedScore.isNecessaryToCalculateMore()).isFalse();
        assertThat(finishedScore.isFullyCalculated()).isFalse();
        assertThatIllegalStateException().isThrownBy(() ->
                finishedScore.calculatedScore(score));
    }

    @Test
    void create_strike_unfinished_score() {
        int strikeScore = 10;
        Score score = UnFinishedScore.ofStrike(strikeScore);

        assertThat(score.currentScore()).isEqualTo(strikeScore);
        assertThat(score.isNecessaryToCalculateMore()).isTrue();
        assertThat(score.isFullyCalculated()).isFalse();
    }

    @Test
    @DisplayName("strike score 두번 추가 계산후 정상적으로 값이 나오는지 테스트")
    void strike_unfinished_score_calculated_score() {
        int strikeScore = 10;
        int firstToAddScore = 5;
        int secondToAddScore = 5;

        Score score = UnFinishedScore.ofStrike(strikeScore);
        Score calculatedScore = score.calculatedScore(firstToAddScore);
        Score finalCalculatedScore = calculatedScore.calculatedScore(secondToAddScore);

        assertThat(finalCalculatedScore.currentScore()).isEqualTo(strikeScore + firstToAddScore + secondToAddScore);
        assertThat(finalCalculatedScore.isNecessaryToCalculateMore()).isFalse();
        assertThat(finalCalculatedScore.isFullyCalculated()).isTrue();
    }

    @Test
    @DisplayName("strike score 두번 초과 계산후 예외 발생하는지 테스트")
    void strike_unfinished_score_calculated_score_more_than_twice_throws_exception() {
        int strikeScore = 10;
        int firstToAddScore = 5;
        int secondToAddScore = 5;
        int thirdAddScore = 5;

        Score score = UnFinishedScore.ofStrike(strikeScore);
        Score calculatedScore = score.calculatedScore(firstToAddScore);
        Score finalCalculatedScore = calculatedScore.calculatedScore(secondToAddScore);

        assertThatIllegalStateException().isThrownBy(() ->
                finalCalculatedScore.calculatedScore(thirdAddScore));
    }

    @Test
    void create_spare_unfinished_score() {
        int spareScore = 10;
        Score score = UnFinishedScore.ofSpare(spareScore);

        assertThat(score.currentScore()).isEqualTo(spareScore);
        assertThat(score.isNecessaryToCalculateMore()).isTrue();
        assertThat(score.isFullyCalculated()).isFalse();
    }


    @Test
    @DisplayName("spare score 한번 추가 계산후 정상적으로 값이 나오는지 테스트")
    void spare_unfinished_score_calculated_score() {
        int spareScore = 10;
        int firstToAddScore = 5;

        Score score = UnFinishedScore.ofSpare(spareScore);
        Score calculatedScore = score.calculatedScore(firstToAddScore);

        assertThat(calculatedScore.currentScore()).isEqualTo(spareScore + firstToAddScore);
        assertThat(calculatedScore.isNecessaryToCalculateMore()).isFalse();
        assertThat(calculatedScore.isFullyCalculated()).isTrue();
    }

    @Test
    @DisplayName("spare score 한번 초과 계산후 예외 발생하는지 테스트")
    void spare_unfinished_score_calculated_score_more_than_once_throws_exception() {
        int strikeScore = 10;
        int firstToAddScore = 5;
        int secondToAddScore = 5;

        Score score = UnFinishedScore.ofSpare(strikeScore);
        Score calculatedScore = score.calculatedScore(firstToAddScore);

        assertThatIllegalStateException().isThrownBy(() ->
                calculatedScore.calculatedScore(secondToAddScore));
    }
}
