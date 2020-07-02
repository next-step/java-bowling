package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {

    @DisplayName("마지막 프레임 1 게임 없는 경우 파이널 false")
    @Test
    void isNotFinal() {
        //Pins pins = new Pins(new Pin(10, 1), new Pin(9, 9));
        //assertThat(pins.isFinal()).isFalse();
    }

    @DisplayName("마지막 프레임에서 1 게임 추가시 파이널 true 변화 체크")
    @Test
    void isFinal() {
        //Pins pins = new Pins(new Pin(10, 1), new Pin(9, 9));
        //assertThat(pins.isFinal()).isFalse();
        //pins.setThirdPin(new Pin(10,0));
        //assertThat(pins.isFinal()).isTrue();
    }

}
