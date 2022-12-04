package bowling.domain.state;

import bowling.domain.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReadyTest {
    @Test
    @DisplayName("10개를 전부 넘어뜨리면 다음 상태는 Strike이다.")
    void test1() {
        State state = new Ready();
        assertThat(state.bowl(Point.of(10))).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("10개를 전부 넘어뜨리지 못하면 다음 상태는 FirstTurn")
    void test2() {
        State state = new Ready();
        assertThat(state.bowl(Point.of(1))).isInstanceOf(FirstTurn.class);
    }
}
