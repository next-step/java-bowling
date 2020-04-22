package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.status.finished.FinalStrikeEnd;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalStrikeTest {

    @Test
    @DisplayName("공던졌을때 FinalStrikeEnd 테스트")
    void throwBallTest() {
        FinalStrike finalStrike = FinalStrike.of(Point.of(10));
        assertThat(
                finalStrike.throwBall(Point.of(7))
        ).isInstanceOf(FinalStrikeEnd.class);
    }

    @Test
    @DisplayName("공던졌을때 FinalStrikeEnd 테스트2")
    void throwBallTest2() {
        FinalStrike finalStrike = FinalStrike.of(Point.of(3));
        assertThat(
                finalStrike.throwBall(Point.of(10))
        ).isInstanceOf(FinalStrikeEnd.class);
    }

}
