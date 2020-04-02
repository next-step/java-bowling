package bowling.domain.frame;

import bowling.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingTest {

    @Test
    @DisplayName("볼링 상태 사이즈")
    void getSizeTest() {

        Bowling bowling = new Bowling();
        bowling.bowl(5);
        bowling.bowl(2);

        bowling.bowl(2);
        bowling.bowl(3);

        LinkedList<State> bowlingStates = bowling.getStates();
        assertThat(bowlingStates.size()).isEqualTo(4);
    }
}
