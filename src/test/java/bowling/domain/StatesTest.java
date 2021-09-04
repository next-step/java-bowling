package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class StatesTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        States states = States.of(Arrays.asList(State.GUTTER, State.NORMAL));

        assertThat(states).isEqualTo(States.of(Arrays.asList(State.GUTTER, State.NORMAL)));
    }

    @DisplayName("Frame bowl 시 States 하나씩 쌓이는지 테스트")
    @Test
    void add() {
        States states = States.of(Arrays.asList(State.GUTTER, State.NORMAL));

        Frame frame = new NormalFrame(1);
        frame.bowl(Pins.of(0));

        frame.bowl(Pins.of(3));

        assertThat(states).isEqualTo(frame.getStates());
    }

    @DisplayName("getState 테스트")
    @Test
    void getState() {
        Frame frame = new FinalFrame();
        frame.bowl(Pins.of(0));
        frame.bowl(Pins.of(10));
        frame.bowl(Pins.of(5));

        States states = frame.getStates();

        assertThat(states.getFirstPitch()).isEqualTo(State.GUTTER);
        assertThat(states.getSecondPitch()).isEqualTo(State.SPARE);
        assertThat(states.getBonusPitch()).isEqualTo(State.NORMAL);
    }
}
