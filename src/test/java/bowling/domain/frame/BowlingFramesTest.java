package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlingFramesTest {

    @DisplayName("frame이 10개가 아니면 예외가 발생한다.")
    @Test
    void frame_size() {
        List<Frame> frames = Arrays.asList(Frame.first());

        assertThatThrownBy(() -> new BowlingFrames(frames))
            .isInstanceOf(IllegalArgumentException.class);
    }

}
