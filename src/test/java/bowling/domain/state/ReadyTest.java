package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @Test
    @DisplayName("상태 값이 없을 시 객체 비교")
    void compareToReady() {
        State state = new Ready();
        State state2 = new Ready();

        boolean same = state.equals(state2);

        assertThat(same).isFalse();
    }

    @Test
    @DisplayName("스트라이크 상태 확인")
    void checkReadyToStrike() {
        State state = new Ready();
        State strike = state.bowl(10);

        assertThat(strike instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("다음 투구 준비 상태")
    void checkReadyToNextReady() {
        State state = new Ready();
        State nextState = state.bowl(7);

        assertThat(nextState instanceof NextReady).isTrue();
    }
}
