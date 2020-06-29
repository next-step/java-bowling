package bowling.domain.score;

import bowling.domain.frame.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StrikeTest {

    @Test
    void nextScore() {
        Strike strike = new Strike();
        assertThat(strike.getScore()).isEqualTo("X");

        Score scoreTen = strike.nextScore(Point.inputPoint(10));
        assertThat(scoreTen.getScore()).isEqualTo("X");
    }

    @Test
    void isStrikeTest() {
        Strike strike = new Strike();
        assertThat(strike.isStrike()).isTrue();
    }

}
