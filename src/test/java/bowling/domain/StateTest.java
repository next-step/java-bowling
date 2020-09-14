package bowling.domain;

import bowling.domain.state.Open;
import bowling.domain.state.Playing;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class StateTest {

    @Test
    void strike_symbol() {
        State state = State.from(10);
        assertThat(state).isInstanceOf(Strike.class);
        assertThat(state.value()).isEqualTo(Arrays.asList("X"));
    }

    @Test
    void playing_symbol() {
        State state = State.from(8);
        assertThat(state).isInstanceOf(Playing.class);
        assertThat(state.value()).isEqualTo(Arrays.asList("8"));
    }

    @Test
    void gutter_symbol() {
        State state = State.from(0);
        assertThat(state).isInstanceOf(Playing.class);
        assertThat(state.value()).isEqualTo(Arrays.asList("-"));
    }

    @Test
    void spare_symbol() {
        State state = State.from(8);
        assertThat(state).isInstanceOf(Playing.class);
        assertThat(state.value()).isEqualTo(Arrays.asList("8"));

        State spare = state.roll(2);

        assertThat(spare).isInstanceOf(Spare.class);
        assertThat(spare.value()).isEqualTo(Arrays.asList("8", "/"));
    }

    @Test
    void open_symbol() {
        State state = State.from(8);
        assertThat(state).isInstanceOf(Playing.class);
        assertThat(state.value()).isEqualTo(Arrays.asList("8"));

        State open = state.roll(1);

        assertThat(open).isInstanceOf(Open.class);
        assertThat(open.value()).isEqualTo(Arrays.asList("8", "1"));
    }

    @Test
    void open_with_gutter_symbol() {
        State state = State.from(8);
        assertThat(state).isInstanceOf(Playing.class);
        assertThat(state.value()).isEqualTo(Arrays.asList("8"));

        State open = state.roll(0);

        assertThat(open).isInstanceOf(Open.class);
        assertThat(open.value()).isEqualTo(Arrays.asList("8", "-"));
    }
}
