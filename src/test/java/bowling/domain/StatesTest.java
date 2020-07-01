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

    @Test
    void getStatesPinSum() {
        States states = new States();

        states.add(State.ONE);
        states.add(State.TWO);

        assertThat(states.getStatesPinSum()).isEqualTo(3);
    }

    @Test
    void getBeforeState() {
        States states = new States();
        states.add(State.TWO);
        states.add(State.STRIKE);

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

    @Test
    void getLastPin() {
        States states = new States();
        states.add(State.STRIKE);
        states.add(State.SEVEN);

        Pin actual = states.getLastPin();

        assertThat(actual).isEqualTo(new Pin(7));
    }

    @Test
    void getBeforePin() {
        States states = new States();
        states.add(State.TWO);
        states.add(State.SPARE);

        Pin actual = states.getBeforePin();

        assertThat(actual).isEqualTo(new Pin(2));
    }
}
