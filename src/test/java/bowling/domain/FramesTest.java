package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    void score() {
        Frames frames = new Frames();
        frames.pitch(1, 10);
        frames.pitch(2, 9);
        frames.pitch(2, 1);
        frames.pitch(3, 7);
        frames.pitch(3, 2);

        assertThat(frames.frameScores().get(1).score()).isEqualTo(new Score(20));
        assertThat(frames.frameScores().get(2).score()).isEqualTo(new Score(17));
        assertThat(frames.frameScores().get(3).score()).isEqualTo(new Score(9));
    }

    @Test
    void score_final_turkey() {
        Frames frames = new Frames();
        frames.pitch(10, 10);
        frames.pitch(10, 10);
        frames.pitch(10, 10);

        assertThat(frames.frameScores().get(10).score()).isEqualTo(new Score(30));
    }

    @Test
    void score_final_double() {
        Frames frames = new Frames();
        frames.pitch(10, 10);
        frames.pitch(10, 10);
        frames.pitch(10, 9);

        assertThat(frames.frameScores().get(10).score()).isEqualTo(new Score(29));
    }

    @Test
    void score_final_strike() {
        Frames frames = new Frames();
        frames.pitch(10, 10);
        frames.pitch(10, 5);
        frames.pitch(10, 2);

        assertThat(frames.frameScores().get(10).score()).isEqualTo(new Score(17));
    }

    @Test
    void score_final_spare() {
        Frames frames = new Frames();
        frames.pitch(10, 8);
        frames.pitch(10, 2);
        frames.pitch(10, 10);

        assertThat(frames.frameScores().get(10).score()).isEqualTo(new Score(20));
    }

    @Test
    void score_final_miss() {
        Frames frames = new Frames();
        frames.pitch(10, 6);
        frames.pitch(10, 2);

        assertThat(frames.frameScores().get(10).score()).isEqualTo(new Score(8));
    }
}
