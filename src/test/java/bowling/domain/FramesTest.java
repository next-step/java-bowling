package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    void initialFrameWithEmpty() {
        Frames frames = new Frames();
        frames.scores()
                .stream()
                .peek(s -> assertThat(s).isEqualTo(new Score()));
        frames.subtotals()
                .stream()
                .peek(s -> assertThat(s).isEqualTo(new Subtotal(0, 2)));
    }

}
