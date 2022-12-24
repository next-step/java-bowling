package bowling.model.state;

import bowling.model.Pin;
import bowling.model.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ReadyTest {

    Ready ready = new Ready();

    @Test
    @DisplayName("레디 상태에서 10개를 맞추면 스트라이크이다.")
    void strike() {
        assertThat(ready.bowl(Pin.of(10))).isInstanceOf(Strike.class);
    }

    @DisplayName("레디 상태에서 10개 이하 맞추면 첫번째 볼이다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void first(int input) {
        assertThat(ready.bowl(Pin.of(input))).isInstanceOf(First.class);
    }

    @Test
    @DisplayName("레디는 점수를 생성할 수 없다.")
    void getScore() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> {
                    ready.getScore();
                });
    }

    @Test
    @DisplayName("레디는 이전 점수 상태를 그대로 반환한다.")
    void addBonusScore() {
        Score beforeScore = new Score(10, 1);
        assertThat(ready.addBonusScore(beforeScore)).isEqualTo(beforeScore);
    }
}
