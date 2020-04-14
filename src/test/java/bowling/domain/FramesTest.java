package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    void play() {
        Frames frames = new Frames();

        frames.play(10);
        frames.play(1);
        frames.play(9);
        frames.play(8);
        frames.play(1);

        assertThat(frames.getFrames()).hasSize(3);
        assertThat(frames.getFrames().get(0).getFrameRounds().getStatus()).isEqualTo(RoundsStatus.STRIKE);
        assertThat(frames.getFrames().get(1).getFrameRounds().getStatus()).isEqualTo(RoundsStatus.SPARE);
        assertThat(frames.getFrames().get(2).getFrameRounds().getStatus()).isEqualTo(RoundsStatus.MISS);
    }

    @Test
    void isEnd_false() {
        Frames frames = new Frames();

        assertThat(frames.isEnd()).isFalse();
    }

    @Test
    void isEnd_true() {
        Frames frames = new Frames();
        frames.play(10);
        frames.play(10);
        frames.play(10);
        frames.play(10);
        frames.play(10);
        frames.play(10);
        frames.play(10);
        frames.play(10);
        frames.play(10);
        frames.play(9);
        frames.play(1);

        assertThat(frames.isEnd()).isTrue();
    }

    @Test
    void getFrameNumber() {
        Frames frames = new Frames();

        frames.play(10);
        frames.play(9);

        assertThat(frames.getFrameNumber()).isEqualTo(2);
    }
}
