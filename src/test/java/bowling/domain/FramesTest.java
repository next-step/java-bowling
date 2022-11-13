package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    void 최초투구() {
        Frames frames = Frames.init();

        frames.bowling(10);
        Bowling bowling = frames.get(0).get(0);

        assertThat(bowling).isEqualTo(Bowling.of(10));
        assertThat(frames.getCurrentRound()).isEqualTo(0);
    }

    @Test
    void 다음투구() {
        Frames frames = Frames.init();

        frames.bowling(10);
        frames.bowling(5);
        Bowling bowling = frames.get(1).get(0);

        assertThat(bowling).isEqualTo(Bowling.of(5));
        assertThat(frames.getCurrentRound()).isEqualTo(1);
    }
}