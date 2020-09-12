package bowling.pin.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinTest {

    private Pin result;

    @Test
    @DisplayName("새로 세팅되는 볼링핀이 10개인지 확인")
    void checkPin() {
        assertThat(Pin.initPins).isEqualTo(10);
    }



}
