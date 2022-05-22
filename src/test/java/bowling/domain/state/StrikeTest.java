package bowling.domain.state;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrikeTest {

    @Test
    void score() {
        Score strikeScore = new Strike().score();

        assertThatThrownBy(strikeScore::getScore)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void additionalScore_ForSpare() {
        Score beforeScore = Score.ofSpare();
        Strike strike = new Strike();

        Score afterScore = strike.additionalScore(beforeScore);

        assertThat(afterScore.getScore()).isEqualTo(20);
    }
}
