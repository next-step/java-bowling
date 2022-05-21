package bowling.domain.state;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissTest {

    @ParameterizedTest
    @CsvSource({"7,4", "7,3", "9,2"})
    void validation(int firstNo, int secondNo) {
        assertThatThrownBy(() -> new Miss(firstNo, secondNo))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void score() {
        Miss miss = new Miss(5, 4);

        Score score = miss.score();

        assertThat(score.getScore()).isEqualTo(9);
    }

    @Test
    void additionalScore_ForStrike() {
        Score beforeScore = Score.ofStrike();
        Miss miss = new Miss(5, 4);

        Score afterScore = miss.additionalScore(beforeScore);

        assertThat(afterScore.getScore()).isEqualTo(19);
    }

    @Test
    void expression() {
        assertThat(new Miss(5, 4).expression()).isEqualTo("5|4");
    }
}
