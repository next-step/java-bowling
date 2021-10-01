package bowling.domain.state.running;

import static org.junit.jupiter.api.Assertions.assertFalse;

import bowling.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    @DisplayName("현재 상태가 종료되었는지 확인할 수 있다.")
    void isFinishedTest() {

        // given
        State state = new Ready();

        // when
        boolean result = state.isFinished();

        // then
        assertFalse(result);
    }
}