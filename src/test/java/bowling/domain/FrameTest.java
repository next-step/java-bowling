package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrameTest {

    private Frame mockFrame() {
        return new Frame() {
            @Override
            public void swing(int score) {
                addHistory(score);
            }

            @Override
            public boolean isEndedFrame() {
                return false;
            }

            @Override
            public int getScore() {
                return 0;
            }

            @Override
            public boolean isSpare() {
                return false;
            }

            @Override
            public boolean isStrike() {
                return false;
            }

            @Override
            public boolean isNotSwing() {
                return false;
            }
        };
    }

    @Test
    void swingHistoryToStringTest() {
        Frame frame = mockFrame();
        frame.swing(0);
        assertThat(frame.swingHistoryToString()).isInstanceOf(String.class);
        assertEquals(frame.firstSwing(), 0);
    }
}
