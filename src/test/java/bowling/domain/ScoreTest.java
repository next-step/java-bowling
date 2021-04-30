package bowling.domain;

import bowling.domain.score.Point;
import bowling.domain.score.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @DisplayName("첫번째 포인트와 두번째 포인트가 합이 10이 넘어서는 안된다.")
    @Test
    void case1() {
        Point firstPoint = Point.of(5);
        Point secondPoint = Point.of(6);

        Assertions.assertThatThrownBy(() -> {
            Score.of(firstPoint, secondPoint);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("두번째 포인트는 첫번째 10 - 첫번째 포인트 보다 커서는 안된다.")
    @Test
    void case2() {
        Point firstPoint = Point.of(5);
        Point secondPoint = Point.of(6);

        Score first = Score.first(firstPoint);
        Assertions.assertThatThrownBy(() -> {
            first.next(secondPoint);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("두번째 포인트는 첫번째 10 - 첫번째 포인트 보다 커서는 안된다.")
    @Test
    void case3() {
        Point firstPoint = Point.of(10);

        Score first = Score.first(firstPoint);
        assertThat(first.type()).isEqualTo(BowlingRole.STRIKE);
    }

    @DisplayName("첫번쨰 포인트와 두번째 포인트 합이 10이면 스페어다.")
    @Test
    void case4() {
        Point firstPoint = Point.of(5);
        Point secondPoint = Point.of(5);

        Score first = Score.first(firstPoint);
        Score score = first.next(secondPoint);

        assertThat(score.type()).isEqualTo(BowlingRole.SPARE);
    }

    @DisplayName("모든 포인트가 0이면 미스다.")
    @Test
    void case5() {
        Point firstPoint = Point.of(5);
        Point secondPoint = Point.of(4);

        Score first = Score.first(firstPoint);
        Score score = first.next(secondPoint);
        assertThat(score.type()).isEqualTo(BowlingRole.MISS);
    }
}