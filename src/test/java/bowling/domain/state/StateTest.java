package bowling.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StateTest {

    @Test
    void 미스_생성_테스트() {
        assertThat(State.newState(BowlingPin.of(3))).isInstanceOf(Miss.class);
        assertThat(State.newState(BowlingPin.of(0), BowlingPin.of(9))).isInstanceOf(Miss.class);
        assertThat(State.newState(BowlingPin.of(9), BowlingPin.of(0))).isInstanceOf(Miss.class);
        assertThat(State.newState(BowlingPin.of(3), BowlingPin.of(6))).isInstanceOf(Miss.class);
    }

    @Test
    void 스페어_생성_테스트() {
        assertThat(State.newState(BowlingPin.of(0), BowlingPin.of(10))).isInstanceOf(Spare.class);
        assertThat(State.newState(BowlingPin.of(3), BowlingPin.of(7))).isInstanceOf(Spare.class);
    }

    @Test
    void 스트라이크_생성_테스트() {
        assertThat(State.newState(BowlingPin.of(10))).isInstanceOf(Strike.class);
    }
}
