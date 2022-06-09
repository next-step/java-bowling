package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StateTest {
    @Test
    void decreaseWait은State를한단계씩감소() {
        assertThat(State.WAIT_TWICE.decreaseWait()).isEqualTo(State.WAIT_ONCE);
    }

    @Test
    void waiting은WAIT_ONCE혹은WAIT_TWICE인경우만true() {
        assertThat(State.WAIT_ONCE.waiting()).isTrue();
        assertThat(State.WAIT_TWICE.waiting()).isTrue();
        assertThat(State.DONE.waiting()).isFalse();
        assertThat(State.INIT.waiting()).isFalse();
    }
}
