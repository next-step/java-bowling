package bowling.domain.score;

import bowling.domain.frame.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NormalTest {

    @Test
    void nextScore_스페어처리() {
        Normal normal = new Normal(Point.inputPoint(1));
        Score spare = normal.nextScore(Point.inputPoint(9));
        assertThat(spare.getScore()).isEqualTo("/");
    }


    @Test
    void nextScore_거터() {
        Normal normal = new Normal(Point.inputPoint(1));
        Score spare = normal.nextScore(Point.inputPoint(0));
        assertThat(spare.getScore()).isEqualTo("-");
    }
}
