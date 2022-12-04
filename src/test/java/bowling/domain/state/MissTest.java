package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MissTest {
    @Test
    @DisplayName("3, 4를 가지고 있다면 Score(7, 0)을 반환한다.")
    void test1() {
        State state = new Miss(Point.of(3), Point.of(4));
        assertThat(state.score()).isEqualTo(new Score(Point.of(7), 0));
    }

    @Test
    @DisplayName("3, 4를 가지고 있을때 Score(4, 1)을 넣으면 추가점수를 더해 Score(7, 0)을 반환한다.")
    void test2() {
        State state = new Miss(Point.of(3), Point.of(4));
        assertThat(state.addExtraPoint(new Score(Point.of(4), 1)))
                .isEqualTo(new Score(Point.of(7), 0));
    }

}
