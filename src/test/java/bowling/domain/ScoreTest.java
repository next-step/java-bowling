package bowling.domain;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    void canGetScore() {
        assertThat(Score.ofMiss(8).canGetScore()).isTrue();
        assertThat(Score.ofSpare().canGetScore()).isFalse();
        assertThat(Score.ofStrike().canGetScore()).isFalse();
    }

    @Test
    void addedScore_ForSpare() {
        Score spare = Score.ofSpare();

        Score addedScore = spare.addedScore(Pin.of(5));

        assertThat(addedScore.getScore()).isEqualTo(15);
    }

    @Test
    void addedScore_ForStrike() {
        Score strike = Score.ofStrike();

        Score addedScore = strike.addedScore(Pin.of(5))
                .addedScore(Pin.of(5));

        assertThat(addedScore.getScore()).isEqualTo(20);
    }
}
