package camp.nextstep.edu.nextstep8.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    @DisplayName("점수가 10점을 넘을 경우 예외 발생")
    @Test
    public void validateRangeTest() {
        assertThatThrownBy(() -> {
            new Score(11);
        });
    }

    @DisplayName("Spare 점수를 포함하여 10점을 넘을 제대로 동작하는지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "5:5:false",
            "1:9:false",
            "5:6:true"
    }, delimiter = ':')
    public void exceedMaxScoreTest(int score, int spare, boolean expected) {
        // given
        Score givenScore = new Score(score);

        // when
        boolean result = givenScore.exceedMaxScore(spare);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("점수가 10점 이하일 경우, spare 와의 합산 점수가 최대 점수를 넘지 않는지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "1:false",
            "3:false",
            "5:true",
            "6:false"
    }, delimiter = ':')
    public void meetMaxScoreTest(int spare, boolean expected) {
        // given
        Score score = new Score(5);

        // when
        boolean result = score.meetMaxScore(spare);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
