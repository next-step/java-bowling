package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.status.finished.FinalSpareEnd;
import bowling.domain.status.finished.FinalStrikeEnd;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalSpareTest {

    @Test
    @DisplayName("공던졌을때 FinalSpareEnd 테스트")
    void throwBallTest() {
        FinalSpare finalSpare = FinalSpare.of(Point.of(3), Point.of(7));
        assertThat(
                finalSpare.throwBall(Point.of(7))
        ).isInstanceOf(FinalSpareEnd.class);
    }

    @Test
    @DisplayName("공던졌을때 FinalSpareEnd 테스트2")
    void throwBallTest2() {
        FinalSpare finalSpare = FinalSpare.of(Point.of(0), Point.of(10));
        assertThat(
                finalSpare.throwBall(Point.of(7))
        ).isInstanceOf(FinalSpareEnd.class);
    }
}
