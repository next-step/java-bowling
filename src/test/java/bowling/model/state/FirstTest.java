package bowling.model.state;

import bowling.model.Pin;
import bowling.model.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class FirstTest {

    First first = new First(Pin.of(3));

    @Test
    @DisplayName("첫번째 볼에서 모두 쓰러뜨리면 스페어다.")
    void spare() {
        assertThat(first.bowl(Pin.of(7))).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("첫번째 볼에서 총 10개 이하 맞추면 미스다.")
    void miss() {
        assertThat(first.bowl(Pin.of(3))).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("첫번째 볼에서 핀의 개수를 초과하면 예외가 발생한다.")
    void overPinsNumberException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    first.bowl(Pin.of(8));
                });
    }

    @Test
    @DisplayName("첫번째 볼은 점수를 생성할 수 없다.")
    void getScore() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> {
                    first.getScore();
                });
    }

    @Test
    @DisplayName("첫번째 볼을 보너스 점수로 더한다.")
    void addBonusScore() {
        assertThat(first.addBonusScore(new Score(10, 1)))
                .isEqualTo(new Score(13, 0));
    }
}
