package bowling.domain.score;

import bowling.domain.frame.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SpareTest {

    @Test
    void nextScore() {
        Spare spare = new Spare(Point.inputPoint(2));
        assertThat(spare.getScore()).isEqualTo("/");
    }

    @Test
    void isSpare() {
        Spare spare = new Spare(Point.inputPoint(2));
        assertThat(spare.isSpare()).isTrue();
    }
}
