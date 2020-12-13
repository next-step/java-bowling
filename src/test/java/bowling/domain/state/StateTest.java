package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.FallingPinCount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StateTest {

    private State state;

    @BeforeEach
    void setup() {
        state = InitState.getInstance();
    }

    @DisplayName("종료 상태반환 테스트")
    @Test
    void isDone() {
        assertThat(state.isDone()).isFalse();

        State strike = state.roll(FallingPinCount.fromFallingCount(10));
        assertThat(strike.isDone()).isTrue();

        State playing = state.roll(FallingPinCount.fromFallingCount(5));
        assertThat(playing.isDone()).isFalse();

        State spare = playing.roll(FallingPinCount.fromFallingCount(5));
        assertThat(spare.isDone()).isTrue();

        State open = playing.roll(FallingPinCount.fromFallingCount(4));
        assertThat(open.isDone()).isTrue();
    }
}