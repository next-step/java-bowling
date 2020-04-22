package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.status.finished.Gutter;
import bowling.domain.status.finished.Miss;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalCoverTest {
    @Test
    @DisplayName("공던졌을때 FinalStrike 테스트")
    void throwBallStrikeTest() {
        FinalCover finalCover = FinalCover.of(Point.of(10));
        assertThat(
                finalCover.throwBall(Point.of(7))
        ).isInstanceOf(FinalStrike.class);
    }

    @Test
    @DisplayName("공던졌을때 FinalStrike 테스트2")
    void throwBallStrikeTest2() {
        FinalCover finalCover = FinalCover.of(Point.of(10));
        assertThat(
                finalCover.throwBall(Point.of(10))
        ).isInstanceOf(FinalStrike.class);
    }

    @Test
    @DisplayName("공던졌을때 FinalSpare 테스트")
    void throwBallCoverTest() {
        FinalCover finalCover = FinalCover.of(Point.of(3));
        assertThat(
                finalCover.throwBall(Point.of(7))
        ).isInstanceOf(FinalSpare.class);
    }

    @Test
    @DisplayName("공던졌을때 FinalSpare 테스트2")
    void throwBallCoverTest2() {
        FinalCover finalCover = FinalCover.of(Point.of(0));
        assertThat(
                finalCover.throwBall(Point.of(10))
        ).isInstanceOf(FinalSpare.class);
    }

    @Test
    @DisplayName("공던졌을때 Gutter 테스트")
    void throwBallGutterTest() {
        FinalCover finalCover = FinalCover.of(Point.of(0));
        assertThat(
                finalCover.throwBall(Point.of(0))
        ).isInstanceOf(Gutter.class);
    }

    @Test
    @DisplayName("공던졌을때 MISS 테스트")
    void throwBallMissTest() {
        FinalCover finalCover = FinalCover.of(Point.of(3));
        assertThat(
                finalCover.throwBall(Point.of(6))
        ).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("공던졌을때 MISS 테스트2")
    void throwBallMissTest2() {
        FinalCover finalCover = FinalCover.of(Point.of(0));
        assertThat(
                finalCover.throwBall(Point.of(6))
        ).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("공던졌을때 MISS 테스트3")
    void throwBallMissTest3() {
        FinalCover finalCover = FinalCover.of(Point.of(3));
        assertThat(
                finalCover.throwBall(Point.of(0))
        ).isInstanceOf(Miss.class);
    }
}
