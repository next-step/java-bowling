package bowling.domain.point;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointsTest {

    @Test
    void createPointsTest() {
        Points points = Points.create();
        assertThat(points.size()).isEqualTo(0);
    }

    @Test
    void addPointsTest() {
        Points points = Points.create();

        Point point = Point.inputPoint(8);
        points.add(point);

        assertThat(points.size()).isEqualTo(1);
    }

    @Test
    void sumPointsTest() {
        Points points = Points.create();

        Point point = Point.inputPoint(8);
        points.add(point);
        points.add(point);

        assertThat(points.sum()).isEqualTo(16);
    }

}
