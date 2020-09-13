package bowling.pin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {

    private Pin pin;
    private Pins pins;
    private List<Pin> pinList;

    @BeforeEach
    void setUp() {
        pinList = new ArrayList<>();
    }

    @Test
    @DisplayName("2번의 투구")
    void pitch() {
        for (int i = 1; i <= 2; i++) {
            pins = Pins.eachPitchResult(pinList, "5", i);
        }
        assertThat(pins.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("투구가 8이면 남은 Pin은 2")
    void pitchIsEight() {
        pins = Pins.eachPitchResult(pinList, "8", 1);
        pin = pins.getPinsIndex(0);
        assertThat(pin.getRemainingPins()).isEqualTo(2);
    }

    @Test
    @DisplayName("투구가 10이면 남은 Pin이 0이면, 스트라이크")
    void strike() {
        pins = Pins.eachPitchResult(pinList, "10", 1);
        pin = pins.getPinsIndex(0);
        assertThat(pin.getRemainingPins()).isEqualTo(0);
        assertThat(pin.getState()).isEqualTo("X");
    }

    @Test
    @DisplayName("투구 횟수가 2이고 남은 Pin이 0이면, 스페어")
    void spare() {
        for (int i = 1; i <= 2; i++) {
            pins = Pins.eachPitchResult(pinList, "5", i);
        }
        Pin pin = pins.getPinsIndex(1);
        String state = pin.getState();
        assertThat(state).isEqualTo("/");
    }

    @Test
    @DisplayName("투구 횟수가 1이고 쓰러트린 Pin이 없으면, 거터")
    void gutterCaseOne() {
        pins = Pins.eachPitchResult(pinList, "0", 1);
        pin = pins.getPinsIndex(0);
        assertThat(pin.getState()).isEqualTo("-");
    }

    @Test
    @DisplayName("투구 횟수가 2이고 쓰러트린 Pin이 없으면, 거터")
    void gutterCaseTwo() {
        pins = Pins.eachPitchResult(pinList, "5", 1);
        Pins.eachPitchResult(pinList, "0", 2);

        pin = pins.getPinsIndex(1);
        assertThat(pin.getState()).isEqualTo("-");
    }

    @Test
    @DisplayName("투구 횟수가 1이고 쓰러트린 Pin이 있으면, 미쓰")
    void miss() {
        pins = Pins.eachPitchResult(pinList, "1",1);
        pin = pins.getPinsIndex(0);
        assertThat(pin.getState()).isEqualTo(String.valueOf(1));
    }

}
