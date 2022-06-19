package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalTest {
    private NormalFrame frame;

    @BeforeEach
    void setUp() {
        frame = new NormalFrame(1);
    }

    @Test
    void pinsGiven_addPin() {
        frame.addPins(3);
        assertThat(frame.pins().first()).isEqualTo(3);
    }

    @Test
    void tryNoIs0Given_validateMoveToNext() {
        frame = new NormalFrame(1, 0, new Pins(List.of(1,3)));
        assertThat(frame.validateMoveToNextIndex()).isEqualTo(2);
    }
}
