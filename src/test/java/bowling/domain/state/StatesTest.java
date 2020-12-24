package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created : 2020-12-24 오후 4:33
 * Developer : Seo
 */
class StatesTest {

    private States states;
    private int userIndex;

    @BeforeEach
    void setUp() {
        states = new States();
        userIndex = 0;
    }

    @Test
    void init() {
        assertThat(states.getState(userIndex)).isInstanceOf(None.class);
        assertThat(states.getState(userIndex + 1)).isInstanceOf(None.class);
    }

    @Test
    void getState() {
        states.add(new Strike());
        assertThat(states.size()).isEqualTo(1);
        assertThat(states.getState(userIndex)).isInstanceOf(Strike.class);
    }

    @Test
    void getLast() {
        states.add(new None());
        states.add(new Strike());
        assertThat(states.getLast()).isInstanceOf(Strike.class);
    }

    @Test
    void set() {
        states.add(new None());
        states.set(userIndex, new Strike());
        assertThat(states.getLast()).isInstanceOf(Strike.class);
    }
}
