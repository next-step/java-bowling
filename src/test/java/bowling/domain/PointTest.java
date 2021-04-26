package bowling.domain;

import bowling.domain.score.Point;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

    @DisplayName("포인트는 0과 10 사이여야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void case1(int point) {
        Assertions.assertThatThrownBy(() -> {
            Point.of(point);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("포인트를 -1로 초기화 한다.")
    @Test
    void case2() {
        Point point = Point.of();
        assertThat(point.toInt()).isEqualTo(-1);
    }
}