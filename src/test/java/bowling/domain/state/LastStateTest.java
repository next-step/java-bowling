package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;
import bowling.exception.AlreadyFinishedException;
import bowling.exception.DoNotHaveEnoughPointsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LastStateTest {
    @Test
    @DisplayName("이미 종료되었다면 예외를 던진다.")
    void test1() {
        State state = new LastState();
        state.bowl(Point.of(10));
        state.bowl(Point.of(10));
        state.bowl(Point.of(10));
        assertThatThrownBy(() -> {
            state.bowl(Point.of(1));
        }).isInstanceOf(AlreadyFinishedException.class);
    }

    @Test
    @DisplayName("종료되지 않았다면 자기자신을 반환한다.")
    void test2() {
        State state = new LastState();
        state = state.bowl(Point.of(10));
        assertThat(state).isEqualTo(state);
    }

    @Test
    @DisplayName("score 호출시 아직 종료되지 않았다면 예외를 던진다.")
    void test3() {
        State state = new LastState();
        state = state.bowl(Point.of(10));
        assertThatThrownBy(state::score)
                .isInstanceOf(DoNotHaveEnoughPointsException.class);
    }

    @Test
    @DisplayName("score 호출시 종료되었다면 포인트 합의 스코어를 반환한다.")
    void test4() {
        State state = new LastState();
        state.bowl(Point.of(10));
        state.bowl(Point.of(10));
        state.bowl(Point.of(10));
        assertThat(state.score()).isEqualTo(new Score(Point.of(30), 0));
    }

    @Test
    @DisplayName("10을 가지고 있는 상태에서 Score(10, 1)에 추가점수를 주면 Score(20, 0)을 반환한다.")
    void test5() {
        State state = new LastState();
        state = state.bowl(Point.of(10));
        assertThat(state.addExtraPoint(new Score(Point.of(10), 1)))
                .isEqualTo(new Score(Point.of(20), 0));
    }

}
