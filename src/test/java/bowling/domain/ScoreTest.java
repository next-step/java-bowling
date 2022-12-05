package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("추가 포인트를 받을 수 있는가")
    void test1() {
        Score score = new Score(Point.of(10), 0);
        assertThat(score.canReceiveExtraPoint()).isFalse();
    }

    @Test
    @DisplayName("추가 포인트를 더하기")
    void test2() {
        Score score = new Score(Point.of(10), 1);
        assertThat(score.addExtraPoint(Point.of(15))).isEqualTo(new Score(Point.of(25), 0));
    }

}
