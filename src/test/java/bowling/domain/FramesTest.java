package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = Frames.init();
    }

    @Test
    void bowlTest() {
        frames.bowl(10);

        assertThat(frames.get(0).isOver()).isTrue();
    }
}
