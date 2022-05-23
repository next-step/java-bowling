package bowling.domain;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({
            "5,5,20",
            "10,9,29",
            "10,10,30"})
    void addedScore_ForStrike(int firstPin, int secondPin, int result) {
        Score strike = Score.ofStrike();

        Score addedScore = strike.addedScore(Pin.of(firstPin))
                .addedScore(Pin.of(secondPin));

        assertThat(addedScore.getScore()).isEqualTo(result);
    }
}
