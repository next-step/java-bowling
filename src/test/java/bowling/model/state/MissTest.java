package bowling.model.state;

import bowling.model.Pin;
import bowling.model.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class MissTest {

    Miss miss = new Miss(Pin.of(3), Pin.of(4));

    @Test
    @DisplayName("미스는 상태에서 또 던지면 예외가 발생한다.")
    public void bowl() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> {
                    miss.bowl(Pin.of(1)).bowl(Pin.of(1));
                });
    }

    @Test
    @DisplayName("미스의 점수는 첫번째 볼과 두번째 볼의 합이다.")
    void getScore() {
        assertThat(miss.getScore()).isEqualTo(new Score(7, 0));
    }

    @Test
    @DisplayName("첫번째 볼을 보너스 점수로 더한다.")
    void addFirstBonusScore() {
        assertThat(miss.addBonusScore(new Score(10, 1)))
                .isEqualTo(new Score(13, 0));
    }

    @Test
    @DisplayName("첫번째 볼과 두번째 볼을 보너스 점수로 더한다.")
    void addAllBonusScore() {
        assertThat(miss.addBonusScore(new Score(10, 2)))
                .isEqualTo(new Score(17, 0));
    }
}
