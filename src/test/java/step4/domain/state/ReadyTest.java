package step4.domain.state;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {
    @DisplayName("첫번째 시도에 모든 공을 쓰러뜨리면 strike 상태가 된다.")
    @Test
    void readyToStrike() {
        State state = new Ready();
        state = state.throwBowl(10);
        Assertions.assertThat(state).isEqualTo(new Strike());
    }

    @DisplayName("첫번째 시도에 모든 공을 쓰러뜨리지 못하면 firstBowl 상태가 된다.")
    @Test
    void readyToFirstBowl() {
        State state = new Ready();
        state = state.throwBowl(9);
        Assertions.assertThat(state).isEqualTo(new FirstBowl(9));
    }

    @DisplayName("첫번째 시도에 공을 11개 이상 쓰러뜨릴 경우 예외 발생")
    @Test
    void readyToException() {
        State state = new Ready();
        Assertions.assertThatThrownBy(() -> {
            state.throwBowl(11);
        }).isInstanceOf(IllegalArgumentException.class);

    }
}