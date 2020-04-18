package bowling.domain.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PointsTest {
    @Test
    @DisplayName("포인트 합 테스트")
    void getSumTest() {
        Points points = new Points(Arrays.asList(Point.of(4), Point.of(6), Point.of(10)));
        assertThat(
            points.getSum()
        ).isEqualTo(20);
    }

    @Test
    @DisplayName("초구 스트라이크 테스트")
    void isStrikeTest() {
        Points points = new Points(Arrays.asList(Point.of(10)));
        assertThat(
                points.isFirstStrike()
        ).isTrue();
    }
}
