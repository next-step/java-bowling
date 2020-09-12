package bowling.pin.domain;

import bowling.state.domain.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {

    private Pins pins;
    private Pin result;

    @BeforeEach
    void setUp() {
        Pins.pins.clear();
    }

    @Test
    @DisplayName("2번의 투구")
    void pitch() {
        for (int i = 1; i <= 2; i++) {
            pins = Pins.playPitch("5", i);
        }
        assertThat(pins.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("투구가 8이면 남은 Pin은 2")
    void pitchIsEight() {
        pins = Pins.playPitch("8", 1);
        result = pins.getPinsIndex(0);
        assertThat(result.getRemainingPins()).isEqualTo(2);
    }

    @Test
    @DisplayName("투구가 10이면 남은 Pin이 0이면, 스트라이크")
    void strike() {
        pins = Pins.playPitch("10", 1);
        result = pins.getPinsIndex(0);
        assertThat(result.getRemainingPins()).isEqualTo(0);
        assertThat(result.getState().getResult()).isEqualTo(State.STRIKE.getResult());
    }

    @Test
    @DisplayName("투구 횟수가 2이고 남은 Pin이 0이면, 스페어")
    void spare() {
        for (int i = 1; i <= 2; i++) {
            pins = Pins.playPitch("5", i);
        }
        Pin pin = pins.getPinsIndex(1);
        String state = pin.getState().getResult();
        assertThat(state).isEqualTo(State.SPARE.getResult());
    }

    @Test
    @DisplayName("투구 횟수가 1이고 쓰러트린 Pin이 없으면, 거터")
    void gutterCaseOne() {
        pins = Pins.playPitch("0", 1);
        result = pins.getPinsIndex(0);
        assertThat(result.getState().getResult()).isEqualTo(State.GUTTER.getResult());
    }

    @Test
    @DisplayName("투구 횟수가 2이고 쓰러트린 Pin이 없으면, 거터")
    void gutterCaseTwo() {
        Pins.playPitch("5", 1);
        pins = Pins.playPitch("0", 2);
        result = pins.getPinsIndex(1);
        assertThat(result.getState().getResult()).isEqualTo(State.GUTTER.getResult());
    }

    @Test
    @DisplayName("투구 횟수가 1이고 쓰러트린 Pin이 있으면, 미쓰")
    void miss() {
        pins = Pins.playPitch("1", 1);
        result = pins.getPinsIndex(0);
        assertThat(result.getState().getResult()).isEqualTo(State.MISS.getResult());
    }

}
