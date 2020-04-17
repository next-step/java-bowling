package bowling.domain.frame.state;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {
    @DisplayName("STRIKE 상태를 갖고올 수 있다.")
    @Test
    void strike() {
        String expect = "X";

        State actual = new Strike();

        assertThat(actual.toResult()).isEqualTo(expect);
    }

    @DisplayName("STRIKE 점수를 갖고올 수 있다.")
    @Test
    void getScore() {
        Calculable actual = new Strike();

        assertThat(actual.getScore()).isEqualTo(new Score(10, 2));
    }
}