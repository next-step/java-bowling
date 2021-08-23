package bowling.domain.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import bowling.domain.Pins;
import org.junit.jupiter.api.Test;

class ReadyTest {
    @Test
    void 스트라이크_테스트() {
        Pins pins = Pins.pitching(10);
        Ready ready = new Ready();
        assertEquals(ready.nextPitch(pins), Strike.of(pins));
    }
}