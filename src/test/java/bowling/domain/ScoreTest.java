package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @ParameterizedTest
    @CsvSource({"-1", "11"})
    void shouldValidateScore(int score) {
        assertThatThrownBy(() -> new Score(score))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({"0,true", "5,false"})
    void shouldValidateGutter(int score, boolean expectedResult) {
        assertThat(new Score(score).isGutter()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({"10,true", "5,false", "0,false"})
    void shouldValidateStrike(int score, boolean expectedResult) {
        assertThat(new Score(score).isStrike()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({"5,5,10", "2,0,2", "3,7,10"})
    void shouldSumScore(int scoreA, int scoreB, int sumScore) {
        assertThat(new Score(scoreA).sum(new Score(scoreB))).isEqualTo(new Score(sumScore));
    }

}
