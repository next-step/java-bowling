package bowling;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class FrameTest {
    @Test
    void test() {
        Frame frame = new Frame();
        frame.setKnockDownPins(10);
        assertThat(frame.getStatus()).isEqualTo("X");
    }
}
