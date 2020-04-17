package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PointsTest {
    @Test
    @DisplayName("포인트 합 테스트")
    void getSumTest() {
        Points points = new Points(Arrays.asList(new Point(4), new Point(6), new Point(10)));
        assertThat(
            points.getSum()
        ).isEqualTo(20);
    }

    @Test
    @DisplayName("초구 스트라이크 테스트")
    void isStrikeTest() {
        Points points = new Points(Arrays.asList(new Point(10)));
        assertThat(
                points.isStrike()
        ).isTrue();
    }

    @Test
    @DisplayName("연속 스트라이크 테스트")
    void isDoubleStrikeTest() {
        Points points = new Points(Arrays.asList(new Point(10), new Point(10)));
        assertThat(
                points.isDoubleStrike()
        ).isTrue();
    }

    @Test
    @DisplayName("스페어 테스트")
    void isSpareTest() {
        Points points = new Points(Arrays.asList(new Point(5), new Point(5)));
        assertThat(
                points.isSpare()
        ).isTrue();
    }
}
