package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadyTest {

    @DisplayName("Ready 상태에서 볼링공을 굴리는 기능 테스트")
    @Test
    void bowlTest() {
        assertThat(new Ready().bowl(10)).isEqualTo(new Strike());
        assertThat(new Ready().bowl(7).bowl(3)).isEqualTo(new Spare(new Pins(7), new Pins(3)));
        assertThat(new Ready().bowl(7).bowl(2)).isEqualTo(new Miss(new Pins(7), new Pins(2)));
    }

    @DisplayName("현재 상태의 점수를 계산할 수 있는지를 반환")
    @Test
    void canCalculateTest() {
        assertThat(new Ready().canCalculate(new Score(1, 2))).isFalse();
    }

    @DisplayName("점수를 계산하려고 할 경우 예외가 발생한다.")
    @Test
    void scoreTest() {
        assertThatThrownBy(() -> new Ready().score())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이전 점수와 Ready상태를 더하려고 할 경우 예외가 발생한다.")
    @Test
    void sumBeforeScoreTest() {
        assertThatThrownBy(() -> new Ready().sumBeforeScore(new Score(1, 2)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}