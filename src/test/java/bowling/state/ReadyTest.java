package bowling.state;

import bowling.Pins;
import bowling.state.running.FirstBowl;
import bowling.state.running.Ready;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @Test
    void 투구_시_First_Bowl_반환() {
        Throwing ready = new Ready();

        Throwing bowl = ready.bowl(new Pins(4));

        assertThat(bowl).isInstanceOf(FirstBowl.class);
    }
}