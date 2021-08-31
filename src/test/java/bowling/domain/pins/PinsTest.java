package bowling.domain.pins;

import bowling.domain.score.Score;
import bowling.exception.IllegalPinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    @DisplayName("입력하는 볼링핀은 0부터 10까지 숫자여야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {11, -1, 20, 30})
    void pins_validation(int pins) {
        assertThatThrownBy(() -> Pins.of(pins))
                .isInstanceOf(IllegalPinNumberException.class);
    }

    @DisplayName("볼링핀의 점수를 리턴한다.")
    @Test
    void pins_score() {
        Pins pins = Pins.of(7);
        Score score = pins.getScore(Score.of(3, 1));

        assertThat(score.getScore()).isEqualTo(10);
    }
}