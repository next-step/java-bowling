package bowling;

import bowling.domain.Points;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointsTest {

    @Test
    public void get_first_point_score_test() {
        Points points = new Points();
        points.addPoint(1);
        assertThat(points.findFirstPointScore()).isEqualTo(1);
    }

    @Test
    public void get_second_point_score_test() {
        Points points = new Points();
        points.addPoint(1);
        points.addPoint(2);
        assertThat(points.findSecondPoint().currentPoint()).isEqualTo(2);
    }


    @Test
    public void get_point_sum_test() {
        Points points = new Points();
        points.addPoint(4);
        points.addPoint(6);
        assertThat(points.currentPointSum()).isEqualTo(10);
    }

    @Test
    public void has_bonus_point_test() {
        Points points = new Points();
        points.addBonusPoint(4);
        assertThat(points.findBonusPoint().isBonusPoint()).isEqualTo(true);
    }
}
