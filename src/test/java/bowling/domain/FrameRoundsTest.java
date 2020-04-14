package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameRoundsTest {
    @Test
    void play() {
        FrameRounds frameRounds = new FrameRounds();

        frameRounds.play(9);
        frameRounds.play(1);

        assertThat(frameRounds.getFrameRounds()).hasSize(2);
        assertThat(frameRounds.getStatus()).isEqualTo(RoundsStatus.SPARE);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:TRUE:FALSE", "10:FALSE:TRUE", "8:FALSE:FALSE"}, delimiter = ':')
    void isEnd(int clearPinCount, boolean lastFrame, boolean expected) {
        FrameRounds frameRounds = new FrameRounds();
        frameRounds.play(clearPinCount);

        assertThat(frameRounds.isEnd(lastFrame)).isEqualTo(expected);
    }
}
