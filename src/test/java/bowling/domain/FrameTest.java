package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    @Test
    void roundPlay() {
        Frame frame = Frame.fistFrame();

        frame.roundPlay(1);

        assertThat(frame.getFrameRounds().getFrameRounds()).hasSize(1);
        assertThat(frame.getFrameRounds().getScoreStatus().getStatus()).isEqualTo(RoundsStatus.NONE);
    }

    @Test
    void fistFrame() {
        Frame frame = Frame.fistFrame();

        assertThat(frame.getFrameRounds().getFrameRounds()).hasSize(0);
        assertThat(frame.getFrameRounds().getScoreStatus().getStatus()).isEqualTo(RoundsStatus.NONE);
    }

    @Test
    void next() {
        Frame firstFrame = Frame.fistFrame();

        Frame secondFrame = firstFrame.next();

        assertThat(secondFrame.getFrameIndex()).isEqualTo(firstFrame.getFrameIndex() + 1);
    }

    @Test
    void updateBonus() {
        Frame frame = Frame.fistFrame();
        frame.roundPlay(10);

        frame.updateBonus(10);

        assertThat(frame.getFrameRounds().getScoreStatus().getScore().getTotalScore()).isEqualTo(20);
    }

    @Test
    void endCalculate() {
        Frame firstFrame = Frame.fistFrame();
        firstFrame.roundPlay(10);
        Frame secondFrame = firstFrame.next();
        secondFrame.roundPlay(10);
        firstFrame.updateBonus(10);
        Frame thirdFrame = secondFrame.next();
        thirdFrame.roundPlay(10);
        firstFrame.updateBonus(10);

        assertThat(firstFrame.endCalculate()).isTrue();
        assertThat(secondFrame.endCalculate()).isFalse();
    }

    @Test
    void addScore() {
        Frame firstFrame = Frame.fistFrame();
        firstFrame.roundPlay(10);
        firstFrame.addScore(10);

        assertThat(firstFrame.getTotalScore()).isEqualTo(20);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:TRUE", "8:FALSE"}, delimiter = ':')
    void isEndFrame(int value, boolean expected) {
        Frame frame = Frame.fistFrame();
        frame.roundPlay(value);

        assertThat(frame.isEndFrame()).isEqualTo(expected);
    }

    @Test
    void availableBonus() {
        Frame frame = Frame.fistFrame();
        frame.roundPlay(10);

        assertThat(frame.availableBonus()).isTrue();
    }
}
