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
        Frame frame = new Frame(new Pin(1), 1);
        Frame frame1 = new Frame(new Pin(1), 1);

        assertThat(frame).isEqualTo(frame1);
    }
}
