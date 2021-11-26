package bowling.domain;

import bowling.domain.value.Pin;
import bowling.domain.value.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("볼링핀 테스트")
class PinsTest {

    @Test
    @DisplayName("정상적으로 핀 생성")
    void create() {
        List<Pin> pins = Arrays.asList(Pin.from(3), Pin.from(7));
        assertDoesNotThrow(
                () -> Pins.from(pins));
    }
}
