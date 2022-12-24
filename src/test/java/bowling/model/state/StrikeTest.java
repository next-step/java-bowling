package bowling.model.state;

import bowling.model.Pin;
import bowling.model.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class StrikeTest {

    Strike strike = new Strike();

    @Test
    @DisplayName("스트라이크 상태에서 또 던지면 예외가 발생한다.")
    public void bowl() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> {
                    strike.bowl(Pin.of(10)).bowl(Pin.of(2));
                });
    }

    @Test
    @DisplayName("스트라이크의 점수는 10이다.")
    void getScore() {
        assertThat(strike.getScore()).isEqualTo(new Score(10, 2));
    }

    @Test
    @DisplayName("스트라이크를 보너스 점수로 더한다.")
    void addBonusScore() {
        assertThat(strike.addBonusScore(new Score(10, 1)))
                .isEqualTo(new Score(20, 0));
    }
}
