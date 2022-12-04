package bowling.domain.state;

import bowling.domain.Point;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpareTest {
    @Test
    @DisplayName("3, 7을 가진 스페어의 Score는 Score(10, 1)이다.")
    void test1() {
        State state = new Spare(Point.of(3), Point.of(7));
        assertThat(state.score()).isEqualTo(new Score(Point.of(10), 1));
    }

    @Test
    @DisplayName("3, 6을 가진 스페어에서 Score(10, 2) 추가점수까지의 합을 구하면 Score(19, 0)를 반환한다.")
    void test2() {
        State state = new Spare(Point.of(3), Point.of(6));
        assertThat(state.addExtraPoint(new Score(Point.of(10), 2)))
                .isEqualTo(new Score(Point.of(19), 0));
    }
}
