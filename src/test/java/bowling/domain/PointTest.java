package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {
    @Test
    @DisplayName("포인트 더하기")
    void test1() {
        Point point1 = Point.of(1);
        Point point2 = Point.of(2);
        assertThat(point1.add(point2)).isEqualTo(Point.of(3));
    }

}
