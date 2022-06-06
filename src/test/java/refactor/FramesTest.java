package refactor;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    void 마지막프레임은3번의기회를가져야함() {
        Frames frames = Frames.create();
        Frame last = Stream.iterate(frames.first(), frame -> frame.next())
                .limit(10)
                .skip(9)
                .findFirst()
                .orElseThrow();
        assertThat(last).isEqualTo(Frame.last());
    }
}
