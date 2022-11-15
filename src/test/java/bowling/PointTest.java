package bowling;

import bowling.domain.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PointTest {
    @Test
    @DisplayName("쓰러트린 핀의 수가 음수")
    void test1() {
        assertThatThrownBy(() -> {
            Point.of(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("핀 더하기")
    void test3() {
        // given
        Point point1 = Point.of(1);
        Point point2 = Point.of(2);
        // when
        Point point = point1.add(point2);
        // then
        assertThat(point).isEqualTo(Point.of(3));
    }

    @Test
    @DisplayName("모든 핀이 쓰러졌는가")
    void test5() {
        // given
        Point point = Point.of(Point.MAX_POINT_COUNT);
        // when
        // then
        assertThat(point.areAllPinsDown()).isTrue();
    }

}
