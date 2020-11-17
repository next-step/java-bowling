package bowling.domain.state;

import bowling.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @DisplayName("점수 넘김 테스트")
    @Test
    void nextScore() {
        Spare spare = new Spare(Point.of(5));
        assertThat(spare.isSpare()).isTrue();
        assertThat(spare.getScore()).isEqualTo("/");
    }
}
