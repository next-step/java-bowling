package bowling.domain.turn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class TurnStateTest {

    @DisplayName("현재 상태의 다음 상태를 구한다")
    @Test
    public void getNextTurnState_success() throws Exception {
        //given
        TurnState ready = TurnState.READY;
        TurnState first = TurnState.FIRST;
        TurnState second = TurnState.SECOND;

        //then
        assertThat(ready.getNextTurnState()).isEqualTo(TurnState.FIRST);
        assertThat(first.getNextTurnState()).isEqualTo(TurnState.SECOND);
        assertThat(second.getNextTurnState()).isEqualTo(TurnState.THIRD);
    }
}
