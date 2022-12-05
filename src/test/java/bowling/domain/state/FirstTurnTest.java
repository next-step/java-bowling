package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstTurnTest {
    @Test
    @DisplayName("SPARE TEST: 10점을 채우면 Spare로 변한다.")
    void test1() {
        State state = new FirstTurn(Point.of(3));
        state = state.bowl(Point.of(7));
        assertThat(state).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("MISS TEST: 10점을 못 채우면 Miss로 변한다.")
    void test2() {
        State state = new FirstTurn(Point.of(3));
        state = state.bowl(Point.of(4));
        assertThat(state).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("이 상태에 10이 들어있고 Score(10, 1)이 들어오면 Score(20, 0)이 된다.")
    void test3() {
        State state = new FirstTurn(Point.of(10));
        Score score = state.addExtraPoint(new Score(Point.of(10), 1));
        assertThat(score).isEqualTo(new Score(Point.of(20), 0));
    }

    @Test
    @DisplayName("이 상태에 10이 들어있고 Score(10, 0)이 들어오면 Score(10, 0)이 된다.")
    void test4() {
        State state = new FirstTurn(Point.of(10));
        Score score = state.addExtraPoint(new Score(Point.of(10), 0));
        assertThat(score).isEqualTo(new Score(Point.of(10), 0));
    }

}
