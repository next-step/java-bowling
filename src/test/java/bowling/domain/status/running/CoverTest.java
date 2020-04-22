package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.status.finished.Gutter;
import bowling.domain.status.finished.Miss;
import bowling.domain.status.finished.Spare;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CoverTest {
    @Test
    @DisplayName("공던졌을때 스페어 테스트")
    void throwBallStrikeTest() {
        Cover cover = Cover.of(Point.of(3));
        assertThat(
                cover.throwBall(Point.of(7))
        ).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("공던졌을때 거터 테스트")
    void throwBallGutterTest() {
        Cover cover = Cover.of(Point.of(0));
        assertThat(
                cover.throwBall(Point.of(0))
        ).isInstanceOf(Gutter.class);
    }

    @Test
    @DisplayName("공던졌을때 미쓰 테스트")
    void throwBallCoverTest() {
        Cover cover = Cover.of(Point.of(3));
        assertThat(
                cover.throwBall(Point.of(4))
        ).isInstanceOf(Miss.class);
    }
}
