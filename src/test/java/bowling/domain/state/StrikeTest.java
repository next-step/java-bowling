package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StrikeTest {
    @Test
    @DisplayName("Strike일 때 Score는 Score(10, 1)이다.")
    void test1() {
        State state = new Strike(Point.of(10));
        assertThat(state.score()).isEqualTo(new Score(Point.of(10), 2));
    }

    @Test
    @DisplayName("Score(10, 1) 추가점수까지의 합을 구하면 Score(20, 0)를 반환한다.")
    void test2() {
        State state = new Strike(Point.of(10));
        assertThat(state.addExtraPoint(new Score(Point.of(10), 1)))
                .isEqualTo(new Score(Point.of(20), 0));
    }
}
