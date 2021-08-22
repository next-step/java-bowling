package bowling.frame;

import bowling.dto.StateDtos;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FramesTest {
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = Frames.init();
    }

    @Test
    void convert() {
        Assertions.assertThat(frames.convert()).filteredOn(StateDtos.class::isInstance);
    }
}
