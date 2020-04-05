package bowling.domain.frame;

import bowling.domain.player.Player;
import bowling.domain.state.Pin;
import bowling.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingTest {

    @Test
    @DisplayName("볼링 상태 사이즈")
    void getSizeTest() {

        Bowling bowling = new Bowling(new LinkedList<>(), new Player("KSJ"));
        bowling.bowl(new Pin(5));
        bowling.bowl(new Pin(2));

        bowling.bowl(new Pin(2));
        bowling.bowl(new Pin(3));

        LinkedList<State> bowlingStates = bowling.getStates();
        assertThat(bowlingStates.size()).isEqualTo(4);
    }
}
