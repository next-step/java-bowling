package step2.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReadyTest {

    @Test
    @DisplayName("준비상태일 때, 10점 투구로 스트라이크 반환")
    void returnStrike() {
        Ready ready = new Ready();
        assertThat(ready.bowl(10)).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("준비상태일 때, 10점이 아닌 경우 FirstBowl 반환")
    void returnFirstBowl() {
        Ready ready = new Ready();
        assertThat(ready.bowl(9)).isInstanceOf(FirstBowl.class);
    }

    @Test
    @DisplayName("준비상태는 종료상태가 아님")
    void isFinish() {
        Ready ready = new Ready();
        assertThat(ready.isFinish()).isFalse();
    }

    @Test
    @DisplayName("준비상태는 점수를 반환할 수 없음")
    void cannotGetScore() {
        Ready ready = new Ready();
        assertThrows(IllegalArgumentException.class,
                ready::getScore);
    }

    @Test
    @DisplayName("준비상태일 때 점수 계산 예외 처리")
    void calculateAdditionalExceptionScore() {
        Ready ready = new Ready();
        Score score = Score.ofStrike();

        assertThrows(IllegalArgumentException.class,
                () -> ready.calculateAdditionalScore(score));
    }

}