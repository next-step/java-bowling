package bowling.pin.domain;

import bowling.ball.domain.Ball;
import bowling.state.domain.State;
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

    @Test
    @DisplayName("투구가 10이면 남은 Pin이 0이면, 스트라이크")
    void strike() {
        Ball ball = Ball.pitch("10", 1);
        result = Pin.pitchResult(ball.getPoint(), ball);
        assertThat(result.getRemainingPins()).isEqualTo(0);
        assertThat(result.getState().getResult()).isEqualTo(State.STRIKE.getResult());
    }

    @Test
    @DisplayName("투구가 8이면 남은 Pin은 2")
    void pitchIsEight() {
        Ball ball = Ball.pitch("8", 1);
        result = Pin.pitchResult(ball.getPoint(), ball);
        assertThat(result.getRemainingPins()).isEqualTo(2);
    }

    @Test
    @DisplayName("투구 횟수가 2이고 남은 Pin이 0이면, 스페어")
    void spare() {
        Ball ball = Ball.pitch("2", 1);
        result = Pin.pitchResult(10, ball);
        assertThat(result.getState().getResult()).isEqualTo(State.STRIKE.getResult());
    }

    @Test
    @DisplayName("투구 횟수가 1이고 쓰러트린 Pin이 없으면, 거터")
    void gutterCaseOne() {
        Ball ball = Ball.pitch("1", 1);
        result = Pin.pitchResult(0, ball);
        assertThat(result.getState().getResult()).isEqualTo(State.GUTTER.getResult());
    }

    @Test
    @DisplayName("투구 횟수가 1이고 쓰러트린 Pin이 있으면, 미쓰")
    void miss() {
        Ball ball = Ball.pitch("1", 1);
        result = Pin.pitchResult(2, ball);
        assertThat(result.getState().getResult()).isEqualTo(State.MISS.getResult());
    }

}
