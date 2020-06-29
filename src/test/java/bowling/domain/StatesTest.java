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

        states.add(State.ONE, new Pin(1));

        assertThat(states.getStates().get(0)).isEqualTo(State.ONE);
    }

    @Test
    void getLastState() {
        States states = new States();

        assertThat(states.getLastState()).isEqualTo(State.READY);

        states.add(State.STRIKE, new Pin(Pin.MAX_PIN));
        states.add(State.SPARE, new Pin(Pin.MAX_PIN));

        assertThat(states.getLastState()).isEqualTo(State.SPARE);
    }

    @Test
    void isLastStateStrike() {
        States states = new States();

        states.add(State.SPARE, new Pin(Pin.MAX_PIN));
        states.add(State.STRIKE, new Pin(Pin.MAX_PIN));

        assertThat(states.isLastStateStrike()).isTrue();
    }

    @Test
    void getStatesPinSum() {
        States states = new States();

        states.add(State.ONE, new Pin(1));
        states.add(State.TWO, new Pin(2));

        assertThat(states.getStatesPinSum()).isEqualTo(3);
    }

    @Test
    void getBeforeState() {
        States states = new States();
        states.add(State.TWO, new Pin(2));
        states.add(State.STRIKE, new Pin(Pin.MAX_PIN));

        State actual = states.getBeforeState();

        assertThat(actual).isEqualTo(State.TWO);
    }

    @Test
    void calculateScore() {
        States states = new States();
        int inputScore = 7;
        Score score = Score.ofMiss(inputScore);

        Score actual = states.calculateScore(score);

        assertThat(actual.getScore()).isEqualTo(inputScore);
    }
}
