package refactor;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    void next는다음Frame을가리킨다() {
        Frame first = new FrameGeneral(new Scores(Arrays.asList(3, 4), 0), new Subtotal(State.INIT, 7));
        Frame second = new FrameGeneral(new Scores(Arrays.asList(2, 4), 0), new Subtotal(State.DONE, 16));
        Frames frames = new Frames(Arrays.asList(first, second));
        assertThat(frames.first()).isEqualTo(first);
        assertThat(frames.next(first)).isEqualTo(second);
    }
}
