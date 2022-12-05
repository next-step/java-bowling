package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PointsTest {
    @Test
    @DisplayName("3, 5를 가지고 있으면 합은 8")
    void test1() {
        Points points = new Points(List.of(Point.of(3), Point.of(5)));
        assertThat(points.sum()).isEqualTo(Point.of(8));
    }

    @Test
    @DisplayName("Score(10, 2)를 가지고 3, 5를 가지고 있는 Points에서 최종 점수를 계산하면 Score(18, 0)이 나온다.")
    void test2() {
        Points points = new Points(List.of(Point.of(3), Point.of(5)));
        assertThat(points.addExtraPoint(new Score(Point.of(10), 2)))
                .isEqualTo(new Score(Point.of(18), 0));
    }

}
