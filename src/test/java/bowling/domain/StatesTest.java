package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("States 클래스 테스트")
public class StatesTest {

    @Test
    void create() {
        assertThatCode(States::new).doesNotThrowAnyException();
    }

    @Test
    void add() {
        States states = new States();

        states.add(State.ONE);

        assertThat(states.getStates().get(0)).isEqualTo(State.ONE);
    }

    @Test
    void getLastState() {
        States states = new States();

        assertThat(states.getLastState()).isEqualTo(State.READY);

        states.add(State.STRIKE);
        states.add(State.SPARE);

        assertThat(states.getLastState()).isEqualTo(State.SPARE);
    }

    @Test
    void isLastStateStrike() {
        States states = new States();

        states.add(State.SPARE);
        states.add(State.STRIKE);

        assertThat(states.isLastStateStrike()).isTrue();
    }
}
