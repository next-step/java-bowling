package bowling.domain.state.running;

import static org.junit.jupiter.api.Assertions.assertFalse;

import bowling.domain.score.Pin;
import bowling.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FirstBowlTest {

    @Test
    @DisplayName("첫번째 볼링은 종료가 되지 않아야 한다.")
    void isFinishedFalseTest() {

        // given
        State state = new FirstBowl(Pin.of(5));

        // when
        boolean result = state.isFinished();

        // then
        assertFalse(result);
    }
}