package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    void 최초투구() {
        Frames frames = Frames.init();
        frames.bowling(10);

        Frame frame = frames.get(0);
        Bowling bowling = frame.get(0);

        assertThat(bowling).isEqualTo(Bowling.of(10));
    }

    @Test
    void 다음투구() {
        Frames frames = Frames.init();
        frames.bowling(10);

        Frame frame = frames.get(0);
        Bowling bowling = frame.get(0);

        assertThat(bowling).isEqualTo(Bowling.of(10));
    }
}