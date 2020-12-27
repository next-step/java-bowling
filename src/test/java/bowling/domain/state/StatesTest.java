package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-24 오후 4:33
 * Developer : Seo
 */
class StatesTest {
    private States states;
    private int playerIndex;

    @BeforeEach
    void setUp() {
        states = new States();
        playerIndex = 0;
    }

    @Test
    void init() {
        assertThat(states.getState(playerIndex)).isInstanceOf(None.class);
        assertThat(states.getState(playerIndex + 1)).isInstanceOf(None.class);
    }

    @Test
    void getState() {
        states.add(playerIndex, new Strike());
        assertThat(states.size()).isEqualTo(1);
        assertThat(states.getState(playerIndex)).isInstanceOf(Strike.class);
    }

    @Test
    void getLast() {
        states.add(playerIndex, new None());
        states.add(playerIndex + 1, new Strike());
        assertThat(states.getLast()).isInstanceOf(Strike.class);
    }

    @Test
    void set() {
        states.add(playerIndex, new None());
        states.set(playerIndex, new Strike());
        assertThat(states.getLast()).isInstanceOf(Strike.class);
    }
}
