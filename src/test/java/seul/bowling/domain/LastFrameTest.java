package seul.bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seul.bowling.domain.status.Spare;

import static org.assertj.core.api.Assertions.assertThat;

public class LastFrameTest {
    private Frame frame;

    @BeforeEach
    void setUp() {
        this.frame = Frame.first();
        for (int i = 1; i < 10; i++) {
            this.frame = this.frame.next();
        }
    }

    @Test
    void addPins() {
        LastFrame lastFrame = (LastFrame) frame;

        lastFrame.addPins(9);
        lastFrame.addPins(1);

        assertThat(lastFrame.getStatus().getPins().allClear()).isTrue();
        assertThat(lastFrame.getStatus().getClass()).isEqualTo(Spare.class);
        assertThat(lastFrame.getScore()).isEqualTo(10);
    }

    @Test
    void endFrame() {
        frame.addPins(1);
        frame.addPins(9);

        assertThat(frame.endFrame()).isFalse();
    }
}
