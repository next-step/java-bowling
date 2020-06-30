package bowling.domain.state;

import bowling.domain.frame.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalTest {

    @Test
    void nextScore_스페어처리() {
        Normal normal = new Normal(Point.inputPoint(1));
        State spare = normal.nextScore(Point.inputPoint(9));
        assertThat(spare.getScore()).isEqualTo("/");
    }


    @Test
    void nextScore_거터() {
        Normal normal = new Normal(Point.inputPoint(1));
        State spare = normal.nextScore(Point.inputPoint(0));
        assertThat(spare.getScore()).isEqualTo("-");
    }
}
