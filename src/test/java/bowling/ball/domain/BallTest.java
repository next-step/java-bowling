package bowling.ball.domain;

import bowling.global.exception.InputPitchPointNullPointerException;
import bowling.global.exception.OutOfPitchRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class BallTest {

    @Test
    @DisplayName("볼링공 투구. 0 ~ 10의 값만 허용한다.")
    void pitch() {
        Ball ball = Ball.pitch("7");
        assertThat(ball.getPoint()).isBetween(0, 10);
    }

    @Test
    @DisplayName("볼링공 투구 시 투구 횟수 증가")
    void increasePitchNumber() {
        Ball ball = Ball.pitch("6");
        assertThat(ball.getPitchNumber()).isEqualTo(2);
    }

    @ParameterizedTest
    @DisplayName("볼링골 투구 입력값이 null일 경우 Exception 발생")
    @NullAndEmptySource
    void validateInputPitchIsNull(String input) {
        assertThatExceptionOfType(InputPitchPointNullPointerException.class)
                .isThrownBy(() -> {
                    Ball.pitch(input);
                });
    }

    @Test
    @DisplayName("볼링골 투구 입력값의 범위가 0 ~ 10을 벗어나는 경우 Exception 발생")
    void validatePitchRange() {
        assertThatExceptionOfType(OutOfPitchRangeException.class)
                .isThrownBy(() -> {
                    Ball.pitch("11");
                });
    }

}
