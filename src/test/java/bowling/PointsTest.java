package bowling;

import bowling.domain.Point;
import bowling.domain.Points;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointsTest {

    @Test
    public void get_first_point_score_test() {
        Points points = new Points();
        points.addPoint(new Point(1));
        assertThat(points.findFirstPointScore()).isEqualTo(1);
    }

    @Test
    public void get_second_point_score_test() {
        Points points = new Points();
        points.addPoint(new Point(1));
        points.addPoint(new Point(2));
        assertThat(points.findSecondPointScore()).isEqualTo(2);
    }


    @Test
    public void get_point_sum_test() {
        Points points = new Points();
        points.addPoint(new Point(4));
        points.addPoint(new Point(6));
        assertThat(points.currentPointSum()).isEqualTo(10);
    }

    @Test
    public void has_bonus_point_test() {
        Points points = new Points();
        points.addPoint(new Point(4, true));
        assertThat(points.findBonusPoint().isPresent()).isEqualTo(true);
    }
}
