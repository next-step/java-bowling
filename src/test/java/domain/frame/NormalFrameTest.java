package domain.frame;

import domain.Pins;
import domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private NormalFrame frame;

    @BeforeEach
    void setUp() {
        frame = new NormalFrame();
    }

    @Test
    void strike() {
        State state = frame.setKnockedDownPins(Pins.ALL);
        assertThat(state instanceof Strike).isTrue();
    }

    @Test
    void spares() {
        State state = frame.setKnockedDownPins(Pins.of(5)).bowl(Pins.of(5));
        assertThat(state instanceof Spares).isTrue();
    }

    @Test
    void open() {
        State state = frame.setKnockedDownPins(Pins.of(5)).bowl(Pins.of(4));
        assertThat(state instanceof Open).isTrue();
    }

    @Test
    void Ongoing() {
        State state = frame.setKnockedDownPins(Pins.of(5));
        assertThat(state instanceof Ongoing).isTrue();
    }
}