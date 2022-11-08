package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @DisplayName("계산할 수 있는지 여부를 반환한다")
    @ParameterizedTest
    @CsvSource(value = {
            "1,false",
            "0,true"
    })
    void canCalculate(int leftTries, boolean expected) {
        assertThat(new Score(10, leftTries).canCalculate()).isEqualTo(expected);
    }

    @DisplayName("투구 후 점수를 반환한다")
    @Test
    void bowl() {
        Score score = new Score(10, 1);

        assertThat(score.bowl(9)).isEqualTo(new Score(19, 0));
    }
}
