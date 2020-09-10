package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    @Test
    void constructor() {
        Frame frame = Frame.create();
        assertThat(frame.getPinCount()).isEqualTo(Frame.PIN_COUNT);
    }


}
