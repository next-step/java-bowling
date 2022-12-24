package bowling.model.state;

import bowling.model.Pin;
import bowling.model.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class SpareTest {

    Spare spare = new Spare(Pin.of(8), Pin.of(2));

    @Test
    @DisplayName("스페어 상태에서 또 던지면 예외가 발생한다.")
    public void bowl() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> {
                    spare.bowl(Pin.of(1));
                });
    }

    @Test
    @DisplayName("스페어의 점수는 10이다.")
    void getScore() {
        assertThat(spare.getScore()).isEqualTo(new Score(10, 1));
    }

    @Test
    @DisplayName("첫번째 볼을 보너스 점수로 더한다.")
    void addFirstBonusScore() {
        assertThat(spare.addBonusScore(new Score(10, 1)))
                .isEqualTo(new Score(18, 0));
    }

    @Test
    @DisplayName("첫번째 볼과 두번째 볼을 보너스 점수로 더한다.")
    void addAllBonusScore() {
        assertThat(spare.addBonusScore(new Score(10, 2)))
                .isEqualTo(new Score(20, 0));
    }
}
