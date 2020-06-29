package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    @Test
    void hasThirdPin() {
        Frame frame = Frame.of(new Pin(10, 10),
                Frame.of(new Pin(10, 10), new Pin(0, 0)));
        assertThat(frame instanceof FinalFrame).isTrue();
    }

}
