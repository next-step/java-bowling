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
}
