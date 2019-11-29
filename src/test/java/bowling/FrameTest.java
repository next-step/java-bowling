package bowling;

import bowling.domain.Frame;
import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    @DisplayName("객체 비교")
    void compareWithFrameObjectTest() {
        Frame frame = Frame.firstFrame(1);
        Frame frame1 = Frame.firstFrame(1);

        assertThat(frame).isEqualTo(frame1);
    }

    @Test
    @DisplayName("적중한 핀 객체 비교")
    void compareWithPinByFrame() {
        Frame frame = Frame.firstFrame(4);
        Pin pin = new Pin(4);

        assertThat(frame.hit()).isEqualTo(pin);
    }
}
