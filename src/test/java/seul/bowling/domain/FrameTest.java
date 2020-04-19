package seul.bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    @Test
    void addPins() {
        Frame frame = Frame.first();

        frame.addPins(9);
        frame.addPins(1);

        assertThat(frame.getPins().allClear()).isTrue();
        assertThat(frame.getResult().getStatus()).isEqualTo(FrameStatus.SPARE);
        assertThat(frame.getResult().getScore().getScore()).isEqualTo(10);
    }

    @Test
    void next() {
        Frame firstFrame = Frame.first();

        Frame secondFrame = firstFrame.next();

        assertThat(secondFrame.getIndex()).isEqualTo(1);
    }

    @Test
    void next_lastFrame() {
        Frame frame = Frame.first();
        for (int i = 1; i < 10; i++) {
            frame = frame.next();
        }

        assertThat(frame.equals(new LastFrame(9))).isTrue();
    }

    @Test
    void first() {
        Frame frame = Frame.first();

        int index = frame.getIndex();

        assertThat(index).isEqualTo(0);
    }

    @Test
    void endFrame() {
        Frame frame = Frame.first();

        frame.addPins(1);
        frame.addPins(9);

        assertThat(frame.endFrame()).isTrue();
    }

    @Test
    void addBonusScore() {
        Frame frame = Frame.first();
        frame.addPins(10);

        frame.addBonusScore(10);

        Score score = frame.getResult().getScore();
        assertThat(score.getScore()).isEqualTo(20);
    }
}
