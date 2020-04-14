package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    @Test
    void roundPlay() {
        Frame frame = Frame.fistFrame(9);

        frame.roundPlay(1);

        assertThat(frame.getFrameRounds().getFrameRounds()).hasSize(2);
        assertThat(frame.getFrameRounds().getStatus()).isEqualTo(RoundsStatus.SPARE);
    }

    @Test
    void fistFrame() {
        Frame frame = Frame.fistFrame(10);

        assertThat(frame.getFrameRounds().getFrameRounds()).hasSize(1);
        assertThat(frame.getFrameRounds().getStatus()).isEqualTo(RoundsStatus.STRIKE);
    }

    @Test
    void next() {
        Frame firstFrame = Frame.fistFrame(10);

        Frame secondFrame = firstFrame.next(8);

        assertThat(secondFrame.getFrameIndex()).isEqualTo(firstFrame.getFrameIndex() + 1);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:TRUE", "8:FALSE"}, delimiter = ':')
    void isEndFrame(int value, boolean expected) {
        Frame frame = Frame.fistFrame(value);

        assertThat(frame.isEndFrame()).isEqualTo(expected);
    }
}
