package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {

    @Test
    void frame_size_test() {
        List<Frame> frames = Arrays.asList(Frame.first());

        assertThatThrownBy(() -> new Frames(frames))
            .isInstanceOf(IllegalArgumentException.class);
    }

}
