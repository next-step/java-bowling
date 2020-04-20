package bowling.domain.point;

import bowling.domain.score.ScoreStatus;
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
    @DisplayName("스트라이크 테스트")
    void isStrikeTest() {
        Points points = new Points(Arrays.asList(Point.of(10)));
        assertThat(
                points.getScoreStatus()
        ).isEqualTo(ScoreStatus.STRIKE);
    }

    @Test
    @DisplayName("스페어 테스트")
    void isSpareTest() {
        Points points = new Points(Arrays.asList(Point.of(3), Point.of(7)));
        assertThat(
                points.getScoreStatus()
        ).isEqualTo(ScoreStatus.SPARE);
    }

    @Test
    @DisplayName("거터 테스트")
    void isGutterTest() {
        Points points = new Points(Arrays.asList(Point.of(0), Point.of(0)));
        assertThat(
                points.getScoreStatus()
        ).isEqualTo(ScoreStatus.GUTTER);
    }

    @Test
    @DisplayName("미스 테스트")
    void isMissTest() {
        Points points = new Points(Arrays.asList(Point.of(3), Point.of(0)));
        assertThat(
                points.getScoreStatus()
        ).isEqualTo(ScoreStatus.MISS);
    }
}
