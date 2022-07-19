package bowling.domain.frame;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrame2Test {

    @Test
    void fallenPinsGiven_subtractPins() {
        Frame2 frame = new NormalFrame2();
        frame.subtractPins(4);

        assertThat(frame).isEqualTo(new NormalFrame2(6, List.of(4)));
    }
}
