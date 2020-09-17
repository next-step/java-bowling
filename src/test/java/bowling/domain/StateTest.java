package bowling.domain;

import bowling.domain.state.Hold;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StateTest {

    @Test
    void strike_symbol() {
        State state = State.from(10);

        assertThat(state).isInstanceOf(Strike.class);
        assertThat(state.toValues()).isEqualTo(Arrays.asList("X"));
        assertThat(state.getScore().toInt()).isEqualTo(10);
    }

    @Test
    void playing_symbol() {
        State state = State.from(8);

        assertThat(state).isInstanceOf(Hold.class);
        assertThat(state.toValues()).isEqualTo(Arrays.asList("8"));

        assertThatThrownBy(() -> state.getScore()).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void gutter_symbol() {
        State state = State.from(0);

        assertThat(state).isInstanceOf(Hold.class);
        assertThat(state.toValues()).isEqualTo(Arrays.asList("-"));

        assertThatThrownBy(() -> state.getScore()).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void spare_symbol() {
        State state = State.from(8);

        assertThat(state).isInstanceOf(Hold.class);
        assertThat(state.toValues()).isEqualTo(Arrays.asList("8"));

        State spare = state.roll(2);

        assertThat(spare).isInstanceOf(Spare.class);
        assertThat(spare.toValues()).isEqualTo(Arrays.asList("8", "/"));
        assertThat(spare.getScore().toInt()).isEqualTo(10);
    }

    @Test
    void open_symbol() {
        State state = State.from(8);

        assertThat(state).isInstanceOf(Hold.class);
        assertThat(state.toValues()).isEqualTo(Arrays.asList("8"));

        State open = state.roll(1);

        assertThat(open).isInstanceOf(Miss.class);
        assertThat(open.toValues()).isEqualTo(Arrays.asList("8", "1"));
        assertThat(open.getScore().toInt()).isEqualTo(9);
    }

    @Test
    void open_with_gutter_symbol() {
        State state = State.from(8);

        assertThat(state).isInstanceOf(Hold.class);
        assertThat(state.toValues()).isEqualTo(Arrays.asList("8"));

        State open = state.roll(0);

        assertThat(open).isInstanceOf(Miss.class);
        assertThat(open.toValues()).isEqualTo(Arrays.asList("8", "-"));
        assertThat(open.getScore().toInt()).isEqualTo(8);
    }
}
