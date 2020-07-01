package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @DisplayName("볼링 3회 = 파이널 투구 프레임")
    @Test
    void hasThirdPin() {
        Frame frame = Frame.of(new Pins(new Pin(10, 0), new Pin(10, 0)));
        assertThat(frame instanceof FinalFrame).isFalse();
        frame.pins.setThirdPin(new Pin(10, 0));
        Frame finalFrame = Frame.of(frame);
        assertThat(finalFrame instanceof FinalFrame).isTrue();
    }

}
