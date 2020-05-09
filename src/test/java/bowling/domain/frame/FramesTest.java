package bowling.domain.frame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    public void framesInit() {
        Frames frames = Frames.init();

        assertThat(frames.size()).isEqualTo(10);
    }
}
