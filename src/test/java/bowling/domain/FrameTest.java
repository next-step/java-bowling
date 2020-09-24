package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrameTest {

    private Frame frame() {
        return new Frame() {
            @Override
            void swing(int score) {
                addHistory(score, 0);
            }

            @Override
            boolean isEndedFrame() {
                return false;
            }
        };
    }

    @Test
    void swingHistoryToStringTest() {
        Frame frame = frame();
        frame.swing(0);
        assertThat(frame.swingHistoryToString()).isInstanceOf(String.class);
        assertEquals(frame.firstSwing(), 0);
    }
}
