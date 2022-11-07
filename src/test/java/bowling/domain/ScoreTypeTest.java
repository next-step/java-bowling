package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTypeTest {

    @DisplayName("Strike 계산")
    @Test
    void score_type_strike() {

        final Score score = new Score();
        score.add(new Pin(10));

        assertThat(ScoreType.STRIKE.matches(score)).isTrue();
    }

    @DisplayName("Spare 계산")
    @ParameterizedTest(name = "첫번째 넘어진 개수는 {0}이고, 두번째 넘어진 개수는 {1}이다.")
    @CsvSource(value = {"0,10", "5,5", "2,8"})
    void score_type_spare(final int one, final int two) {

        final Score score = new Score();
        score.add(new Pin(one));
        score.add(new Pin(two));

        assertThat(ScoreType.SPARE.matches(score)).isTrue();
    }

    @DisplayName("Miss 계산")
    @ParameterizedTest(name = "첫번째 넘어진 개수는 {0}이고, 두번째 넘어진 개수는 {1}이다.")
    @CsvSource(value = {"5,3", "0,0", "1,8"})
    void score_type_miss(final int one, final int two) {

        final Score score = new Score();
        score.add(new Pin(one));
        score.add(new Pin(two));

        assertThat(ScoreType.MISS.matches(score)).isTrue();
    }
}