package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.status.finished.BonusEnd;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BonusTest {
    @Test
    @DisplayName("공던졌을때 BonusEnd 테스트")
    void throwBallStrikeTest() {
        Bonus bonus = Bonus.of(Point.of(10), Point.of(10));
        assertThat(
                bonus.throwBall(Point.of(7))
        ).isInstanceOf(BonusEnd.class);
    }
}
