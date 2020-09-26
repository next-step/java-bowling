package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrameTest {

    private Frame frame() {
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
            public String getScore() {
                return "";
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
