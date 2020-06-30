package bowling.domain.state;

import bowling.domain.frame.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    void nextScore() {
        Strike strike = new Strike();
        assertThat(strike.getScore()).isEqualTo("X");

        State stateTen = strike.nextScore(Point.inputPoint(10));
        assertThat(stateTen.getScore()).isEqualTo("X");
    }

    @Test
    void isStrikeTest() {
        Strike strike = new Strike();
        assertThat(strike.isStrike()).isTrue();
    }

}
