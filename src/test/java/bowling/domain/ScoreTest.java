package bowling.domain;

import bowling.exception.InvalidScoreCountException;
import bowling.exception.InvalidScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @Test
    @DisplayName("추가점수를 제외한 기본 점수의 범위는 0 ~ 10 점이다.")
    void invalidScore() {
        assertThatThrownBy(() -> Score.of(-1, 2)).isInstanceOf(InvalidScoreException.class);
        assertThatThrownBy(() -> Score.of(11, 2)).isInstanceOf(InvalidScoreException.class);
    }

    @Test
    @DisplayName("추가점수의 횟수의 범위는 0 ~ 2 이다.")
    void invalidScore_count() {
        assertThatThrownBy(() -> Score.of(0, 3)).isInstanceOf(InvalidScoreCountException.class);
        assertThatThrownBy(() -> Score.of(0, -1)).isInstanceOf(InvalidScoreCountException.class);
    }

    @Test
    @DisplayName("스트라이크의 경우 10점과 2번의 추가점수 기회가 있다.")
    void strike() {
        assertThat(Score.strike()).isEqualTo(Score.of(10, 2));
    }
}