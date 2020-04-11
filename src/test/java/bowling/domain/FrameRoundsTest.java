package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameRoundsTest {
    @ParameterizedTest
    @CsvSource(value = {"9:NONE", "10:STRIKE"}, delimiter = ':')
    void of(int value, RoundsStatus expected) {
        FrameRounds frameRounds = FrameRounds.of(value);

        assertThat(frameRounds.getFrameRounds()).hasSize(1);
        assertThat(frameRounds.getStatus()).isEqualTo(expected);
    }

    @Test
    void play() {
        FrameRounds frameRounds = FrameRounds.of(1);

        frameRounds.play(9);

        assertThat(frameRounds.getFrameRounds()).hasSize(2);
        assertThat(frameRounds.getStatus()).isEqualTo(RoundsStatus.SPARE);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:TRUE", "8:FALSE"}, delimiter = ':')
    void isEnd(int value, boolean expected) {
        FrameRounds frameRounds = FrameRounds.of(value);

        assertThat(frameRounds.isEnd()).isEqualTo(expected);
    }
}
