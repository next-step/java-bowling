package bowling.pin.domain;

import bowling.ball.domain.Ball;
import bowling.state.domain.State;
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
            pin = Pin.pitchResult(pinList, Ball.pitch("5", i));
            pins = Pins.eachPitchResult(pinList, pin);
        }
        assertThat(pins.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("투구가 8이면 남은 Pin은 2")
    void pitchIsEight() {
        pin = Pin.pitchResult(pinList, Ball.pitch("8", 1));
        pins = Pins.eachPitchResult(pinList, pin);
        pin = pins.getPinsIndex(0);
        assertThat(pin.getRemainingPins()).isEqualTo(2);
    }

    @Test
    @DisplayName("투구가 10이면 남은 Pin이 0이면, 스트라이크")
    void strike() {
        pin = Pin.pitchResult(pinList, Ball.pitch("10", 1));
        pins = Pins.eachPitchResult(pinList, pin);
        pin = pins.getPinsIndex(0);
        assertThat(pin.getRemainingPins()).isEqualTo(0);
        assertThat(pin.getState().getResult()).isEqualTo(State.STRIKE.getResult());
    }

    @Test
    @DisplayName("투구 횟수가 2이고 남은 Pin이 0이면, 스페어")
    void spare() {
        for (int i = 1; i <= 2; i++) {
            pin = Pin.pitchResult(pinList, Ball.pitch("5", i));
            pins = Pins.eachPitchResult(pinList, pin);
        }
        Pin pin = pins.getPinsIndex(1);
        String state = pin.getState().getResult();
        assertThat(state).isEqualTo(State.SPARE.getResult());
    }

    @Test
    @DisplayName("투구 횟수가 1이고 쓰러트린 Pin이 없으면, 거터")
    void gutterCaseOne() {
        pin = Pin.pitchResult(pinList, Ball.pitch("0", 1));
        pins = Pins.eachPitchResult(pinList, pin);
        pin = pins.getPinsIndex(0);
        assertThat(pin.getState().getResult()).isEqualTo(State.GUTTER.getResult());
    }

    @Test
    @DisplayName("투구 횟수가 2이고 쓰러트린 Pin이 없으면, 거터")
    void gutterCaseTwo() {
        pin = Pin.pitchResult(pinList, Ball.pitch("5", 1));
        pins = Pins.eachPitchResult(pinList, pin);
        Pin result2 = Pin.pitchResult(pinList, Ball.pitch("0", 2));
        Pins.eachPitchResult(pinList, result2);

        pin = pins.getPinsIndex(1);
        assertThat(pin.getState().getResult()).isEqualTo(State.GUTTER.getResult());
    }

    @Test
    @DisplayName("투구 횟수가 1이고 쓰러트린 Pin이 있으면, 미쓰")
    void miss() {
        pin = Pin.pitchResult(pinList, Ball.pitch("1", 1));
        pins = Pins.eachPitchResult(pinList, pin);
        pin = pins.getPinsIndex(0);
        assertThat(pin.getState().getResult()).isEqualTo(State.MISS.getResult());
    }

}
