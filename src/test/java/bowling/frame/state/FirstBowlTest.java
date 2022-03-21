package bowling.frame.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class FirstBowlTest {

    @Test
    public void bowlWhenSpare() {
        FirstBowl firstBowl = new FirstBowl(Pins.bowl(9));
        State state = firstBowl.bowl(1);
        assertThat(state instanceof Spare).isTrue();
    }

    @Test
    public void bowlWhenMiss() throws Exception {
        FirstBowl firstBowl = new FirstBowl(Pins.bowl(9));
        State state = firstBowl.bowl(0);
        assertThat(state instanceof Miss).isTrue();
    }
}
