package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ScoresTest {

    @Test
    void shouldAddPins() {
        Scores scores = new Scores();
        scores.add(1);
        scores.add(4);

        assertThat(scores.getScores()).containsExactly(new Score(1), new Score(4));
    }

    @Test
    void shouldReturnNormalRoundEndOrNot_whenNonStrike() {
        Scores scores = new Scores();
        scores.add(1);

        assertThat(scores.isNormalRoundEnd()).isFalse();

        scores.add(5);
        assertThat(scores.isNormalRoundEnd()).isTrue();
    }

    @Test
    void shouldReturnNormalRoundEndOrNot_whenStrike() {
        Scores scores = new Scores();
        scores.add(10);

        assertThat(scores.isNormalRoundEnd()).isTrue();
    }

    @Test
    void shouldReturnLastRoundEndOrNot() {
        Scores scores = new Scores();
        scores.add(1);
        scores.add(2);

        assertThat(scores.isLastRoundEnd()).isFalse();

        scores.add(10);
        assertThat(scores.isLastRoundEnd()).isTrue();
    }

    @Test
    void shouldValidateSecondSpare() {
        validateSecondSpare(0, 10);
        validateSecondSpare(4, 6);
        validateSecondSpare(9, 1);

    }

    private void validateSecondSpare(int firstScore, int secondScore) {
        Scores scores = new Scores();
        scores.add(firstScore);
        scores.add(secondScore);

        assertThat(scores.isSecondPinSpare()).isTrue();
    }

    @Test
    void shouldValidateThirdSpare() {
        validateThirdSpare(0, 10);
        validateThirdSpare(4, 6);
        validateThirdSpare(9, 1);
    }

    private void validateThirdSpare(int secondScore, int thirdScore) {
        Scores scores = new Scores();
        scores.add(5);
        scores.add(secondScore);
        scores.add(thirdScore);

        assertThat(scores.isThirdPinSpare()).isTrue();
    }

}
