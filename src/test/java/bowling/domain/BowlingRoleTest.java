package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingRoleTest {

    @DisplayName("첫번쨰 포인트가 10이면 STRIKE이다.")
    @Test
    void case1() {
        BowlingRole bowlingRole = BowlingRole.valueOf(Score.of(Point.of(10), Point.of(0)));
        assertThat(bowlingRole).isEqualTo(BowlingRole.STRIKE);
    }

    @DisplayName("두개의 포인트 합이 10이면 스페어다.")
    @Test
    void case2() {
        BowlingRole bowlingRole = BowlingRole.valueOf(Score.of(Point.of(5), Point.of(5)));
        assertThat(bowlingRole).isEqualTo(BowlingRole.SPARE);
    }

    @DisplayName("두개의 포인트 합이 10이 아니면 미스다.")
    @Test
    void case3() {
        BowlingRole bowlingRole = BowlingRole.valueOf(Score.of(Point.of(5), Point.of(4)));
        assertThat(bowlingRole).isEqualTo(BowlingRole.MISS);
    }

}