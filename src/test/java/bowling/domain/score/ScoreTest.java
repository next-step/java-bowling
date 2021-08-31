package bowling.domain.score;

import bowling.exception.CannotCalculateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @DisplayName("스트라이크는 투구를 2번 더해야지 계산 할 수 있다.")
    @Test
    void strike() {
        Score score = Score.of(10, 2);

        assertThat(score.canCalculateScore()).isFalse();
        assertThatThrownBy(score::getScore)
                .isInstanceOf(CannotCalculateException.class);
    }

    @DisplayName("스페어는 투구를 1번 더해야지 계산 할 수 있다.")
    @Test
    void spare() {
        Score score = Score.of(10, 1);

        assertThat(score.canCalculateScore()).isFalse();
        assertThatThrownBy(score::getScore)
                .isInstanceOf(CannotCalculateException.class);
    }

    @DisplayName("미스 또는 거터 점수 계산 ")
    @Test
    void miss_or_gutter() {
        Score miss = Score.of(3, 0);
        Score gutter = Score.of(0, 0);

        assertAll(
                () -> assertThat(miss.canCalculateScore()).isTrue(),
                () -> assertThat(miss.getScore()).isEqualTo(3),
                () -> assertThat(gutter.canCalculateScore()).isTrue(),
                () -> assertThat(gutter.getScore()).isZero()
        );
    }
}