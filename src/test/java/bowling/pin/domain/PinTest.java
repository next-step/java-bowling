package bowling.pin.domain;

import bowling.ball.domain.Ball;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinTest {

    @Test
    @DisplayName("새로 세팅되는 볼링핀이 10개인지 확인")
    void checkPin() {
        assertThat(Pin.firstPin).isEqualTo(10);
    }

    @Test
    @DisplayName("투구가 10이면 남은 Pin은 0. 스트라이크")
    void strike() {
        Ball ball = Ball.pitch("10");
        int result = Pin.eachPitch(ball.getPoint());
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("투구가 8이면 남은 Pin은 2")
    void pitchIsEight() {
        Ball ball = Ball.pitch("8");
        int result = Pin.eachPitch(ball.getPoint());
        assertThat(result).isEqualTo(2);
    }
}
