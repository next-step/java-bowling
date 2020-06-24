package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Pin 클래스 테스트")
public class PinTest {

    @Test
    void create() {
        int fallenPin = 10;
        Pin pin = new Pin(fallenPin);

        assertThat(pin).isEqualTo(new Pin(fallenPin));
    }
}
