package seul.bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {
    @Test
    void play() {
        Frames frames = new Frames();

        frames.play(10);
        frames.play(1);
        frames.play(2);
        frames.play(10);
        frames.play(9);
        frames.play(1);

        assertThat(frames.getFrames()).hasSize(4);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:FALSE", "12:TRUE"}, delimiter = ':')
    void end(int loop, boolean expected) {
        Frames frames = new Frames();

        for (int i = 0; i < loop; i++) {
            frames.play(10);
        }

        assertThat(frames.end()).isEqualTo(expected);
    }
}
