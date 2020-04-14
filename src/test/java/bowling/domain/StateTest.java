package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StateTest {

    @DisplayName("턴과 남은 핀의 개수를 이용해 상태를 구한다")
    @Test
    public void checkStatus_success() throws Exception {
        //given
        State strike = State.checkStatue(Tern.FIRST, 0);
        State spare = State.checkStatue(Tern.SECOND, 0);
        State miss1 = State.checkStatue(Tern.FIRST, 5);
        State miss2 = State.checkStatue(Tern.SECOND, 5);
        State gutter = State.checkStatue(Tern.SECOND, 10);

        //then
        assertThat(strike).isEqualTo(State.STRIKE);
        assertThat(spare).isEqualTo(State.SPARE);
        assertThat(miss1).isEqualTo(State.MISS);
        assertThat(miss2).isEqualTo(State.MISS);
        assertThat(gutter).isEqualTo(State.GUTTER);
    }
}
