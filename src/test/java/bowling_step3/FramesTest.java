package bowling_step3;

import bowling_step3.domain.Frame;
import bowling_step3.domain.Frames;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    void FrameAt10ShouldBeFrameLast() {
        Frames frames = Frames.create();
        Frame actual = Stream.iterate(frames.first(), frame -> frame.next())
                .skip(9)
                .findFirst()
                .orElseThrow();
        assertThat(actual.next()).isNull();
    }
}
