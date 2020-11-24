package bowling.frame.state;

import bowling.score.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class FinalTest {

    private Pin currentPins;
    private State state;

    @Test
    @DisplayName("투구 종료 여부 확인 - 종료")
    void isFinished() {
        currentPins = Pin.bowl("5");
        state = Final.from(currentPins);
        assertThat(state.isFinish()).isTrue();
    }

    @Test
    @DisplayName("볼링 결과 - 스트라이크")
    void currentPinsIsStrike() {
        currentPins = Pin.bowl("10");
        state = Final.from(currentPins);
        assertThat(state instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("Final 에서 투구 시 Exception 발생")
    void cantBowl() {
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> state = Final.from(Pin.bowl("5"))
                                                .bowl(Pin.bowl("5")));
    }
}
