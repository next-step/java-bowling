package bowling.domain.turn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {

    @DisplayName("현재 상태의 다음 상태를 구한다")
    @Test
    public void getNextTurnState_success() throws Exception {
        //given
        TurnState first = TurnState.FIRST;
        TurnState second = TurnState.SECOND;

        //then
        assertThat(first.getNextTurnState()).isEqualTo(TurnState.SECOND);
        assertThat(second.getNextTurnState()).isEqualTo(TurnState.THIRD);
    }
}
