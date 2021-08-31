package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PinTest {
    @DisplayName("생성 테스트")
    @Test
    void create(){
        Pin pin = Pin.of(4);

        assertThat(pin).isEqualTo(Pin.of(4));
    }

    @DisplayName("남은 핀 테스트")
    @Test
    void leftPin(){
        Pin pin = Pin.of(4);

        assertThat(pin.leftPin()).isEqualTo(Pin.of(6));
    }
}
