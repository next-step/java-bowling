package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {

    @DisplayName("Pins 생성 테스트")
    @Test
    void createTest() {
        List<Pin> pins = Arrays.asList(Pin.of(1), Pin.of(2), Pin.of(3));
        assertThat(pins).containsExactly(Pin.of(1), Pin.of(2), Pin.of(3));
        assertThat(pins.size()).isEqualTo(3);
    }
}