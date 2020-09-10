package camp.nextstep.edu.rebellion.bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class AttemptTest {
    @DisplayName("시도 횟 수가 잘 생성되는 지 확인")
    @Test
    public void attemptTest() {
        // given
        int initCount = 2;

        // when
        Attempt attempt = new Attempt(initCount);

        // then
        assertAll(
                () -> assertThat(attempt.isFirstAttempt()).isTrue(),
                () -> assertThat(attempt.hasAttempt()).isTrue()
        );
    }

    @DisplayName("시도 횟 수가 잘 기록 되는지 확인")
    @Test
    public void triedTest() {
        // given
        int initCount = 2;

        // when
        Attempt attempt = new Attempt(initCount);

        // and
        attempt.tried();

        // then
        assertAll(
                () -> assertThat(attempt.isFirstAttempt()).isFalse(),
                () -> assertThat(attempt.hasAttempt()).isTrue()
        );

        // and
        attempt.tried();

        // then
        assertAll(
                () -> assertThat(attempt.isFirstAttempt()).isFalse(),
                () -> assertThat(attempt.hasAttempt()).isFalse()
        );
    }

    @DisplayName("시도 횟수 초기 값이 잘못되었을 경우 예외 발생")
    @Test
    public void attemptRangeThrownTest() {
        // given
        int initCount = 10;

        // when & then
        assertThatThrownBy(() -> new Attempt(initCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도 횟 수 초기 값이 잘못 되었습니다 (최대 3) : "
                        + initCount);
    }
}
