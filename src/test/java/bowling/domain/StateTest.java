package bowling.domain;

import bowling.domain.state.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StateTest {

    @Test
    void strike_symbol() {
        State state = new Strike(Pin.of(10));

        assertThat(state).isInstanceOf(Strike.class);
        assertThat(state.toPins()).isEqualTo(Arrays.asList(Pin.of(10)));
        assertThat(state.getScore().toInt()).isEqualTo(10);
    }

    @Test
    void ready_symbol() {
        State state = new Ready().roll(8);

        assertThat(state).isInstanceOf(Hold.class);
        assertThat(state.toPins()).isEqualTo(Arrays.asList(Pin.of(8)));

        assertThatThrownBy(() -> state.getScore()).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void gutter_symbol() {
        State state = new Ready().roll(0);

        assertThat(state).isInstanceOf(Hold.class);
        assertThat(state.toPins()).isEqualTo(Arrays.asList(Pin.of(0)));

        assertThatThrownBy(() -> state.getScore()).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void spare_symbol() {
        State state = new Ready().roll(8);

        assertThat(state).isInstanceOf(Hold.class);
        assertThat(state.toPins()).isEqualTo(Arrays.asList(Pin.of(8)));

        State spare = state.roll(2);

        assertThat(spare).isInstanceOf(Spare.class);
        assertThat(spare.toPins()).isEqualTo(Arrays.asList(Pin.of(8), Pin.of(2)));
        assertThat(spare.getScore().toInt()).isEqualTo(10);
    }

    @Test
    void miss_symbol() {
        State state = new Ready().roll(8);

        assertThat(state).isInstanceOf(Hold.class);
        assertThat(state.toPins()).isEqualTo(Arrays.asList(Pin.of(8)));

        State miss = state.roll(1);

        assertThat(miss).isInstanceOf(Miss.class);
        assertThat(miss.toPins()).isEqualTo(Arrays.asList(Pin.of(8), Pin.of(1)));
        assertThat(miss.getScore().toInt()).isEqualTo(9);
    }

    @Test
    void miss_with_gutter_symbol() {
        State state = new Ready().roll(8);

        assertThat(state).isInstanceOf(Hold.class);
        assertThat(state.toPins()).isEqualTo(Arrays.asList(Pin.of(8)));

        State miss = state.roll(0);

        assertThat(miss).isInstanceOf(Miss.class);
        assertThat(miss.toPins()).isEqualTo(Arrays.asList(Pin.of(8), Pin.of(0)));
        assertThat(miss.getScore().toInt()).isEqualTo(8);
    }
}
